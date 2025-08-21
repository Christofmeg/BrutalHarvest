package com.christofmeg.brutalharvest.mixin;

import com.christofmeg.brutalharvest.common.block.FarmlandSlabBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.christofmeg.brutalharvest.common.block.base.BlockProperties.LOWER;

@Mixin(CropBlock.class)
public abstract class CropBlockMixin extends Block {

    //https://linkie.shedaniel.dev/mappings

    @Final
    @Shadow private static VoxelShape[] SHAPE_BY_AGE;
    @Shadow public abstract int getAge(BlockState pState);
    @Shadow protected abstract IntegerProperty getAgeProperty();

    @Unique
    private static final VoxelShape[] SHAPE_BY_AGE_SLAB = new VoxelShape[]{
            Block.box(0.0, -8.0, 0.0, 16.0, -6.0, 16.0),
            Block.box(0.0, -8.0, 0.0, 16.0, -4.0, 16.0),
            Block.box(0.0, -8.0, 0.0, 16.0, -2.0, 16.0),
            Block.box(0.0, -8.0, 0.0, 16.0, 0.0, 16.0),
            Block.box(0.0, -8.0, 0.0, 16.0, 2.0, 16.0),
            Block.box(0.0, -8.0, 0.0, 16.0, 4.0, 16.0),
            Block.box(0.0, -8.0, 0.0, 16.0, 6.0, 16.0),
            Block.box(0.0, -8.0, 0.0, 16.0, 8.0, 16.0)};

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(Properties pProperties, CallbackInfo ci) {
//        this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), 0).setValue(LOWER, false));
    }

    public CropBlockMixin(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), 0).setValue(LOWER, false));
    }

    @Inject(method = "createBlockStateDefinition", at = @At("TAIL")) //injectExtraProperty
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder, CallbackInfo ci) {
        pBuilder.add(LOWER);
    }

    @Inject(
            method = "getShape(" +
                    "Lnet/minecraft/world/level/block/state/BlockState;" +
                    "Lnet/minecraft/world/level/BlockGetter;" +
                    "Lnet/minecraft/core/BlockPos;" +
                    "Lnet/minecraft/world/phys/shapes/CollisionContext;" +
                    ")" +
                    "Lnet/minecraft/world/phys/shapes/VoxelShape;", //getOutlineShape
            at = @At("HEAD"),
            cancellable = true
    )
    public void getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        BlockState below = level.getBlockState(pos.below());
        if (below.getBlock() instanceof FarmlandSlabBlock && below.getValue(FarmlandSlabBlock.TYPE) == SlabType.BOTTOM) {
            cir.setReturnValue(SHAPE_BY_AGE_SLAB[this.getAge(state)]);
        }
        else {
            cir.setReturnValue(SHAPE_BY_AGE[this.getAge(state)]);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull BlockState updateShape(@NotNull BlockState state, @NotNull Direction direction, @NotNull BlockState neighborState, @NotNull LevelAccessor level, @NotNull BlockPos currentPos, @NotNull BlockPos neighborPos) {
        if (direction == Direction.DOWN) {
            boolean isLower = neighborState.getBlock() instanceof FarmlandSlabBlock && neighborState.getValue(FarmlandSlabBlock.TYPE) == SlabType.BOTTOM;
            System.out.println("Updating LOWER: " + isLower); // Debug log
            return state.setValue(LOWER, isLower);
        }
        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }












    @Unique
    private boolean isLower(BlockState blockState) {
        return blockState.getBlock() instanceof FarmlandSlabBlock && blockState.getValue(FarmlandSlabBlock.TYPE) == SlabType.BOTTOM;
    }



}

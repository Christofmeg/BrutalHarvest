package com.christofmeg.brutalharvest.common.block;

import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class DirtPathSlabBlock extends DirtPathBlock implements SimpleWaterloggedBlock {

    public static final EnumProperty<SlabType> TYPE;
    public static final BooleanProperty WATERLOGGED;
    protected static final VoxelShape BOTTOM_AABB;
    protected static final VoxelShape TOP_AABB;

    public DirtPathSlabBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(TYPE, SlabType.BOTTOM).setValue(WATERLOGGED, false));
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState pState) {
        return pState.getValue(TYPE) != SlabType.DOUBLE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(TYPE, WATERLOGGED);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        SlabType $$4 = pState.getValue(TYPE);
        switch ($$4) {
            case DOUBLE -> {
                return SHAPE;
            }
            case TOP -> {
                return TOP_AABB;
            }
            default -> {
                return BOTTOM_AABB;
            }
        }
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        BlockState state = context.getLevel().getBlockState(pos);
        if (state.is(this)) {
            return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? BlockRegistry.DIRT_SLAB.get().defaultBlockState() : state.setValue(TYPE, SlabType.DOUBLE).setValue(WATERLOGGED, false);
        } else {
            FluidState fluidState = context.getLevel().getFluidState(pos);
            BlockState blockState = this.defaultBlockState().setValue(TYPE, SlabType.BOTTOM).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
            Direction direction = context.getClickedFace();
            return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ?
                    BlockRegistry.DIRT_SLAB.get().defaultBlockState() :
                    direction != Direction.DOWN && (direction == Direction.UP || !(context.getClickLocation().y - (double)pos.getY() > 0.5)) ?
                            blockState : blockState.setValue(TYPE, SlabType.TOP);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        ItemStack $$2 = pUseContext.getItemInHand();
        SlabType $$3 = pState.getValue(TYPE);
        if ($$3 != SlabType.DOUBLE && $$2.is(this.asItem())) {
            if (pUseContext.replacingClickedOnBlock()) {
                boolean $$4 = pUseContext.getClickLocation().y - (double)pUseContext.getClickedPos().getY() > 0.5;
                Direction $$5 = pUseContext.getClickedFace();
                if ($$3 == SlabType.BOTTOM) {
                    return $$5 == Direction.UP || $$4 && $$5.getAxis().isHorizontal();
                } else {
                    return $$5 == Direction.DOWN || !$$4 && $$5.getAxis().isHorizontal();
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Override
    public boolean placeLiquid(@NotNull LevelAccessor pLevel, @NotNull BlockPos pPos, BlockState pState, @NotNull FluidState pFluidState) {
        return pState.getValue(TYPE) != SlabType.DOUBLE && SimpleWaterloggedBlock.super.placeLiquid(pLevel, pPos, pState, pFluidState);
    }

    @Override
    public boolean canPlaceLiquid(@NotNull BlockGetter pLevel, @NotNull BlockPos pPos, BlockState pState, @NotNull Fluid pFluid) {
        return pState.getValue(TYPE) != SlabType.DOUBLE && SimpleWaterloggedBlock.super.canPlaceLiquid(pLevel, pPos, pState, pFluid);
    }

    @Override
    public @NotNull BlockState updateShape(BlockState pState, @NotNull Direction pFacing, @NotNull BlockState pFacingState, @NotNull LevelAccessor pLevel, @NotNull BlockPos pCurrentPos, @NotNull BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        } else if (pFacing == Direction.UP && !pState.canSurvive(pLevel, pCurrentPos)) {
            pLevel.scheduleTick(pCurrentPos, this, 1);
        }

        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    @Override
    public void tick(BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom) {
        if (!pState.canSurvive(pLevel, pPos)) {
            turnToDirt(null, pState, pLevel, pPos);
        }
    }

    @Override
    public void fallOn(Level pLevel, @NotNull BlockState pState, @NotNull BlockPos pPos, @NotNull Entity pEntity, float pFallDistance) {
        if (!pLevel.isClientSide && ForgeHooks.onFarmlandTrample(pLevel, pPos, BlockRegistry.DIRT_SLAB.get().defaultBlockState().setValue(TYPE, pState.getValue(TYPE)), pFallDistance, pEntity)) {
            turnToDirt(pEntity, pState, pLevel, pPos);
        }

        pEntity.causeFallDamage(pFallDistance, 1.0F, pEntity.damageSources().fall());
    }

    public static void turnToDirt(@Nullable Entity pEntity, @NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos) {
        BlockState blockstate = pushEntitiesUp(pState, BlockRegistry.DIRT_SLAB.get().defaultBlockState().setValue(TYPE, pState.getValue(TYPE)), pLevel, pPos);
        pLevel.setBlockAndUpdate(pPos, blockstate);
        pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(pEntity, blockstate));
    }

    @Override
    public boolean canSustainPlant(@NotNull BlockState state, @NotNull BlockGetter world, BlockPos pos, @NotNull Direction facing, IPlantable plantable) {
        PlantType type = plantable.getPlantType(world, pos.relative(facing));
        if (plantable instanceof BushBlock && mayPlaceOn(state)) {
            return true;
        } else if (PlantType.CROP.equals(type)) {
            return state.is(BlockRegistry.FARMLAND_SLAB.get()) && state.getValue(SlabBlock.TYPE) != SlabType.BOTTOM;
        } else if (PlantType.PLAINS.equals(type)) {
            return mayPlaceOn(state);
        } else {
            boolean isBeach = mayPlaceOn(state);
            boolean hasWater = false;

            for (Direction face : Direction.Plane.HORIZONTAL) {
                BlockState adjacentBlockState = world.getBlockState(pos.relative(face));
                FluidState adjacentFluidState = world.getFluidState(pos.relative(face));
                hasWater = adjacentBlockState.is(Blocks.FROSTED_ICE) || adjacentFluidState.is(FluidTags.WATER);
                if (hasWater) {
                    break;
                }
            }
            if (isBeach && hasWater) {
                return true;
            }

            return super.canSustainPlant(state, world, pos, facing, plantable);
        }
    }

    protected boolean mayPlaceOn(BlockState state) {
        return state.is(BlockRegistry.DIRT_SLAB.get()) && state.getValue(SlabBlock.TYPE) != SlabType.BOTTOM ||
                state.is(BlockRegistry.FARMLAND_SLAB.get()) && state.getValue(SlabBlock.TYPE) != SlabType.BOTTOM;
    }

    static {
        TYPE = BlockStateProperties.SLAB_TYPE;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        BOTTOM_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 7.0, 16.0);
        TOP_AABB = Block.box(0.0, 8.0, 0.0, 16.0, 15.0, 16.0);
    }

}

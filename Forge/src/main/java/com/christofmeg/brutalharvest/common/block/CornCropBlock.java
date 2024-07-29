package com.christofmeg.brutalharvest.common.block;

import com.christofmeg.brutalharvest.common.block.base.BaseDoubleCropBlock;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import com.christofmeg.brutalharvest.common.item.KnifeItem;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class CornCropBlock extends BaseDoubleCropBlock {

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 13);
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
            Shapes.block(),
            Shapes.block(),
            Shapes.block(),
            Shapes.block(),
            Shapes.block(),
            Shapes.block(),
            Block.box(0.0, 0.0, 0.0, 16.0, 3.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 11.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 11.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 11.0, 16.0),
    };

    public CornCropBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        return SHAPE_BY_AGE[this.getAge(pState)];
    }

    @Override
    public int getMaxAge() {
        return 6; //TODO JADE/TOP/WTHIT override
    }

    @Override
    protected int getMaxAgeDifference() {
        return 6;
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return ItemRegistry.CORN_SEEDS.get();
    }

    @Override
    protected ItemStack getBaseItemStack() {
        return ItemRegistry.CORN.get().getDefaultInstance();
    }

    @Override
    protected int getAgeAfterKnife() {
        return 3;
    }

    @Override
    public @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        int age = this.getAge(state);
        boolean matureAge = age == this.getMaxAge() || age == (this.getMaxAge() + this.getMaxAgeDifference());
        boolean deadAge = age == this.getMaxAge() + 1 || age == (this.getMaxAge() + 1 + this.getMaxAgeDifference());
        ItemStack stack = player.getItemInHand(interactionHand);
        if (stack.getItem() instanceof KnifeItem && !level.isClientSide) {
            if (!matureAge && !deadAge) {
                return InteractionResult.sidedSuccess(false);
            }

            BlockState prevState = state;
            if (matureAge) {
                state = this.getStateForAge(this.getAgeAfterKnife());
                level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
                popResource(level, pos, new ItemStack(this.getBaseItemStack().getItem(), 2 + level.random.nextInt(3)));
            } else {
                level.playSound(null, pos, SoundEvents.CROP_BREAK, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
                state = Blocks.AIR.defaultBlockState();
                int random = level.random.nextInt(2);
                if (random == 1) {
                    level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
                    popResource(level, pos, new ItemStack(this.getBaseSeedId()));
                }
            }

            if (level.getBlockState(pos.above()).getBlock() instanceof BaseDoubleCropBlock) {
                if (!state.isAir()) {
                    BlockState newBlockState = state.setValue(AGE, state.getValue(AGE) + this.getMaxAgeDifference());
                    level.setBlock(pos.above(), newBlockState, 2);
                } else {
                    level.setBlock(pos.above(), state, 2);
                }
                level.setBlock(pos, state, 2);
            } else if (level.getBlockState(pos.below()).getBlock() instanceof BaseDoubleCropBlock) {
                if (!state.isAir()) {
                    BlockState newBlockState = hasTopBlockAfterKnife(prevState) ?
                            state.setValue(AGE, state.getValue(AGE) + this.getMaxAgeDifference()) :
                            Blocks.AIR.defaultBlockState();
                    level.setBlock(pos, newBlockState, 2);
                } else {
                    level.setBlock(pos, state, 2);
                }
                level.setBlock(pos.below(), state, 2);
            } else {
                level.setBlock(pos, state, 2);
            }

            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));
            stack.hurtAndBreak(1, player, (livingEntity) -> livingEntity.broadcastBreakEvent(interactionHand));
            return InteractionResult.sidedSuccess(false);
        }
        return super.use(state, level, pos, player, interactionHand, blockHitResult);
    }

}
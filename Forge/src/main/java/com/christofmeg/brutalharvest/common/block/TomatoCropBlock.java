package com.christofmeg.brutalharvest.common.block;

import com.christofmeg.brutalharvest.common.block.base.BaseCropBlock;
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
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class TomatoCropBlock extends BaseCropBlock {

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 8);
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 11.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 13.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0)
    };

    public TomatoCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 7;
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return ItemRegistry.TOMATO_SEEDS.get();
    }

    @Override
    protected ItemStack getBaseItemStack() {
        return ItemRegistry.TOMATO.get().getDefaultInstance();
    }

    @Override
    protected int getAgeAfterKnife() {
        return 3;
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter blockGetter, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE_BY_AGE[this.getAge(state)];
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        int age = this.getAge(state);
        boolean unripeAge = age == 4 || age == 5 || age == 6;
        boolean matureAge = age == this.getMaxAge();
        boolean deadAge = age == this.getMaxAge() + 1;
        ItemStack stack = player.getItemInHand(interactionHand);
        if (stack.getItem() instanceof KnifeItem && !level.isClientSide) {
            if (!unripeAge && !matureAge && !deadAge) {
                return InteractionResult.sidedSuccess(false);
            }
            if (unripeAge) {
                state = this.getStateForAge(getAgeAfterKnife());
                popResource(level, pos, new ItemStack(ItemRegistry.UNRIPE_TOMATO.get(), 3 + level.random.nextInt(4)));
            } else if (matureAge) {
                state = this.getStateForAge(getAgeAfterKnife());
                popResource(level, pos, new ItemStack(this.getBaseItemStack().getItem(), 3 + level.random.nextInt(4)));
            } else {
                state = Blocks.AIR.defaultBlockState();
                popResource(level, pos, new ItemStack(ItemRegistry.ROTTEN_TOMATO.get(), 3 + level.random.nextInt(4)));
            }
            level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            level.setBlock(pos, state, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));
            stack.hurtAndBreak(1, player, (livingEntity) -> livingEntity.broadcastBreakEvent(interactionHand));
            return InteractionResult.sidedSuccess(false);
        }
        return super.use(state, level, pos, player, interactionHand, blockHitResult);
    }

}
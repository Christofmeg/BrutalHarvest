package com.christofmeg.brutalharvest.common.block.base;

import com.christofmeg.brutalharvest.common.block.CucumberCropBlock;
import com.christofmeg.brutalharvest.common.item.KnifeItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.NotNull;

public abstract class BaseDoubleCropBlock extends BaseCropBlock {

    public BaseDoubleCropBlock(Properties properties) {
        super(properties);
    }

    protected int getMaxAgeDifference() {
        return 0;
    }

    protected int getMaxAgeTop() {
        return this.getMaxAge() + this.getMaxAgeDifference();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, RandomSource random) {
        if (random.nextInt(3) != 0) {
            if (level.isAreaLoaded(pos, 1)) {
                if (level.getRawBrightness(pos, 0) >= 9) {
                    BlockState below = level.getBlockState(pos.below());
                    if (below.is(this)) {
                        return;
                    }
                    int age = this.getAge(state);
                    if (age < this.getMaxAge() || (age == this.getMaxAge() && random.nextInt(20) == 0)) { // 5% chance to die
                        float f = getGrowthSpeed(this, level, pos);
                        if (ForgeHooks.onCropsGrowPre(level, pos, state, random.nextInt((int)(25.0F / f) + 1) == 0)) {
                            if (age == this.getMaxAge()) {
                                level.setBlock(pos.above(), this.getStateForAge(age + 1 + this.getMaxAgeDifference()), 2);
                                level.setBlock(pos, this.getStateForAge(age + 1), 2);
                            } else {
                                this.growCrops(level, pos, state);
                            }
                            ForgeHooks.onCropsGrowPost(level, pos, state);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader levelReader, @NotNull BlockPos pos, @NotNull BlockState state, boolean $$3) {
        return this.getAge(state) < this.getMaxAge() - 1 || this.getAge(state) > this.getMaxAge() + 1 && this.getAge(state) < this.getMaxAgeTop() - 1;
    }

    @Override
    public void playerWillDestroy(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Player player) {
        BlockState belowState = level.getBlockState(pos.below());
        BlockState aboveState = level.getBlockState(pos.above());

        // If the block below is the same plant, break it
        if (belowState.is(this)) {
            level.destroyBlock(pos.below(), false, player);
        }

        // If the block above is the same plant, break it
        if (aboveState.is(this)) {
            level.destroyBlock(pos.above(), false, player);
        }

        // Call the super method to handle the original block
        super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public void growCrops(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state) {
        int newAge = this.getAge(state) + 1;
        int maxAge = this.getMaxAge();
        if (this.getAge(state) > this.getMaxAge() + 1) {
            maxAge += this.getMaxAgeDifference();
        }
        if (newAge > maxAge) {
            newAge = maxAge;
        }

        BlockState above = level.getBlockState(pos.above());
        BlockState below = level.getBlockState(pos.below());

        if (above.getBlock() instanceof BaseDoubleCropBlock) {
            level.setBlock(pos.above(), this.getStateForAge(newAge + this.getMaxAgeDifference()), 2);
        } else if (below.getBlock() instanceof BaseDoubleCropBlock) {
            level.setBlock(pos.below(), this.getStateForAge(newAge - this.getMaxAgeDifference()), 2);
        }
        if ((this.getAge(state) >= this.getMaxAge() + 1 - this.getMaxAgeDifference()) && level.getBlockState(pos.above()).is(Blocks.AIR) && !level.getBlockState(pos.below()).is(this)) {
            level.setBlock(pos.above(), this.getStateForAge(newAge + this.getMaxAgeDifference()), 2);
        }
        level.setBlock(pos, this.getStateForAge(newAge), 2);
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
        BlockState below = level.getBlockState(pos.below(1));
        if (below.is(this)) {
            int belowAge = this.getAge(below);
            int thisAge = this.getAge(state);
            if (belowAge + getMaxAgeDifference() <= thisAge) {
                return true;
            }
        }
        return super.canSurvive(state, level, pos);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        int age = this.getAge(state);
        boolean matureAge = age == this.getMaxAge() || age == this.getMaxAge() + this.getMaxAgeDifference();
        boolean deadAge = age == this.getMaxAge() + 1 || age == this.getMaxAge() + 1 + this.getMaxAgeDifference();
        ItemStack stack = player.getItemInHand(interactionHand);
        if (stack.getItem() instanceof KnifeItem && !level.isClientSide) {
            if (!matureAge && !deadAge) {
                return InteractionResult.sidedSuccess(false);
            }
            if (matureAge) {
                state = this.getStateForAge(getAgeAfterKnife());
                level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
                popResource(level, pos, new ItemStack(this.getBaseItemStack().getItem(), 2 + level.random.nextInt(3)));
            } else {
                level.playSound(null, pos, SoundEvents.CROP_BREAK, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
                state = Blocks.AIR.defaultBlockState();
                int random = level.random.nextInt(2);
                if (random == 0) {
                    level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
                    popResource(level, pos, new ItemStack(this.getBaseSeedId(), random));
                }
            }

            BlockState newBlockState = state.isAir() ? state : state.setValue(AGE, state.getValue(AGE) + this.getMaxAgeDifference());
            if (level.getBlockState(pos.above()).getBlock() instanceof CucumberCropBlock) {
                level.setBlock(pos.above(), newBlockState, 2);
                level.setBlock(pos, state, 2);
            } else if (level.getBlockState(pos.below()).getBlock() instanceof CucumberCropBlock) {
                level.setBlock(pos.below(), state, 2);
                level.setBlock(pos, newBlockState, 2);
            }

            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));
            stack.hurtAndBreak(1, player, (livingEntity) -> livingEntity.broadcastBreakEvent(interactionHand));
            return InteractionResult.sidedSuccess(false);
        }
        return super.use(state, level, pos, player, interactionHand, blockHitResult);
    }

}

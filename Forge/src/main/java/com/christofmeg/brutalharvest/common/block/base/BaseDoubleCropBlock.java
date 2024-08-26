package com.christofmeg.brutalharvest.common.block.base;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
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

    public boolean hasTopBlockAfterKnife(BlockState state) {
        if (state.getBlock() instanceof BaseDoubleCropBlock cropBlock) {
            return cropBlock.getAge(state) > (this.getMaxAge() + 1);
        } else {
            return false;
        }
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
        return this.getAge(state) < this.getMaxAge() || this.getAge(state) > this.getMaxAge() + 1 && this.getAge(state) < this.getMaxAgeTop();
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

            if (belowAge + getMaxAgeDifference() < thisAge) {
                return false;
            } else if (belowAge + getMaxAgeDifference() <= thisAge) {
                return true;
            }

            /*
            if (belowAge + getMaxAgeDifference() <= thisAge) {
                return true;
            }
             */

        }
        return super.canSurvive(state, level, pos);
    }

}

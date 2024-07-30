package com.christofmeg.brutalharvest.common.block;

import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.NotNull;

public class BlueberryBushBlock extends BushBlock implements BonemealableBlock {

    public static final int MAX_AGE = 3;
    public static final int AGE_AFTER_KNIFE = 0;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 4);

    public BlueberryBushBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    //TODO adde TOP/JADE age or growth information like crops have

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull ItemStack getCloneItemStack(@NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState) {
        return ItemRegistry.BLUEBERRY.get().getDefaultInstance();
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        if (state.getValue(AGE) == 0) {
            return Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
        } else {
            return Shapes.block();
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult use(BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        int age = state.getValue(AGE);
        boolean matureAge = age == MAX_AGE;
        boolean deadAge = age == MAX_AGE + 1;
        ItemStack stack = player.getItemInHand(interactionHand);
        if (!matureAge && !deadAge && stack.is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (!level.isClientSide) {
            if (!matureAge && !deadAge) {
        //        return InteractionResult.sidedSuccess(false);
                return super.use(state, level, pos, player, interactionHand, blockHitResult);
            }
            if (matureAge) {
                state = state.setValue(AGE, AGE_AFTER_KNIFE);
                level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
                popResource(level, pos, new ItemStack(ItemRegistry.BLUEBERRY.get(), 2 + level.random.nextInt(3)));
            } else {
                level.playSound(null, pos, SoundEvents.CROP_BREAK, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
                state = Blocks.AIR.defaultBlockState();
            }
            level.setBlock(pos, state, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));
            return InteractionResult.sidedSuccess(false);
        }
        return InteractionResult.PASS;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        int age = state.getValue(AGE);
        if (age < MAX_AGE || (age == MAX_AGE && random.nextInt(20) == 0)) { // 5% chance to die
            if (level.getRawBrightness(pos.above(), 0) >= 9 && ForgeHooks.onCropsGrowPre(level, pos, state, random.nextInt(5) == 0)) {
                BlockState blockstate = state.setValue(AGE, age + 1);
                level.setBlock(pos, blockstate, 2);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockstate));
                ForgeHooks.onCropsGrowPost(level, pos, state);
            }
        }
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState state) {
        return state.getValue(AGE) < MAX_AGE + 1;
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader levelReader, @NotNull BlockPos pos, @NotNull BlockState state, boolean $$3) {
        return state.getValue(AGE) < MAX_AGE - 1;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void entityInside(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Entity pEntity) {
        if (pEntity instanceof LivingEntity && pEntity.getType() != EntityType.FOX && pEntity.getType() != EntityType.BEE) {
            pEntity.makeStuckInBlock(pState, new Vec3(0.800000011920929, 0.75, 0.800000011920929));
            if (!pLevel.isClientSide && pState.getValue(AGE) > 0 && (pEntity.xOld != pEntity.getX() || pEntity.zOld != pEntity.getZ())) {
                double d0 = Math.abs(pEntity.getX() - pEntity.xOld);
                double d1 = Math.abs(pEntity.getZ() - pEntity.zOld);
                if (d0 >= 0.003000000026077032 || d1 >= 0.003000000026077032) {
                    pEntity.hurt(pLevel.damageSources().sweetBerryBush(), 1.0F);
                }
            }
        }
    }

    @Override
    public boolean isBonemealSuccess(@NotNull Level level, @NotNull RandomSource randomSource, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(@NotNull ServerLevel level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        int i = Math.min(3, state.getValue(AGE) + 1);
        level.setBlock(pos, state.setValue(AGE, i), 2);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

}

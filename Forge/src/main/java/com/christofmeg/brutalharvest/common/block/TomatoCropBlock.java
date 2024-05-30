package com.christofmeg.brutalharvest.common.block;

import com.christofmeg.brutalharvest.common.init.ItemRegistry;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TomatoCropBlock extends CropBlock {

    public static final IntegerProperty AGE;
    private static final VoxelShape[] SHAPE_BY_AGE;

    public TomatoCropBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), 0));
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter blockGetter, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE_BY_AGE[this.getAge(state)];
    }

    @Override
    protected @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 5;
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull List<ItemStack> getDrops(@NotNull BlockState state, LootParams.@NotNull Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        int randomTomatoes = 3 + builder.getLevel().random.nextInt(4);
        if (!dropsOriginal.isEmpty()) {
            return dropsOriginal;
        }
        return
                state.getValue(AGE) == 5 ?  List.of(new ItemStack(ItemRegistry.UNRIPE_TOMATO.get(), randomTomatoes), new ItemStack(this, 2)) :
                        state.getValue(AGE) == 6 ? List.of(new ItemStack(ItemRegistry.TOMATO.get(), randomTomatoes), new ItemStack(this, 2)) :
                                state.getValue(AGE) == 7 ? List.of(new ItemStack(ItemRegistry.ROTTEN_TOMATO.get(), randomTomatoes), new ItemStack(this, 2)) :
                                        List.of(new ItemStack(this, 1));
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, @NotNull BlockGetter blockGetter, @NotNull BlockPos pos) {
        return state.is(Blocks.FARMLAND) || state.getBlock() instanceof FarmBlock;
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader levelReader, BlockPos pos) {
        BlockPos below = pos.below();
        return this.mayPlaceOn(levelReader.getBlockState(below), levelReader, below);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, @NotNull BlockGetter blockGetter, @NotNull BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    @Override
    public boolean isPathfindable(@NotNull BlockState state, @NotNull BlockGetter blockGetter, @NotNull BlockPos pos, @NotNull PathComputationType $$3) {
        return $$3 == PathComputationType.AIR && !this.hasCollision || super.isPathfindable(state, blockGetter, pos, $$3);
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult use(BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        int age = state.getValue(AGE);
        boolean reachedTomatoAge = age == 5;
        if (!reachedTomatoAge && player.getItemInHand(interactionHand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (age > 4) {
            ItemStack stack = player.getItemInHand(interactionHand);
            if (stack.getItem() instanceof KnifeItem) {
                int randomTomatoes = 3 + level.random.nextInt(4);
                popResource(level, pos,
                        age == 5 ?  new ItemStack(ItemRegistry.UNRIPE_TOMATO.get(), randomTomatoes) :
                        age == 6 ? new ItemStack(ItemRegistry.TOMATO.get(), randomTomatoes) :
                        age == 7 ? new ItemStack(ItemRegistry.ROTTEN_TOMATO.get(), randomTomatoes) : ItemStack.EMPTY);
                level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
                BlockState newBlockState = state.setValue(AGE, 4);
                level.setBlock(pos, newBlockState, 2);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newBlockState));
                stack.hurtAndBreak(1, player, (livingEntity) -> livingEntity.broadcastBreakEvent(interactionHand));
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return super.use(state, level, pos, player, interactionHand, blockHitResult);
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(@NotNull BlockGetter $$0, @NotNull BlockPos $$1, @NotNull BlockState $$2) {
        return new ItemStack(ItemRegistry.TOMATO_SEEDS.get());
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState state) {
        return state.getValue(AGE) < 7;
    }

    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel $$1, @NotNull BlockPos $$2, @NotNull RandomSource $$3) {
        int age = state.getValue(AGE);
        if (age < 7 && $$3.nextInt(5) == 0 && $$1.getRawBrightness($$2.above(), 0) >= 9) {
            BlockState $$5 = state;
            if (age == 6) {
                if ($$3.nextInt(4) == 0) { // 25% chance
                    $$5 = state.setValue(AGE, age + 1);
                }
            } else {
                $$5 = state.setValue(AGE, age + 1);
            }
            $$1.setBlock($$2, $$5, 2);
            $$1.gameEvent(GameEvent.BLOCK_CHANGE, $$2, GameEvent.Context.of($$5));
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader levelReader, @NotNull BlockPos pos, BlockState state, boolean $$3) {
        return state.getValue(AGE) < 5;
    }

    static {
        AGE = BlockStateProperties.AGE_7;
        SHAPE_BY_AGE = new VoxelShape[]{
                Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
                Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
                Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
                Block.box(0.0, 0.0, 0.0, 16.0, 13.0, 16.0),
                Block.box(0.0, 0.0, 0.0, 16.0, 13.0, 16.0),
                Block.box(0.0, 0.0, 0.0, 16.0, 13.0, 16.0),
                Block.box(0.0, 0.0, 0.0, 16.0, 13.0, 16.0)};
    }

}

package com.christofmeg.brutalharvest.common.block;

import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import com.christofmeg.brutalharvest.common.item.KnifeItem;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CottonCropBlock extends CropBlock {

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 5);
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 7.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 9.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 11.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 13.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 15.0, 16.0)
    };

    public CottonCropBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), 0));
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter blockGetter, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE_BY_AGE[this.getAge(state)];
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, @NotNull BlockGetter blockGetter, @NotNull BlockPos pos) {
        return state.is(Blocks.FARMLAND) || state.getBlock() instanceof FarmBlock;
    }

    @Override
    public int getMaxAge() {
        return 5;
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull List<ItemStack> getDrops(@NotNull BlockState state, LootParams.@NotNull Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        int randomCotton = 2 + builder.getLevel().random.nextInt(4);
        int randomCottonSeeds = 2 + builder.getLevel().random.nextInt(2);
        if (!dropsOriginal.isEmpty()) {
            return dropsOriginal;
        }
        return state.getValue(AGE) == 5 ?  List.of(new ItemStack(ItemRegistry.COTTON.get(), randomCotton), new ItemStack(this, randomCottonSeeds)) : List.of(new ItemStack(this, 1));
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return ItemRegistry.COTTON_SEEDS.get();
    }

    @Override
    @NotNull
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader levelReader, @NotNull BlockPos pos, BlockState state, boolean $$3) {
        return state.getValue(AGE) < 4;
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult use(BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        int age = state.getValue(AGE);
        boolean reachedCottonAge = age == 5;
        if (!reachedCottonAge && player.getItemInHand(interactionHand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (reachedCottonAge) {
            ItemStack stack = player.getItemInHand(interactionHand);
            if (stack.getItem() instanceof KnifeItem) {
                int randomCotton = 2 + level.random.nextInt(4);
                int randomCottonSeeds = 2 + level.random.nextInt(2);
                popResource(level, pos, new ItemStack(ItemRegistry.COTTON.get(), randomCotton));
                popResource(level, pos, new ItemStack(ItemRegistry.COTTON_SEEDS.get(), randomCottonSeeds));
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

}

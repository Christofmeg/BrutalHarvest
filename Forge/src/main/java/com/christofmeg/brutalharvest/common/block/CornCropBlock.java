package com.christofmeg.brutalharvest.common.block;

import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import com.christofmeg.brutalharvest.common.item.KnifeItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CornCropBlock extends CropBlock {

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
            Shapes.block(),
            Shapes.block(),
            Shapes.block(),
            Shapes.block(),
            Block.box(0.0, 0.0, 0.0, 16.0, 3.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 11.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 11.0, 16.0),
    };

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 9);

    public CornCropBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), 0));
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        return SHAPE_BY_AGE[this.getAge(pState)];
    }

    @SuppressWarnings("deprecation")
    public void randomTick(@NotNull BlockState pState, ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom) {
        if (pLevel.isAreaLoaded(pPos, 1)) {
            if (pLevel.getRawBrightness(pPos, 0) >= 9) {
                int age = this.getAge(pState);
                if (age < this.getMaxAge()) {
                    float f = getGrowthSpeed(this, pLevel, pPos);
                    if (ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, pRandom.nextInt((int)(25.0F / f) + 1) == 0)) {
                        if (age >= 1) {
                            pLevel.setBlock(pPos.above(1), this.getStateForAge(age + 4 + 1), 2);
                        }
                        pLevel.setBlock(pPos, this.getStateForAge(age + 1), 2);
                        ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
                    }
                }
            }
        }
    }

    @Override
    public boolean canSustainPlant(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull Direction facing, @NotNull IPlantable plantable) {
        return this.mayPlaceOn(state, world, pos);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, @NotNull BlockGetter blockGetter, @NotNull BlockPos pos) {
        return state.is(Blocks.FARMLAND) || state.getBlock() instanceof FarmBlock;
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
        BlockState below = level.getBlockState(pos.below(1));
        if (below.is(this)) {
            int belowAge = below.getValue(AGE);
            int thisAge = state.getValue(AGE);
            if (belowAge + 4 <= thisAge) {
                return true;
            }
        }
        return super.canSurvive(state, level, pos);
    }

    @Override
    public int getMaxAge() {
        return 5; //TODO JADE/TOP/WTHIT override
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

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull List<ItemStack> getDrops(@NotNull BlockState state, LootParams.@NotNull Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        int randomAmountCrop = 3 + builder.getLevel().random.nextInt(2);
        int randomAmountSeed = 2 + builder.getLevel().random.nextInt(2);
        if (!dropsOriginal.isEmpty()) {
            return dropsOriginal;
        }
        return state.getValue(AGE) == 5 || state.getValue(AGE) == 9 ?
                List.of(new ItemStack(ItemRegistry.CORN.get(), randomAmountCrop), new ItemStack(this, randomAmountSeed)) :
                List.of(new ItemStack(this, 1));
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return ItemRegistry.CORN_SEEDS.get();
    }

    @Override
    public @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader levelReader, @NotNull BlockPos pos, BlockState state, boolean $$3) {
        return state.getValue(AGE) < 4 || state.getValue(AGE) > 5 && state.getValue(AGE) < 8;
    }

    @Override
    public void growCrops(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state) {
        int newAge = this.getAge(state) + 1; //this.getBonemealAgeIncrease(level);
        int maxAge = this.getMaxAge();
        if (this.getAge(state) > 5) {
            maxAge += 4;
        }
        if (newAge > maxAge) {
            newAge = maxAge;
        }

        BlockState above = level.getBlockState(pos.above());
        BlockState below = level.getBlockState(pos.below());

        if (above.getBlock() instanceof CornCropBlock) {
            level.setBlock(pos.above(), this.getStateForAge(newAge + 4), 2);
        } else if (below.getBlock() instanceof CornCropBlock) {
            level.setBlock(pos.below(), this.getStateForAge(newAge - 4), 2);
        }
        if (this.getAge(state) >= 1 && level.getBlockState(pos.above()).is(Blocks.AIR) && !level.getBlockState(pos.below()).is(this)) {
            level.setBlock(pos.above(), this.getStateForAge(newAge + 4), 2);
        }

        level.setBlock(pos, this.getStateForAge(newAge), 2);

    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult use(BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        int age = state.getValue(AGE);
        boolean reachedCornAge = age == 5 || age == 9;
        if (!reachedCornAge && player.getItemInHand(interactionHand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (reachedCornAge) {
            ItemStack stack = player.getItemInHand(interactionHand);
            if (stack.getItem() instanceof KnifeItem) {
                if (level.getBlockState(pos.above()).getBlock() instanceof CornCropBlock) {
                    use(level, state, pos, pos.above(), +4, player, stack, interactionHand);
                } else if (level.getBlockState(pos.below()).getBlock() instanceof CornCropBlock) {
                    use(level, state, pos, pos.below(), -4, player, stack, interactionHand);
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return super.use(state, level, pos, player, interactionHand, blockHitResult);
    }

    private void use(Level level, BlockState state, BlockPos pos, BlockPos posNearby, int age, Player player, ItemStack stack, InteractionHand interactionHand) {
        int randomAmountCrop = 3 + level.random.nextInt(2);
        int newCropAge = state.getValue(AGE) - 1;
        BlockState newBlockState = state.setValue(AGE, newCropAge);

        level.setBlock(posNearby, this.getStateForAge(newCropAge + age), 2);
        level.setBlock(pos, newBlockState, 2);

        level.gameEvent(GameEvent.BLOCK_CHANGE, posNearby, GameEvent.Context.of(player, this.getStateForAge(newCropAge + age)));
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newBlockState));

        popResource(level, pos, new ItemStack(ItemRegistry.CORN.get(), randomAmountCrop));
        level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
        stack.hurtAndBreak(1, player, (livingEntity) -> livingEntity.broadcastBreakEvent(interactionHand));
    }

}
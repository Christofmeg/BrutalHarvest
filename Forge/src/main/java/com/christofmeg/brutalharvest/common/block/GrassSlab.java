package com.christofmeg.brutalharvest.common.block;

import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.lighting.LightEngine;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class GrassSlab extends GrassBlock implements SimpleWaterloggedBlock {

    public static final EnumProperty<SlabType> TYPE;
    public static final BooleanProperty WATERLOGGED;
    protected static final VoxelShape BOTTOM_AABB;
    protected static final VoxelShape TOP_AABB;

    public GrassSlab(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(TYPE, SlabType.BOTTOM).setValue(WATERLOGGED, false).setValue(SNOWY, false));
    }

    private static boolean isSnowySetting(BlockState pState) {
        return pState.is(BlockTags.SNOW);
    }

    @SuppressWarnings("deprecation")
    private static boolean canBeGrass(BlockState pState, LevelReader pLevelReader, BlockPos pPos) {
        BlockPos blockpos = pPos.above();
        BlockState blockstate = pLevelReader.getBlockState(blockpos);
        if (blockstate.is(Blocks.SNOW) && blockstate.getValue(SnowLayerBlock.LAYERS) == 1) {
            return true;
        } else if (blockstate.getFluidState().getAmount() == 8) {
            return false;
        } else if (blockstate.isSolid()) {
            int i = LightEngine.getLightBlockInto(pLevelReader, pState, pPos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(pLevelReader, blockpos));
            return i < pLevelReader.getMaxLightLevel();
        }
        else return true;
    }

    private static boolean canPropagate(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.above();
        return canBeGrass(pState, pLevel, pPos) && !pLevel.getFluidState(blockpos).is(FluidTags.WATER);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(@NotNull BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom) {
        if (!canBeGrass(pState, pLevel, pPos)) {
            if (!pLevel.isAreaLoaded(pPos, 1)) {
                return;
            }
            pLevel.setBlockAndUpdate(pPos, BlockRegistry.DIRT_SLAB.get().withPropertiesOf(pState));
        } else {
            if (!pLevel.isAreaLoaded(pPos, 3)) {
                return;
            }

            if (pLevel.getMaxLocalRawBrightness(pPos.above()) >= 9) {
                for(int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(5) - 3, pRandom.nextInt(3) - 1);
                    if (pLevel.getBlockState(blockpos).is(BlockRegistry.DIRT_SLAB.get()) && canPropagate(pState, pLevel, blockpos)) {
                        pLevel.setBlockAndUpdate(blockpos, this.withPropertiesOf(pState).setValue(SlabBlock.TYPE, pLevel.getBlockState(blockpos).getValue(SlabBlock.TYPE)).setValue(SNOWY, pLevel.getBlockState(blockpos.above()).is(Blocks.SNOW)));
                    } else if (pLevel.getBlockState(blockpos).is(Blocks.DIRT) && canPropagate(Blocks.GRASS_BLOCK.defaultBlockState(), pLevel, blockpos)) {
                        pLevel.setBlockAndUpdate(blockpos, Blocks.GRASS_BLOCK.defaultBlockState().setValue(SNOWY, pLevel.getBlockState(blockpos.above()).is(Blocks.SNOW)));
                    }
                }
            }
        }
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, @NotNull RandomSource randomSource, BlockPos blockPos, @NotNull BlockState blockState) {
        BlockPos above = blockPos.above();
        Block grassBlock = Blocks.GRASS;
        Optional<Holder.Reference<PlacedFeature>> $$6 = serverLevel.registryAccess().registryOrThrow(Registries.PLACED_FEATURE).getHolder(VegetationPlacements.GRASS_BONEMEAL);

        label49:
        for(int i = 0; i < 128; ++i) {
            BlockPos $$8 = above;

            for(int j = 0; j < i / 16; ++j) {
                $$8 = $$8.offset(randomSource.nextInt(3) - 1, (randomSource.nextInt(3) - 1) * randomSource.nextInt(3) / 2, randomSource.nextInt(3) - 1);
                if (!serverLevel.getBlockState($$8.below()).is(this) || serverLevel.getBlockState($$8).isCollisionShapeFullBlock(serverLevel, $$8)) {
                    continue label49;
                }
            }

            BlockState $$10 = serverLevel.getBlockState($$8);
            if (randomSource.nextInt(10) == 0) {
                if ($$10.is(grassBlock) || $$10.is(BlockRegistry.GRASS_SLAB.get())) {
                    ((BonemealableBlock)$$10).performBonemeal(serverLevel, randomSource, $$8, $$10);
                }
            }

            if ($$10.isAir()) {
                Holder<?> $$13;
                if (randomSource.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> $$11 = serverLevel.getBiome($$8).value().getGenerationSettings().getFlowerFeatures();
                    if ($$11.isEmpty()) {
                        continue;
                    }

                    $$13 = ((RandomPatchConfiguration) $$11.get(0).config()).feature();
                } else {
                    if ($$6.isEmpty()) {
                        continue;
                    }

                    $$13 = $$6.get();
                }

                ((PlacedFeature)$$13.value()).place(serverLevel, serverLevel.getChunkSource().getGenerator(), randomSource, $$8);
            }
        }

    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean useShapeForLightOcclusion(BlockState pState) {
        return pState.getValue(TYPE) != SlabType.DOUBLE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(TYPE, WATERLOGGED, SNOWY);
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull VoxelShape getShape(BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        SlabType $$4 = pState.getValue(TYPE);
        switch ($$4) {
            case DOUBLE -> {
                return Shapes.block();
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
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos pos = pContext.getClickedPos();
        BlockState blockState = pContext.getLevel().getBlockState(pos);
        BlockState above = pContext.getLevel().getBlockState(pContext.getClickedPos().above());
        if (blockState.is(this)) {
            return blockState.setValue(TYPE, SlabType.DOUBLE).setValue(WATERLOGGED, false).setValue(SNOWY, isSnowySetting(above));
        } else {
            FluidState $$3 = pContext.getLevel().getFluidState(pos);
            BlockState $$4 = this.defaultBlockState().setValue(TYPE, SlabType.BOTTOM).setValue(WATERLOGGED, $$3.getType() == Fluids.WATER).setValue(SNOWY, isSnowySetting(above));
            Direction $$5 = pContext.getClickedFace();
            return $$5 != Direction.DOWN && ($$5 == Direction.UP || !(pContext.getClickLocation().y - (double)pos.getY() > 0.5)) ? $$4 : $$4.setValue(TYPE, SlabType.TOP);
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
        }

        return pFacing == Direction.UP ? pState.setValue(SNOWY, isSnowySetting(pFacingState)) : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    static {
        TYPE = BlockStateProperties.SLAB_TYPE;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        BOTTOM_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
        TOP_AABB = Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);
    }

}

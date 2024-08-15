package com.christofmeg.brutalharvest.common.world.tree;

import com.christofmeg.brutalharvest.common.block.RubberLogGeneratedBlock;
import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import com.christofmeg.brutalharvest.common.init.TrunkPlacerTypeRegistry;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class RubberTrunkPlacer extends TrunkPlacer {
    public static final Codec<RubberTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> trunkPlacerParts(instance).apply(instance, RubberTrunkPlacer::new));

    private final List<Direction> directions = new ArrayList<>();

    public RubberTrunkPlacer(int p_70248_, int p_70249_, int p_70250_) {
        super(p_70248_, p_70249_, p_70250_);
        directions.add(Direction.NORTH);
        directions.add(Direction.SOUTH);
        directions.add(Direction.EAST);
        directions.add(Direction.WEST);
    }

    protected @NotNull TrunkPlacerType<?> type() {
        return TrunkPlacerTypeRegistry.RUBBER_TRUNK_PLACER.get();
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NotNull LevelSimulatedReader level, @NotNull BiConsumer<BlockPos, BlockState> blockGetter, @NotNull RandomSource randomSource, int height, BlockPos pos, @NotNull TreeConfiguration treeConfiguration) {
        setDirtAt(level, blockGetter, randomSource, pos.below(), treeConfiguration);

        for(int i = 0; i < height; ++i) {
            if (randomSource.nextInt(height) == 0) {
                blockGetter.accept(pos.above(i), BlockRegistry.RUBBER_LOG_GENERATED.get().defaultBlockState().setValue(RubberLogGeneratedBlock.OPEN, true).setValue(RubberLogGeneratedBlock.FACING, directions.get(randomSource.nextInt(directions.size()))));
            }
            this.placeLog(level, blockGetter, randomSource, pos.above(i), treeConfiguration);
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pos.above(height), 0, false));
    }
}

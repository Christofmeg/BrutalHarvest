package com.christofmeg.brutalharvest.common.world.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RubberTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource randomSource, boolean b) {
        return BrutalConfiguredFeatures.RUBBER_KEY;
    }

}

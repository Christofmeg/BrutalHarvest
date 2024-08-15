package com.christofmeg.brutalharvest.common.world.tree;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class BrutalPlacedFeatures {

    public static final ResourceKey<PlacedFeature> RUBBER_PLACED_KEY = registerKey("rubber_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, RUBBER_PLACED_KEY, configuredFeatures.getOrThrow(BrutalConfiguredFeatures.RUBBER_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1f, 1), //10 % chance to spawn 1 tree in a forge:is_hot/overworld biome
                        BlockRegistry.RUBBER_SAPLING.get()));

    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(CommonConstants.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

}

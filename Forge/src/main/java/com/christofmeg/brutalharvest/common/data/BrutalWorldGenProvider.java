package com.christofmeg.brutalharvest.common.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.world.tree.BrutalBiomeModifiers;
import com.christofmeg.brutalharvest.common.world.tree.BrutalConfiguredFeatures;
import com.christofmeg.brutalharvest.common.world.tree.BrutalPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class BrutalWorldGenProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, BrutalConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, BrutalPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, BrutalBiomeModifiers::bootstrap);

    public BrutalWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(CommonConstants.MOD_ID));
    }

}

package com.christofmeg.brutalharvest.common.init;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.world.tree.RubberTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;

public class TrunkPlacerTypeRegistry {

    public static void init(@Nonnull IEventBus modEventBus) {
        TRUNK_PLACER.register(modEventBus);
    }

    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, CommonConstants.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<?>> RUBBER_TRUNK_PLACER = TRUNK_PLACER.register("rubber_trunk_placer", () -> new TrunkPlacerType<>(RubberTrunkPlacer.CODEC));

}

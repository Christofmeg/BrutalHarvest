package com.christofmeg.brutalharvest;

import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import com.christofmeg.brutalharvest.common.init.CreativeModeTabRegistry;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
@Mod(CommonConstants.MOD_ID)
public class BrutalHarvest {

    public BrutalHarvest() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        init(bus);
    }

    private void init(@Nonnull IEventBus modEventBus) {
        MinecraftForge.EVENT_BUS.register(this);
        BlockRegistry.init(modEventBus);
        ItemRegistry.init(modEventBus);
        CreativeModeTabRegistry.REGISTRY.register(modEventBus);
    }

}

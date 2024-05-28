package com.christofmeg.brutalharvest.common.event;

import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetupEvent {

    @SubscribeEvent
    public static void commonSetupEvent(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.UNRIPE_TOMATO.get(), 0.30F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.TOMATO.get(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.ROTTEN_TOMATO.get(), 0.85F);

            ComposterBlock.COMPOSTABLES.put(ItemRegistry.LETTUCE.get(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.SLICED_LETTUCE.get(), 0.50F);

            ComposterBlock.COMPOSTABLES.put(ItemRegistry.CORN.get(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.CORN_KERNEL.get(), 0.30F);

            ComposterBlock.COMPOSTABLES.put(ItemRegistry.CUCUMBER.get(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.CUCUMBER_SLICE.get(), 0.50F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.PICKLE.get(), 0.65F);

            ComposterBlock.COMPOSTABLES.put(ItemRegistry.TOMATO_SEEDS.get(), 0.30F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.LETTUCE_SEEDS.get(), 0.30F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.CORN_SEEDS.get(), 0.30F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.CUCUMBER_SEEDS.get(), 0.30F);
        });
    }

}

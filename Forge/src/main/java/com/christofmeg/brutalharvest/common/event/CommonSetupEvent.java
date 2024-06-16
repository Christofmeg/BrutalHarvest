package com.christofmeg.brutalharvest.common.event;

import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonSetupEvent {

    public void commonSetupEvent(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.UNRIPE_TOMATO.get(), 0.30F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.TOMATO.get(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.TOMATO_SLICE.get(), 0.30F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.ROTTEN_TOMATO.get(), 0.85F);

            ComposterBlock.COMPOSTABLES.put(ItemRegistry.LETTUCE.get(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.SLICED_LETTUCE.get(), 0.50F);

            ComposterBlock.COMPOSTABLES.put(ItemRegistry.CORN.get(), 0.65F);

            ComposterBlock.COMPOSTABLES.put(ItemRegistry.CUCUMBER.get(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.CUCUMBER_SLICES.get(), 0.50F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.PICKLE.get(), 0.65F);

            ComposterBlock.COMPOSTABLES.put(ItemRegistry.SUGAR_BEET.get(), 0.65F);

            ComposterBlock.COMPOSTABLES.put(ItemRegistry.TOMATO_SEEDS.get(), 0.30F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.LETTUCE_SEEDS.get(), 0.30F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.CORN_SEEDS.get(), 0.30F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.CUCUMBER_SEEDS.get(), 0.30F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.COTTON_SEEDS.get(), 0.30F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.SUGAR_BEET_SEEDS.get(), 0.30F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.RAPESEEDS.get(), 0.30F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.STRAWBERRY_SEEDS.get(), 0.30F);
            ComposterBlock.COMPOSTABLES.put(ItemRegistry.ONION_SEEDS.get(), 0.30F);
        });
    }

}

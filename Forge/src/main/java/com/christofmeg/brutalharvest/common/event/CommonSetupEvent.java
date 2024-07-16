package com.christofmeg.brutalharvest.common.event;

import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryObject;

public class CommonSetupEvent {

    public void commonSetupEvent(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            compost(ItemRegistry.UNRIPE_TOMATO, 0.30F);
            compost(ItemRegistry.TOMATO, 0.65F);
            compost(ItemRegistry.ROTTEN_TOMATO, 0.85F);
            compost(ItemRegistry.TOMATO_SLICE, 0.30F);

            compost(ItemRegistry.LETTUCE, 0.65F);
            compost(ItemRegistry.SLICED_LETTUCE, 0.50F);

            compost(ItemRegistry.CORN, 0.65F);

            compost(ItemRegistry.CUCUMBER, 0.65F);
            compost(ItemRegistry.CUCUMBER_SLICES, 0.50F);
            compost(ItemRegistry.PICKLE, 0.65F);

//            compost(ItemRegistry.ONION, 0.65F);

//            compost(ItemRegistry.CHILI_PEPPER, 0.65F);

            compost(ItemRegistry.TOMATO_SEEDS, 0.30F);
            compost(ItemRegistry.LETTUCE_SEEDS, 0.30F);
            compost(ItemRegistry.CORN_SEEDS, 0.30F);
            compost(ItemRegistry.CUCUMBER_SEEDS, 0.30F);
            compost(ItemRegistry.COTTON_SEEDS, 0.30F);
            compost(ItemRegistry.SUGAR_BEET_SEEDS, 0.30F);
            compost(ItemRegistry.RAPESEEDS, 0.30F);
//            compost(ItemRegistry.STRAWBERRY_SEEDS, 0.30F);
//            compost(ItemRegistry.ONION_SEEDS, 0.30F);
//            compost(ItemRegistry.CHILI_PEPPER_SEEDS, 0.30F);
        });
    }

    private float compost(RegistryObject<Item> item, float value) {
        return ComposterBlock.COMPOSTABLES.put(item.get(), value);
    }

}

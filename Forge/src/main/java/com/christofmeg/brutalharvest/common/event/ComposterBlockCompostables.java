package com.christofmeg.brutalharvest.common.event;

import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.registries.RegistryObject;

public class ComposterBlockCompostables {

    public static void registerCompostables() {
        compost(ItemRegistry.UNRIPE_TOMATO, 0.30F);
        compost(ItemRegistry.TOMATO, 0.65F);
        compost(ItemRegistry.ROTTEN_TOMATO, 0.85F);
        compost(ItemRegistry.TOMATO_SLICE, 0.30F);

        compost(ItemRegistry.LETTUCE, 0.65F);
        compost(ItemRegistry.SLICED_LETTUCE, 0.50F);

        compost(ItemRegistry.CORN, 0.65F);

        compost(ItemRegistry.CUCUMBER, 0.65F);
        compost(ItemRegistry.CUCUMBER_SLICE, 0.50F);
        compost(ItemRegistry.PICKLE, 0.65F);
/*
            compost(ItemRegistry.COFFEE_BEANS, 0.30F);
            compost(ItemRegistry.DRIED_COFFEE_BEANS, 0.30F);
            compost(ItemRegistry.COFFEE_POWDER, 0.30F);
*/
        compost(ItemRegistry.RAPESEED_BEANS, 0.50F);

        compost(ItemRegistry.SUGAR_BEET, 0.65F);

        compost(ItemRegistry.UNRIPE_STRAWBERRY, 0.45F);
        compost(ItemRegistry.STRAWBERRY, 0.65F);

//          compost(ItemRegistry.ONION, 0.65F);
/*
            compost(ItemRegistry.GREEN_CHILI_PEPPER, 0.85F);
            compost(ItemRegistry.YELLOW_CHILI_PEPPER, 0.85F);
            compost(ItemRegistry.RED_PEPPER_SEEDS, 0.65F);
*/
//          compost(ItemRegistry.COOKED_RICE, 0.40F);

        compost(ItemRegistry.TOMATO_SEEDS, 0.30F);
        compost(ItemRegistry.LETTUCE_SEEDS, 0.30F);
        compost(ItemRegistry.CORN_SEEDS, 0.30F);
        compost(ItemRegistry.CUCUMBER_SEEDS, 0.30F);
        compost(ItemRegistry.COTTON_SEEDS, 0.30F);
//          compost(ItemRegistry.COFFEE_CHERRY, 0.30F);
        compost(ItemRegistry.RAPESEEDS, 0.30F);
        compost(ItemRegistry.SUGAR_BEET_SEEDS, 0.30F);
        compost(ItemRegistry.STRAWBERRY_SEEDS, 0.30F);
//          compost(ItemRegistry.ONION_SEEDS, 0.30F);
//          compost(ItemRegistry.CHILI_PEPPER_SEEDS, 0.30F);
//          compost(ItemRegistry.RICE, 0.30F);

        compost(ItemRegistry.BLUEBERRY, 0.50F);

        compost(BlockRegistry.RUBBER_SAPLING.get().asItem(), 0.30F);
    }

    public static void compost(RegistryObject<Item> item, float value) {
        compost(item.get(), value);
    }

    public static void compost(Item item, float value) {
        synchronized (ComposterBlock.COMPOSTABLES) {
            ComposterBlock.COMPOSTABLES.put(item, value);
        }
    }

}

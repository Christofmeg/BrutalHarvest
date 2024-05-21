package com.christofmeg.brutalharvest.common.init;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.item.BrutalFoods;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS;

    public static final RegistryObject<Item> GREEN_TOMATO;
    public static final RegistryObject<Item> TOMATO;
    public static final RegistryObject<Item> ROTTEN_TOMATO;
    public static final RegistryObject<Item> TOMATO_SEEDS;

    public static final RegistryObject<Item> LETTUCE;
    public static final RegistryObject<Item> SLICED_LETTUCE;
    public static final RegistryObject<Item> LETTUCE_SEEDS;

    public static final RegistryObject<Item> CORN;
    public static final RegistryObject<Item> CORN_KERNEL;
    public static final RegistryObject<Item> CORN_SEEDS;

    public static final RegistryObject<Item> CUCUMBER;
    public static final RegistryObject<Item> CUCUMBER_SLICE;
    public static final RegistryObject<Item> PICKLE;
    public static final RegistryObject<Item> CUCUMBER_SEEDS;

    private ItemRegistry() {
    }

    public static void init(@Nonnull IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }

    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CommonConstants.MOD_ID);

        GREEN_TOMATO = ITEMS.register("green_tomato", () -> new Item(new Item.Properties().food(BrutalFoods.GREEN_TOMATO)));
        TOMATO = ITEMS.register("tomato", () -> new Item(new Item.Properties().food(BrutalFoods.TOMATO)));
        ROTTEN_TOMATO = ITEMS.register("rotten_tomato", () -> new Item(new Item.Properties().food(BrutalFoods.ROTTEN_TOMATO)));

        LETTUCE = ITEMS.register("lettuce", () -> new Item(new Item.Properties().food(BrutalFoods.LETTUCE)));
        SLICED_LETTUCE = ITEMS.register("sliced_lettuce", () -> new Item(new Item.Properties().food(BrutalFoods.SLICED_LETTUCE)));

        CORN = ITEMS.register("corn", () -> new Item(new Item.Properties().food(BrutalFoods.CORN)));
        CORN_KERNEL = ITEMS.register("corn_kernel", () -> new Item(new Item.Properties().food(BrutalFoods.CORN_KERNEL)));

        CUCUMBER = ITEMS.register("cucumber", () -> new Item(new Item.Properties().food(BrutalFoods.CUCUMBER)));
        CUCUMBER_SLICE = ITEMS.register("cucumber_slice", () -> new Item(new Item.Properties().food(BrutalFoods.CUT_CUCUMBER)));
        PICKLE = ITEMS.register("pickle", () -> new Item(new Item.Properties().food(BrutalFoods.PICKLES)));

        TOMATO_SEEDS = ITEMS.register("tomato_seeds", () -> new Item(new Item.Properties()));
        LETTUCE_SEEDS = ITEMS.register("lettuce_seeds", () -> new Item(new Item.Properties()));
        CORN_SEEDS = ITEMS.register("corn_seeds", () -> new Item(new Item.Properties()));
        CUCUMBER_SEEDS = ITEMS.register("cucumber_seeds", () -> new Item(new Item.Properties()));

    }

}
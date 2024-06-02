package com.christofmeg.brutalharvest.common.init;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.item.BrutalFoods;
import com.christofmeg.brutalharvest.common.item.BrutalTiers;
import com.christofmeg.brutalharvest.common.item.KnifeItem;
import com.christofmeg.brutalharvest.common.item.TomatoProjectileItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS;

    public static final RegistryObject<Item> UNRIPE_TOMATO;
    public static final RegistryObject<Item> TOMATO;
    public static final RegistryObject<Item> TOMATO_SLICE;
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

    public static final RegistryObject<Item> FLINT_KNIFE;
    public static final RegistryObject<Item> WOODEN_KNIFE;
    public static final RegistryObject<Item> STONE_KNIFE;
    public static final RegistryObject<Item> COPPER_KNIFE;
    public static final RegistryObject<Item> IRON_KNIFE;
    public static final RegistryObject<Item> GOLDEN_KNIFE;
    public static final RegistryObject<Item> DIAMOND_KNIFE;
    public static final RegistryObject<Item> NETHERITE_KNIFE;

    public static void init(@Nonnull IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }

    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CommonConstants.MOD_ID);

        UNRIPE_TOMATO = ITEMS.register("unripe_tomato", () -> new TomatoProjectileItem(new Item.Properties().food(BrutalFoods.UNRIPE_TOMATO)));
        TOMATO = ITEMS.register("tomato", () -> new TomatoProjectileItem(new Item.Properties().food(BrutalFoods.TOMATO)));
        ROTTEN_TOMATO = ITEMS.register("rotten_tomato", () -> new TomatoProjectileItem(new Item.Properties().food(BrutalFoods.ROTTEN_TOMATO)));
        TOMATO_SLICE = ITEMS.register("tomato_slice", () -> new Item(new Item.Properties().food(BrutalFoods.TOMATO_SLICE)));

        LETTUCE = ITEMS.register("lettuce", () -> new Item(new Item.Properties().food(BrutalFoods.LETTUCE)));
        SLICED_LETTUCE = ITEMS.register("sliced_lettuce", () -> new Item(new Item.Properties().food(BrutalFoods.SLICED_LETTUCE)));

        CORN = ITEMS.register("corn", () -> new Item(new Item.Properties().food(BrutalFoods.CORN)));
        CORN_KERNEL = ITEMS.register("corn_kernel", () -> new Item(new Item.Properties().food(BrutalFoods.CORN_KERNEL)));

        CUCUMBER = ITEMS.register("cucumber", () -> new Item(new Item.Properties().food(BrutalFoods.CUCUMBER)));
        CUCUMBER_SLICE = ITEMS.register("cucumber_slices", () -> new Item(new Item.Properties().food(BrutalFoods.CUT_CUCUMBER)));
        PICKLE = ITEMS.register("pickle", () -> new Item(new Item.Properties().food(BrutalFoods.PICKLES)));

        TOMATO_SEEDS = ITEMS.register("tomato_seeds", () -> new ItemNameBlockItem(BlockRegistry.TOMATO_PLANT.get(), new Item.Properties()));
        LETTUCE_SEEDS = ITEMS.register("lettuce_seeds", () -> new Item(new Item.Properties()));
        CORN_SEEDS = ITEMS.register("corn_seeds", () -> new Item(new Item.Properties()));
        CUCUMBER_SEEDS = ITEMS.register("cucumber_seeds", () -> new Item(new Item.Properties()));

        FLINT_KNIFE = ITEMS.register("flint_knife", () -> new KnifeItem(BrutalTiers.FLINT, 1, new Item.Properties()));
        WOODEN_KNIFE = ITEMS.register("wooden_knife", () -> new KnifeItem(Tiers.WOOD, 1, new Item.Properties()));
        STONE_KNIFE = ITEMS.register("stone_knife", () -> new KnifeItem(BrutalTiers.STONE, 1, new Item.Properties()));
        COPPER_KNIFE = ITEMS.register("copper_knife", () -> new KnifeItem(BrutalTiers.COPPER,  1, new Item.Properties()));
        IRON_KNIFE = ITEMS.register("iron_knife", () -> new KnifeItem(Tiers.IRON, 1, new Item.Properties()));
        GOLDEN_KNIFE = ITEMS.register("golden_knife", () -> new KnifeItem(Tiers.GOLD, 1, new Item.Properties()));
        DIAMOND_KNIFE = ITEMS.register("diamond_knife", () -> new KnifeItem(Tiers.DIAMOND, 1, new Item.Properties()));
        NETHERITE_KNIFE = ITEMS.register("netherite_knife", () -> new KnifeItem(Tiers.NETHERITE, 1, new Item.Properties()));

    }

}
package com.christofmeg.brutalharvest.common.init;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.item.*;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;

public class ItemRegistry {

    //TODO look at farmers delight cooking pot

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
    public static final RegistryObject<Item> CORN_SEEDS;

    public static final RegistryObject<Item> CUCUMBER;
    public static final RegistryObject<Item> CUCUMBER_SLICES;
    public static final RegistryObject<Item> PICKLE;
    public static final RegistryObject<Item> CUCUMBER_SEEDS;

    public static final RegistryObject<Item> COTTON;
    public static final RegistryObject<Item> COTTON_SEEDS;

    public static final RegistryObject<Item> SUGAR_BEET;
    public static final RegistryObject<Item> SUGAR_BEET_SEEDS;

    public static final RegistryObject<Item> RAPESEEDS;

    public static final RegistryObject<Item> STRAWBERRY_SEEDS;

    public static final RegistryObject<Item> ONION_SEEDS;

    public static final RegistryObject<Item> RUBBER_BUCKET;
    public static final RegistryObject<Item> RUBBER;

    public static final RegistryObject<Item> FLINT_KNIFE;
    public static final RegistryObject<Item> WOODEN_KNIFE;
    public static final RegistryObject<Item> STONE_KNIFE;
    public static final RegistryObject<Item> COPPER_KNIFE;
    public static final RegistryObject<Item> IRON_KNIFE;
    public static final RegistryObject<Item> GOLDEN_KNIFE;
    public static final RegistryObject<Item> DIAMOND_KNIFE;
    public static final RegistryObject<Item> NETHERITE_KNIFE;

    public static final RegistryObject<Item> STONE_SCYTHE;
    public static final RegistryObject<Item> COPPER_SCYTHE;
    public static final RegistryObject<Item> IRON_SCYTHE;
    public static final RegistryObject<Item> GOLDEN_SCYTHE;
    public static final RegistryObject<Item> DIAMOND_SCYTHE;
    public static final RegistryObject<Item> NETHERITE_SCYTHE;

    public static final RegistryObject<Item> SEED_SATCHEL;
    public static final RegistryObject<Item> FABRIC;

    public static final RegistryObject<Item> BLACK_FABRIC;
    public static final RegistryObject<Item> BLUE_FABRIC;
    public static final RegistryObject<Item> BROWN_FABRIC;
    public static final RegistryObject<Item> CYAN_FABRIC;
    public static final RegistryObject<Item> GRAY_FABRIC;
    public static final RegistryObject<Item> GREEN_FABRIC;
    public static final RegistryObject<Item> LIGHT_BLUE_FABRIC;
    public static final RegistryObject<Item> LIGHT_GRAY_FABRIC;
    public static final RegistryObject<Item> LIME_FABRIC;
    public static final RegistryObject<Item> MAGENTA_FABRIC;
    public static final RegistryObject<Item> ORANGE_FABRIC;
    public static final RegistryObject<Item> PINK_FABRIC;
    public static final RegistryObject<Item> PURPLE_FABRIC;
    public static final RegistryObject<Item> RED_FABRIC;
    public static final RegistryObject<Item> WHITE_FABRIC;
    public static final RegistryObject<Item> YELLOW_FABRIC;

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

        CUCUMBER = ITEMS.register("cucumber", () -> new Item(new Item.Properties().food(BrutalFoods.CUCUMBER)));
        CUCUMBER_SLICES = ITEMS.register("cucumber_slices", () -> new Item(new Item.Properties().food(BrutalFoods.CUCUMBER_SLICES)));
        PICKLE = ITEMS.register("pickle", () -> new Item(new Item.Properties().food(BrutalFoods.PICKLES)));

        COTTON = ITEMS.register("cotton", () -> new Item(new Item.Properties()));
        SUGAR_BEET = ITEMS.register("sugar_beet", () -> new Item(new Item.Properties().food(Foods.BEETROOT)));

        TOMATO_SEEDS = ITEMS.register("tomato_seeds", () -> new ItemNameBlockItem(BlockRegistry.TOMATO.get(), new Item.Properties()));
        LETTUCE_SEEDS = ITEMS.register("lettuce_seeds", () -> new Item(new Item.Properties()));
        CORN_SEEDS = ITEMS.register("corn_seeds", () -> new ItemNameBlockItem(BlockRegistry.CORN.get(), new Item.Properties()));
        CUCUMBER_SEEDS = ITEMS.register("cucumber_seeds", () -> new Item(new Item.Properties()));
        COTTON_SEEDS = ITEMS.register("cotton_seeds", () -> new ItemNameBlockItem(BlockRegistry.COTTON.get(), new Item.Properties()));
        RAPESEEDS = ITEMS.register("rapeseeds", () -> new Item(new Item.Properties()));
        SUGAR_BEET_SEEDS = ITEMS.register("sugar_beet_seeds", () -> new ItemNameBlockItem(BlockRegistry.SUGAR_BEET.get(), new Item.Properties()));
        STRAWBERRY_SEEDS = ITEMS.register("strawberry_seeds", () -> new Item(new Item.Properties()));
        ONION_SEEDS = ITEMS.register("onion_seeds", () -> new Item(new Item.Properties()));

        RUBBER_BUCKET = ITEMS.register("rubber_bucket", () -> new Item(new Item.Properties())); //TODO implement
        RUBBER = ITEMS.register("rubber", () -> new Item(new Item.Properties()));

        FLINT_KNIFE = ITEMS.register("flint_knife", () -> new KnifeItem(BrutalTiers.FLINT, 1, new Item.Properties()));
        WOODEN_KNIFE = ITEMS.register("wooden_knife", () -> new KnifeItem(Tiers.WOOD, 1, new Item.Properties()));
        STONE_KNIFE = ITEMS.register("stone_knife", () -> new KnifeItem(BrutalTiers.STONE, 1, new Item.Properties()));
        COPPER_KNIFE = ITEMS.register("copper_knife", () -> new KnifeItem(BrutalTiers.COPPER,  1, new Item.Properties()));
        IRON_KNIFE = ITEMS.register("iron_knife", () -> new KnifeItem(Tiers.IRON, 1, new Item.Properties()));
        GOLDEN_KNIFE = ITEMS.register("golden_knife", () -> new KnifeItem(Tiers.GOLD, 1, new Item.Properties()));
        DIAMOND_KNIFE = ITEMS.register("diamond_knife", () -> new KnifeItem(Tiers.DIAMOND, 1, new Item.Properties()));
        NETHERITE_KNIFE = ITEMS.register("netherite_knife", () -> new KnifeItem(Tiers.NETHERITE, 1, new Item.Properties()));

        STONE_SCYTHE = ITEMS.register("stone_scythe", () -> new ScytheItem(BrutalTiers.STONE, 1, new Item.Properties()));
        COPPER_SCYTHE = ITEMS.register("copper_scythe", () -> new ScytheItem(BrutalTiers.COPPER,  1, new Item.Properties()));
        IRON_SCYTHE = ITEMS.register("iron_scythe", () -> new ScytheItem(Tiers.IRON, 1, new Item.Properties()));
        GOLDEN_SCYTHE = ITEMS.register("golden_scythe", () -> new ScytheItem(Tiers.GOLD, 1, new Item.Properties()));
        DIAMOND_SCYTHE = ITEMS.register("diamond_scythe", () -> new ScytheItem(Tiers.DIAMOND, 1, new Item.Properties()));
        NETHERITE_SCYTHE = ITEMS.register("netherite_scythe", () -> new ScytheItem(Tiers.NETHERITE, 1, new Item.Properties()));

        SEED_SATCHEL = ITEMS.register("seed_satchel", () -> new Item(new Item.Properties()));
        FABRIC = ITEMS.register("fabric", () -> new Item(new Item.Properties()));

        BLACK_FABRIC = ITEMS.register("black" + "_" + "fabric", () -> new Item(new Item.Properties()));
        BLUE_FABRIC = ITEMS.register("blue" + "_" + "fabric", () -> new Item(new Item.Properties()));
        BROWN_FABRIC = ITEMS.register("brown" + "_" + "fabric", () -> new Item(new Item.Properties()));
        CYAN_FABRIC = ITEMS.register("cyan" + "_" + "fabric", () -> new Item(new Item.Properties()));
        GRAY_FABRIC = ITEMS.register("gray" + "_" + "fabric", () -> new Item(new Item.Properties()));
        GREEN_FABRIC = ITEMS.register("green" + "_" + "fabric", () -> new Item(new Item.Properties()));
        LIGHT_BLUE_FABRIC = ITEMS.register("light_blue" + "_" + "fabric", () -> new Item(new Item.Properties()));
        LIGHT_GRAY_FABRIC = ITEMS.register("light_gray" + "_" + "fabric", () -> new Item(new Item.Properties()));
        LIME_FABRIC = ITEMS.register("lime" + "_" + "fabric", () -> new Item(new Item.Properties()));
        MAGENTA_FABRIC = ITEMS.register("magenta" + "_" + "fabric", () -> new Item(new Item.Properties()));
        ORANGE_FABRIC = ITEMS.register("orange" + "_" + "fabric", () -> new Item(new Item.Properties()));
        PINK_FABRIC = ITEMS.register("pink" + "_" + "fabric", () -> new Item(new Item.Properties()));
        PURPLE_FABRIC = ITEMS.register("purple" + "_" + "fabric", () -> new Item(new Item.Properties()));
        RED_FABRIC = ITEMS.register("red" + "_" + "fabric", () -> new Item(new Item.Properties()));
        WHITE_FABRIC = ITEMS.register("white" + "_" + "fabric", () -> new Item(new Item.Properties()));
        YELLOW_FABRIC = ITEMS.register("yellow" + "_" + "fabric", () -> new Item(new Item.Properties()));

    }

}
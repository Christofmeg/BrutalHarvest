package com.christofmeg.brutalharvest.common.init;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.item.*;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
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
    public static final RegistryObject<Item> CUCUMBER_SLICE;
    public static final RegistryObject<Item> PICKLE;
    public static final RegistryObject<Item> CUCUMBER_SEEDS;

    public static final RegistryObject<Item> COTTON;
    public static final RegistryObject<Item> COTTON_SEEDS;

    public static final RegistryObject<Item> RAPESEED_BEANS;
    public static final RegistryObject<Item> RAPESEEDS;
    public static final RegistryObject<Item> RAPESEED_OIL;

    public static final RegistryObject<Item> SUGAR_BEET;
    public static final RegistryObject<Item> SUGAR_BEET_SEEDS;

    public static final RegistryObject<Item> UNRIPE_STRAWBERRY;
    public static final RegistryObject<Item> STRAWBERRY;
    public static final RegistryObject<Item> STRAWBERRY_SEEDS;

    public static final RegistryObject<Item> BLUEBERRY;

    // ALL BELOW IN 2.0 OR 3.0
    // public static final RegistryObject<Item> ONION_SEEDS;
    // public static final RegistryObject<Item> ONION;

    // public static final RegistryObject<Item> CHILI_PEPPER_SEEDS;
    // public static final RegistryObject<Item> CHILI_PEPPER;

    // public static final RegistryObject<Item> LOBSTER;
    // public static final RegistryObject<Item> RAW_LOBSTER;

    // public static final RegistryObject<Item> SUSHI;
    // public static final RegistryObject<Item> CUCUMBER_SUSHI;
    // public static final RegistryObject<Item> LOBSTER_SUSHI;

//    public static final RegistryObject<Item> RICE;
//    public static final RegistryObject<Item> RICE_SEEDS;

    public static final RegistryObject<Item> FRIED_EGG;
    public static final RegistryObject<Item> SCRAMBLED_EGG;
    public static final RegistryObject<Item> BOILED_EGG;

    public static final RegistryObject<Item> HONEY_TOAST;
    public static final RegistryObject<Item> STRAWBERRY_TOAST;
    public static final RegistryObject<Item> BLUEBERRY_TOAST;

    public static final RegistryObject<Item> HONEY_JAR;
    public static final RegistryObject<Item> STRAWBERRY_JAM;
    public static final RegistryObject<Item> BLUEBERRY_JAM;

    public static final RegistryObject<Item> FRIED_EGG_TOAST;
    public static final RegistryObject<Item> SCRAMBLED_EGG_TOAST;
    public static final RegistryObject<Item> BOILED_EGG_TOAST;

    public static final RegistryObject<Item> TOAST_LOAF;
    public static final RegistryObject<Item> TOAST;
    public static final RegistryObject<Item> TOAST_SLICE;

    public static final RegistryObject<Item> POPCORN;

    public static final RegistryObject<Item> FLOUR;
    public static final RegistryObject<Item> DOUGH;
    public static final RegistryObject<Item> TOMATO_DOUGH;

//    public static final RegistryObject<Item> RUBBER_BUCKET;
//    public static final RegistryObject<Item> RUBBER;

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
    public static final RegistryObject<Item> JAR;
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

    public static final RegistryObject<Item> GARDENERS_HAT;
    public static final RegistryObject<Item> CHEFS_HAT;
    public static final RegistryObject<Item> BLUE_DUNGAREE;
    public static final RegistryObject<Item> OLD_DUNGAREE;
    public static final RegistryObject<Item> BOOTS;
    public static final RegistryObject<Item> CHEFS_APRON;

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

        STRAWBERRY = ITEMS.register("strawberry", () -> new Item(new Item.Properties().food(BrutalFoods.STRAWBERRY)));
        UNRIPE_STRAWBERRY = ITEMS.register("unripe_strawberry", () -> new Item(new Item.Properties().food(BrutalFoods.UNRIPE_STRAWBERRY)));
        BLUEBERRY = ITEMS.register("blueberry", () -> new ItemNameBlockItem(BlockRegistry.BLUEBERRY.get(), new Item.Properties().food(BrutalFoods.BLUEBERRY)));

        CORN = ITEMS.register("corn", () -> new Item(new Item.Properties().food(BrutalFoods.CORN)));

        CUCUMBER = ITEMS.register("cucumber", () -> new Item(new Item.Properties().food(BrutalFoods.CUCUMBER)));
        CUCUMBER_SLICE = ITEMS.register("cucumber_slice", () -> new Item(new Item.Properties().food(BrutalFoods.CUCUMBER_SLICE)));
        PICKLE = ITEMS.register("pickle", () -> new Item(new Item.Properties().food(BrutalFoods.PICKLE)));

        COTTON = ITEMS.register("cotton", () -> new Item(new Item.Properties()));

        RAPESEED_BEANS = ITEMS.register("rapeseed_beans", () -> new Item(new Item.Properties()));
        RAPESEED_OIL = ITEMS.register("rapeseed_oil", () -> new Item(new Item.Properties()));

        SUGAR_BEET = ITEMS.register("sugar_beet", () -> new Item(new Item.Properties().food(Foods.BEETROOT)));
        // ONION = ITEMS.register("onion", () -> new Item(new Item.Properties().food(BrutalFoods.ONION)));
        // CHILI_PEPPER = ITEMS.register("chili_pepper", () -> new Item(new Item.Properties().food(BrutalFoods.CHILI_PEPPER)));

        TOMATO_SEEDS = ITEMS.register("tomato_seeds", () -> new ItemNameBlockItem(BlockRegistry.TOMATO.get(), new Item.Properties()));
        LETTUCE_SEEDS = ITEMS.register("lettuce_seeds", () -> new ItemNameBlockItem(BlockRegistry.LETTUCE.get(), new Item.Properties()));
        CORN_SEEDS = ITEMS.register("corn_seeds", () -> new ItemNameBlockItem(BlockRegistry.CORN.get(), new Item.Properties()));
        CUCUMBER_SEEDS = ITEMS.register("cucumber_seeds", () -> new ItemNameBlockItem(BlockRegistry.CUCUMBER.get(), new Item.Properties()));
        COTTON_SEEDS = ITEMS.register("cotton_seeds", () -> new ItemNameBlockItem(BlockRegistry.COTTON.get(), new Item.Properties()));
        RAPESEEDS = ITEMS.register("rapeseeds", () -> new ItemNameBlockItem(BlockRegistry.RAPESEED.get(), new Item.Properties()));
        STRAWBERRY_SEEDS = ITEMS.register("strawberry_seeds", () -> new ItemNameBlockItem(BlockRegistry.STRAWBERRY.get(), new Item.Properties()));
        SUGAR_BEET_SEEDS = ITEMS.register("sugar_beet_seeds", () -> new ItemNameBlockItem(BlockRegistry.SUGAR_BEET.get(), new Item.Properties()));

        // ALL BELOW IN 2.0 OR 3.0
        // ONION_SEEDS = ITEMS.register("onion_seeds", () -> new Item(new Item.Properties()));
        // CHILI_PEPPER_SEEDS = ITEMS.register("chili_pepper_seeds", () -> new Item(new Item.Properties()));

        // LOBSTER = ITEMS.register("lobster", () -> new Item(new Item.Properties().food(BrutalFoods.LOBSTER)));
        // RAW_LOBSTER = ITEMS.register("raw_lobster", () -> new Item(new Item.Properties().food(BrutalFoods.RAW_LOBSTER)));

        // SUSHI = ITEMS.register("sushi", () -> new Item(new Item.Properties().food(BrutalFoods.SUSHI)));
        // CUCUMBER_SUSHI = ITEMS.register("cucumber_sushi", () -> new Item(new Item.Properties().food(BrutalFoods.CUCUMBER_SUSHI)));
        // LOBSTER_SUSHI = ITEMS.register("lobster_sushi", () -> new Item(new Item.Properties().food(BrutalFoods.LOBSTER_SUSHI)));

//        RICE = ITEMS.register("rice", () -> new Item(new Item.Properties()));
//        RICE_SEEDS = ITEMS.register("rice_seeds", () -> new ItemNameBlockItem(BlockRegistry.SUGAR_BEET.get(), new Item.Properties()));

        FRIED_EGG = ITEMS.register("fried_egg", () -> new Item(new Item.Properties().food(BrutalFoods.FRIED_EGG)));
        SCRAMBLED_EGG = ITEMS.register("scrambled_egg", () -> new Item(new Item.Properties().food(BrutalFoods.SCRAMBLED_EGG)));
        BOILED_EGG = ITEMS.register("boiled_egg", () -> new Item(new Item.Properties().food(BrutalFoods.BOILED_EGG)));

        HONEY_TOAST = ITEMS.register("honey_toast", () -> new Item(new Item.Properties().food(BrutalFoods.HONEY_TOAST)));
        STRAWBERRY_TOAST = ITEMS.register("strawberry_toast", () -> new Item(new Item.Properties().food(BrutalFoods.STRAWBERRY_TOAST)));
        BLUEBERRY_TOAST = ITEMS.register("blueberry_toast", () -> new Item(new Item.Properties().food(BrutalFoods.BLUEBERRY_TOAST)));

        SEED_SATCHEL = ITEMS.register("seed_satchel", () -> new Item(new Item.Properties()));
        JAR = ITEMS.register("jar", () -> new Item(new Item.Properties()));

        HONEY_JAR = ITEMS.register("honey_jar", () -> new Item(new Item.Properties().food(BrutalFoods.HONEY_JAR).craftRemainder(ItemRegistry.JAR.get())));
        STRAWBERRY_JAM = ITEMS.register("strawberry_jam", () -> new Item(new Item.Properties().food(BrutalFoods.STRAWBERRY_JAM).craftRemainder(ItemRegistry.JAR.get())));
        BLUEBERRY_JAM = ITEMS.register("blueberry_jam", () -> new Item(new Item.Properties().food(BrutalFoods.BLUEBERRY_JAM).craftRemainder(ItemRegistry.JAR.get())));

        FRIED_EGG_TOAST = ITEMS.register("fried_egg_toast", () -> new Item(new Item.Properties().food(BrutalFoods.FRIED_EGG_TOAST)));
        SCRAMBLED_EGG_TOAST = ITEMS.register("scrambled_egg_toast", () -> new Item(new Item.Properties().food(BrutalFoods.SCRAMBLED_EGG_TOAST)));
        BOILED_EGG_TOAST = ITEMS.register("boiled_egg_toast", () -> new Item(new Item.Properties().food(BrutalFoods.BOILED_EGG_TOAST)));

        TOAST_LOAF = ITEMS.register("toast_loaf", () -> new Item(new Item.Properties().food(BrutalFoods.TOAST_LOAF)));
        TOAST = ITEMS.register("toast", () -> new Item(new Item.Properties().food(BrutalFoods.TOAST)));
        TOAST_SLICE = ITEMS.register("toast_slice", () -> new Item(new Item.Properties().food(BrutalFoods.TOAST_SLICE)));

        POPCORN = ITEMS.register("popcorn", () -> new Item(new Item.Properties().food(BrutalFoods.POPCORN).craftRemainder(Items.BOWL)));

        FLOUR = ITEMS.register("flour", () -> new Item(new Item.Properties()));
        DOUGH = ITEMS.register("dough", () -> new Item(new Item.Properties()));
        TOMATO_DOUGH = ITEMS.register("tomato_dough", () -> new Item(new Item.Properties()));

//        RUBBER_BUCKET = ITEMS.register("rubber_bucket", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET))); //TODO implement
//        RUBBER = ITEMS.register("rubber", () -> new Item(new Item.Properties()));

        FLINT_KNIFE = ITEMS.register("flint_knife", () -> new KnifeItem(BrutalTiers.FLINT, new Item.Properties()));
        WOODEN_KNIFE = ITEMS.register("wooden_knife", () -> new KnifeItem(Tiers.WOOD, new Item.Properties()));
        STONE_KNIFE = ITEMS.register("stone_knife", () -> new KnifeItem(BrutalTiers.STONE, new Item.Properties()));
        COPPER_KNIFE = ITEMS.register("copper_knife", () -> new KnifeItem(BrutalTiers.COPPER, new Item.Properties()));
        IRON_KNIFE = ITEMS.register("iron_knife", () -> new KnifeItem(Tiers.IRON, new Item.Properties()));
        GOLDEN_KNIFE = ITEMS.register("golden_knife", () -> new KnifeItem(Tiers.GOLD, new Item.Properties()));
        DIAMOND_KNIFE = ITEMS.register("diamond_knife", () -> new KnifeItem(Tiers.DIAMOND, new Item.Properties()));
        NETHERITE_KNIFE = ITEMS.register("netherite_knife", () -> new KnifeItem(Tiers.NETHERITE, new Item.Properties()));

        STONE_SCYTHE = ITEMS.register("stone_scythe", () -> new ScytheItem(BrutalTiers.STONE, 1, new Item.Properties()));
        COPPER_SCYTHE = ITEMS.register("copper_scythe", () -> new ScytheItem(BrutalTiers.COPPER, 1, new Item.Properties()));
        IRON_SCYTHE = ITEMS.register("iron_scythe", () -> new ScytheItem(Tiers.IRON, 2, new Item.Properties()));
        GOLDEN_SCYTHE = ITEMS.register("golden_scythe", () -> new ScytheItem(Tiers.GOLD, 2, new Item.Properties()));
        DIAMOND_SCYTHE = ITEMS.register("diamond_scythe", () -> new ScytheItem(Tiers.DIAMOND, 3, new Item.Properties()));
        NETHERITE_SCYTHE = ITEMS.register("netherite_scythe", () -> new ScytheItem(Tiers.NETHERITE, 3, new Item.Properties()));

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

        GARDENERS_HAT = ITEMS.register("gardeners_hat", () -> new GardenersHatItem(BrutalArmorMaterials.GARDENERS_HAT, ArmorItem.Type.HELMET, new Item.Properties()));
        CHEFS_HAT = ITEMS.register("chefs_hat", () -> new ChefsHatItem(BrutalArmorMaterials.CHEFS_HAT, ArmorItem.Type.HELMET, new Item.Properties()));
        BLUE_DUNGAREE = ITEMS.register("blue_dungaree", () -> new DungareeItem(BrutalArmorMaterials.BLUE_DUNGAREE, ArmorItem.Type.LEGGINGS, new Item.Properties(), "blue"));
        OLD_DUNGAREE = ITEMS.register("old_dungaree", () -> new DungareeItem(BrutalArmorMaterials.OLD_DUNGAREE, ArmorItem.Type.LEGGINGS, new Item.Properties(), "old"));
        BOOTS = ITEMS.register("boots", () -> new ArmorItem(BrutalArmorMaterials.BOOTS, ArmorItem.Type.BOOTS, new Item.Properties()));
        CHEFS_APRON = ITEMS.register("chefs_apron", () -> new ChefsApronItem(BrutalArmorMaterials.CHEFS_HAT, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    }

}
package com.christofmeg.brutalharvest.common.init;

import com.christofmeg.brutalharvest.CommonConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagRegistry {

    public static final class Blocks {
        @SuppressWarnings("unused")
        private static TagKey<Block> vanillaTag(final String path) {
            return BlockTags.create(new ResourceLocation("minecraft", path));
        }

        @SuppressWarnings("unused")
        private static TagKey<Block> modTag(final String path) {
            return BlockTags.create(new ResourceLocation(CommonConstants.MOD_ID, path));
        }

        @SuppressWarnings("unused")
        private static TagKey<Block> forgeTag(final String path) {
            return BlockTags.create(new ResourceLocation("forge", path));
        }

        public static TagKey<Block> NEEDS_FLINT_TOOL = forgeTag("needs_flint_tool");
        public static TagKey<Block> NEEDS_COPPER_TOOL = forgeTag("needs_copper_tool");

        public static TagKey<Block> AE2_GROWTH_ACCELERATABLE = BlockTags.create(new ResourceLocation("ae2", "growth_acceleratable"));

        public static TagKey<Block> RUBBER_LOGS = modTag("rubber_logs");

        public static TagKey<Block> MINEABLE_WITH_SCYTHE = forgeTag("mineable/scythe");

        public static TagKey<Block> SLABS_DIRT = forgeTag("slabs/dirt");

    }

    public static final class Items {
        @SuppressWarnings("unused")
        private static TagKey<Item> vanillaTag(final String path) {
            return ItemTags.create(new ResourceLocation("minecraft", path));
        }

        @SuppressWarnings("unused")
        private static TagKey<Item> modTag(final String path) {
            return ItemTags.create(new ResourceLocation(CommonConstants.MOD_ID, path));
        }

        @SuppressWarnings("unused")
        private static TagKey<Item> forgeTag(final String path) {
            return ItemTags.create(new ResourceLocation("forge", path));
        }

        public static TagKey<Item> TOMATO_SEEDS = forgeTag("seeds/tomato");
        public static TagKey<Item> LETTUCE_SEEDS = forgeTag("seeds/lettuce");
        public static TagKey<Item> CORN_SEEDS = forgeTag("seeds/corn");
        public static TagKey<Item> CUCUMBER_SEEDS = forgeTag("seeds/cucumber");
        public static TagKey<Item> COTTON_SEEDS = forgeTag("seeds/cotton");
        public static TagKey<Item> RAPESEEDS = forgeTag("seeds/rapeseeds");
        public static TagKey<Item> SUGAR_BEET_SEEDS = forgeTag("seeds/sugar_beet");
        public static TagKey<Item> STRAWBERRY_SEEDS = forgeTag("seeds/strawberry");
//        public static TagKey<Item> ONION_SEEDS = forgeTag("seeds/onion_seeds");
//        public static TagKey<Item> CHILI_PEPPER_SEEDS = forgeTag("seeds/chili_pepper_seeds");

        public static TagKey<Item> VEGETABLES = forgeTag("vegetables");
        public static TagKey<Item> TOMATO = forgeTag("vegetables/tomato");
        public static TagKey<Item> LETTUCE = forgeTag("vegetables/lettuce");
        public static TagKey<Item> CORN = forgeTag("vegetables/corn");
        public static TagKey<Item> CUCUMBER = forgeTag("vegetables/cucumber");
        public static TagKey<Item> BEETROOT = forgeTag("vegetables/beetroot");
        public static TagKey<Item> SUGAR_BEET = forgeTag("vegetables/sugar_beet");
//        public static TagKey<Item> ONION = forgeTag("vegetables/onion");
//        public static TagKey<Item> CHILI_PEPPER = forgeTag("vegetables/chili_pepper");

        public static TagKey<Item> FRUITS = forgeTag("fruits");
        public static TagKey<Item> STRAWBERRY = forgeTag("fruits/strawberry");
        public static TagKey<Item> UNRIPE_STRAWBERRY = forgeTag("fruits/unripe_strawberry");
        public static TagKey<Item> BLUEBERRY = forgeTag("fruits/blueberry");

        public static TagKey<Item> CROPS = forgeTag("crops");
        public static TagKey<Item> CROPS_TOMATO = forgeTag("crops/tomato");
        public static TagKey<Item> CROPS_LETTUCE = forgeTag("crops/lettuce");
        public static TagKey<Item> CROPS_CORN = forgeTag("crops/corn");
        public static TagKey<Item> CROPS_CUCUMBER = forgeTag("crops/cucumber");
        public static TagKey<Item> CROPS_COTTON = forgeTag("crops/cotton");
        public static TagKey<Item> CROPS_RAPESEED = forgeTag("crops/rapeseed");
        public static TagKey<Item> CROPS_SUGAR_BEET = forgeTag("crops/sugar_beet");
        public static TagKey<Item> CROPS_STRAWBERRY = forgeTag("crops/strawberry");
//        public static TagKey<Item> CROPS_ONION = forgeTag("crops/onion");
//        public static TagKey<Item> RICE = forgeTag("crops/rice");

        public static TagKey<Item> FOODS = forgeTag("foods");
//        public static TagKey<Item> LOBSTER = forgeTag("foods/lobster");
//        public static TagKey<Item> RAW_LOBSTER = forgeTag("foods/raw_lobster");
//        public static TagKey<Item> SUSHI = forgeTag("foods/sushi");
//        public static TagKey<Item> CUCUMBER_SUSHI = forgeTag("foods/cucumber_shushi");
//        public static TagKey<Item> LOBSTER_SUSHI = forgeTag("foods/lobster_sushi");
        public static TagKey<Item> FRIED_EGG = forgeTag("foods/fried_egg");
        public static TagKey<Item> SCRAMBLED_EGG = forgeTag("foods/scrambled_egg");
        public static TagKey<Item> BOILED_EGG = forgeTag("foods/boiled_egg");

        public static TagKey<Item> TOAST_HONEY = forgeTag("foods/toast_honey");
        public static TagKey<Item> TOAST_STRAWBERRY = forgeTag("foods/toast_strawberry");
        public static TagKey<Item> TOAST_BLUEBERRY = forgeTag("foods/toast_blueberry");

        public static TagKey<Item> HONEY_JAR = forgeTag("foods/honey_jar");
        public static TagKey<Item> STRAWBERRY_JAM = forgeTag("foods/strawberry_jam");
        public static TagKey<Item> BLUEBERRY_JAM = forgeTag("foods/blueberry_jam");

        public static TagKey<Item> TOAST_FRIED_EGG = forgeTag("foods/toast_fried_egg");
        public static TagKey<Item> TOAST_SCRAMBLED_EGG = forgeTag("foods/toast_scrambled_egg");
        public static TagKey<Item> TOAST_BOILED_EGG = forgeTag("foods/toast_boiled_egg");

        public static TagKey<Item> TOAST_LOAF = forgeTag("foods/toast_loaf");
        public static TagKey<Item> TOAST = forgeTag("foods/toast");
        public static TagKey<Item> TOAST_SLICE = forgeTag("foods/toast_slice");

        public static TagKey<Item> POPCORN = forgeTag("foods/popcorn");

        public static TagKey<Item> SALAD_INGREDIENTS = forgeTag("salad_ingredients");
        public static TagKey<Item> SALAD_INGREDIENTS_LETTUCE = forgeTag("salad_ingredients/lettuce");

        public static TagKey<Item> ITEMS = forgeTag("items");
        public static TagKey<Item> SEED_SATCHEL = forgeTag("items/seed_satchel");

        public static TagKey<Item> JAR = forgeTag("items/jar");

        public static TagKey<Item> FLOUR = forgeTag("items/flour");
        public static TagKey<Item> DOUGH = forgeTag("items/dough");
        public static TagKey<Item> TOMATO_DOUGH = forgeTag("items/tomato_dough");

        public static TagKey<Item> TOOLS = forgeTag("tools");
        public static TagKey<Item> KNIVES = forgeTag("tools/knives");
        public static TagKey<Item> FARMERS_DELIGHT_KNIVES = ItemTags.create(new ResourceLocation("farmersdelight", "tools/knives"));
        public static TagKey<Item> SCYTHES = forgeTag("tools/scythes");

        public static TagKey<Item> BUCKETS_WATER = forgeTag("buckets/water");
        public static TagKey<Item> BUCKETS_MILK = forgeTag("buckets/milk");
        public static TagKey<Item> BOTTLES_MILK = forgeTag("bottles/milk");
        public static TagKey<Item> MILK_MILK_BOTTLES = forgeTag("milk/milk_bottle");

        public static TagKey<Item> FABRICS = modTag("fabrics");
        public static TagKey<Item> FABRICS_COLORED = modTag("fabrics_colored");

        public static TagKey<Item> WOOLS_WHITE = forgeTag("wools/" + "white");
        public static TagKey<Item> WOOLS_ORANGE = forgeTag("wools/" + "orange");
        public static TagKey<Item> WOOLS_MAGENTA = forgeTag("wools/" + "magenta");
        public static TagKey<Item> WOOLS_LIGHT_BLUE = forgeTag("wools/" + "light_blue");
        public static TagKey<Item> WOOLS_YELLOW = forgeTag("wools/" + "yellow");
        public static TagKey<Item> WOOLS_LIME = forgeTag("wools/" + "lime");
        public static TagKey<Item> WOOLS_PINK = forgeTag("wools/" + "pink");
        public static TagKey<Item> WOOLS_GRAY = forgeTag("wools/" + "gray");
        public static TagKey<Item> WOOLS_LIGHT_GRAY = forgeTag("wools/" + "light_gray");
        public static TagKey<Item> WOOLS_CYAN = forgeTag("wools/" + "cyan");
        public static TagKey<Item> WOOLS_PURPLE = forgeTag("wools/" + "purple");
        public static TagKey<Item> WOOLS_BLUE = forgeTag("wools/" + "blue");
        public static TagKey<Item> WOOLS_BROWN = forgeTag("wools/" + "brown");
        public static TagKey<Item> WOOLS_GREEN = forgeTag("wools/" + "green");
        public static TagKey<Item> WOOLS_RED = forgeTag("wools/" + "red");
        public static TagKey<Item> WOOLS_BLACK = forgeTag("wools/" + "black");

        public static TagKey<Item> RUBBER_LOGS = modTag("rubber_logs");

        public static TagKey<Item> HIGH_TIER_HOES = ItemTags.create(new ResourceLocation("rightclickharvest", "high_tier_hoes"));
        public static TagKey<Item> MID_TIER_HOES = ItemTags.create(new ResourceLocation("rightclickharvest", "mid_tier_hoes"));
        public static TagKey<Item> LOW_TIER_HOES = ItemTags.create(new ResourceLocation("rightclickharvest", "low_tier_hoes"));

        public static TagKey<Item> SLABS_TILLABLE = modTag("slabs/tillable");
        public static TagKey<Item> BLOCKS_TILLABLE = modTag("blocks/tillable");

    }

}

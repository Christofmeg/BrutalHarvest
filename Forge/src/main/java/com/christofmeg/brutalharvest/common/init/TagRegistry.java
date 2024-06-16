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
        public static TagKey<Item> STRAWBERRY_SEEDS = forgeTag("seeds/strawberry_seeds");
        public static TagKey<Item> ONION_SEEDS = forgeTag("seeds/onion_seeds");

        public static TagKey<Item> VEGETABLES = forgeTag("vegetables");
        public static TagKey<Item> TOMATO = forgeTag("vegetables/tomato");
        public static TagKey<Item> LETTUCE = forgeTag("vegetables/lettuce");
        public static TagKey<Item> CORN = forgeTag("vegetables/corn");
        public static TagKey<Item> CUCUMBER = forgeTag("vegetables/cucumber");
        public static TagKey<Item> BEETROOT = forgeTag("vegetables/beetroot");
        public static TagKey<Item> SUGAR_BEET = forgeTag("vegetables/sugar_beet");
//        public static TagKey<Item> ONTION = forgeTag("vegetables/onion");

        public static TagKey<Item> CROPS = forgeTag("crops");
        public static TagKey<Item> CROPS_TOMATO = forgeTag("crops/tomato");
        public static TagKey<Item> CROPS_LETTUCE = forgeTag("crops/lettuce");
        public static TagKey<Item> CROPS_CORN = forgeTag("crops/corn");
        public static TagKey<Item> CROPS_CUCUMBER = forgeTag("crops/cucumber");
        public static TagKey<Item> CROPS_COTTON = forgeTag("crops/cotton");
        public static TagKey<Item> CROPS_SUGAR_BEET = forgeTag("crops/sugar_beet");
//        public static TagKey<Item> CROPS_ONION = forgeTag("crops/onion");

        public static TagKey<Item> SALAD_INGREDIENTS = forgeTag("salad_ingredients");
        public static TagKey<Item> SALAD_INGREDIENTS_LETTUCE = forgeTag("salad_ingredients/lettuce");

        public static TagKey<Item> TOOLS = forgeTag("tools");
        public static TagKey<Item> KNIVES = forgeTag("tools/knives");
        public static TagKey<Item> FARMERS_DELIGHT_KNIVES = ItemTags.create(new ResourceLocation("farmersdelight", "tools/knives"));
        public static TagKey<Item> SCYTHES = forgeTag("tools/scythes");

        public static TagKey<Item> BUCKETS_WATER = forgeTag("buckets/water");

    }

}

package com.christofmeg.brutalharvest.common.init;

import com.christofmeg.brutalharvest.CommonConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TagRegistry {

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

        public static TagKey<Item> VEGETABLES = forgeTag("vegetables");
        public static TagKey<Item> TOMATO = forgeTag("vegetables/tomato");
        public static TagKey<Item> LETTUCE = forgeTag("vegetables/lettuce");
        public static TagKey<Item> CORN= forgeTag("vegetables/corn");
        public static TagKey<Item> CUCUMBER = forgeTag("vegetables/cucumber");

        public static TagKey<Item> SALAD_INGREDIENTS = forgeTag("salad_ingredients");
        public static TagKey<Item> SALAD_INGREDIENTS_LETTUCE = forgeTag("salad_ingredients/lettuce");

    }

}

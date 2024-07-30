package com.christofmeg.brutalharvest.common.data;

import com.christofmeg.brutalharvest.common.data.base.BaseRecipeProvider;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import com.christofmeg.brutalharvest.common.init.TagRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class BrutalRecipeProvider extends BaseRecipeProvider {

    public BrutalRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        this.addShapedRecipes(consumer);
        this.addShapelessRecipes(consumer);
        this.addSmithingRecipes(consumer);
        this.addCookingRecipes(consumer);
    }

    private void addShapedRecipes(Consumer<FinishedRecipe> consumer) {
        knifeBuilder(ItemRegistry.FLINT_KNIFE.get(), Ingredient.of(Items.FLINT), "flint" + "_knife", consumer);
        knifeBuilder(ItemRegistry.WOODEN_KNIFE.get(), Ingredient.of(ItemTags.PLANKS), "wooden" + "_knife", consumer);
        knifeBuilder(ItemRegistry.STONE_KNIFE.get(), Ingredient.of(ItemTags.STONE_TOOL_MATERIALS), "stone" + "_knife", consumer);
        knifeBuilder(ItemRegistry.COPPER_KNIFE.get(), Ingredient.of(Tags.Items.INGOTS_COPPER), "copper" + "_knife", consumer);
        knifeBuilder(ItemRegistry.IRON_KNIFE.get(), Ingredient.of(Tags.Items.INGOTS_IRON), "iron" + "_knife", consumer);
        knifeBuilder(ItemRegistry.GOLDEN_KNIFE.get(), Ingredient.of(Tags.Items.INGOTS_GOLD), "golden" + "_knife", consumer);
        knifeBuilder(ItemRegistry.DIAMOND_KNIFE.get(), Ingredient.of(Tags.Items.GEMS_DIAMOND), "diamond" + "_knife", consumer);

        scytheBuilder(ItemRegistry.STONE_SCYTHE.get(), Ingredient.of(ItemTags.STONE_TOOL_MATERIALS), "stone" + "_scythe", consumer);
        scytheBuilder(ItemRegistry.COPPER_SCYTHE.get(), Ingredient.of(Tags.Items.INGOTS_COPPER), "copper" + "_scythe", consumer);
        scytheBuilder(ItemRegistry.IRON_SCYTHE.get(), Ingredient.of(Tags.Items.INGOTS_IRON), "iron" + "_scythe", consumer);
        scytheBuilder(ItemRegistry.GOLDEN_SCYTHE.get(), Ingredient.of(Tags.Items.INGOTS_GOLD), "golden" + "_scythe", consumer);
        scytheBuilder(ItemRegistry.DIAMOND_SCYTHE.get(), Ingredient.of(Tags.Items.GEMS_DIAMOND), "diamond" + "_scythe", consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.SEED_SATCHEL.get())
                .define('C', TagRegistry.Items.CROPS)
                .define('F', ItemRegistry.FABRIC.get())
                .pattern("FCF")
                .pattern("FFF")
                .unlockedBy("has_cotton", has(ItemRegistry.COTTON.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.SEED_SATCHEL.get())));

        woolRecipeBuilder(TagRegistry.Items.WOOLS_BLACK, Items.BLACK_BANNER, Items.BLACK_BED, Items.BLACK_CARPET, consumer);
        woolRecipeBuilder(TagRegistry.Items.WOOLS_BLUE, Items.BLUE_BANNER, Items.BLUE_BED, Items.BLUE_CARPET, consumer);
        woolRecipeBuilder(TagRegistry.Items.WOOLS_BROWN, Items.BROWN_BANNER, Items.BROWN_BED, Items.BROWN_CARPET, consumer);
        woolRecipeBuilder(TagRegistry.Items.WOOLS_CYAN, Items.CYAN_BANNER, Items.CYAN_BED, Items.CYAN_CARPET, consumer);
        woolRecipeBuilder(TagRegistry.Items.WOOLS_GRAY, Items.GRAY_BANNER, Items.GRAY_BED, Items.GRAY_CARPET, consumer);
        woolRecipeBuilder(TagRegistry.Items.WOOLS_GREEN, Items.GREEN_BANNER, Items.GREEN_BED, Items.GREEN_CARPET, consumer);
        woolRecipeBuilder(TagRegistry.Items.WOOLS_LIGHT_BLUE, Items.LIGHT_BLUE_BANNER, Items.LIGHT_BLUE_BED, Items.LIGHT_BLUE_CARPET, consumer);
        woolRecipeBuilder(TagRegistry.Items.WOOLS_LIGHT_GRAY, Items.LIGHT_GRAY_BANNER, Items.LIGHT_GRAY_BED, Items.LIGHT_GRAY_CARPET, consumer);
        woolRecipeBuilder(TagRegistry.Items.WOOLS_LIME, Items.LIME_BANNER, Items.LIME_BED, Items.LIME_CARPET, consumer);
        woolRecipeBuilder(TagRegistry.Items.WOOLS_MAGENTA, Items.MAGENTA_BANNER, Items.MAGENTA_BED, Items.MAGENTA_CARPET, consumer);
        woolRecipeBuilder(TagRegistry.Items.WOOLS_ORANGE, Items.ORANGE_BANNER, Items.ORANGE_BED, Items.ORANGE_CARPET, consumer);
        woolRecipeBuilder(TagRegistry.Items.WOOLS_PINK, Items.PINK_BANNER, Items.PINK_BED, Items.PINK_CARPET, consumer);
        woolRecipeBuilder(TagRegistry.Items.WOOLS_PURPLE, Items.PURPLE_BANNER, Items.PURPLE_BED, Items.PURPLE_CARPET, consumer);
        woolRecipeBuilder(TagRegistry.Items.WOOLS_RED, Items.RED_BANNER, Items.RED_BED, Items.RED_CARPET, consumer);
        woolRecipeBuilder(TagRegistry.Items.WOOLS_WHITE, Items.WHITE_BANNER, Items.WHITE_BED, Items.WHITE_CARPET, consumer);
        woolRecipeBuilder(TagRegistry.Items.WOOLS_YELLOW, Items.YELLOW_BANNER, Items.YELLOW_BED, Items.YELLOW_CARPET, consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.GARDENERS_HAT.get())
                .define('W', Tags.Items.CROPS_WHEAT)
                .pattern("WWW")
                .pattern("W W")
                .unlockedBy("has_wheat", has(Tags.Items.CROPS_WHEAT))
                .save(consumer, modLoc(getItemName(ItemRegistry.GARDENERS_HAT.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.CHEFS_HAT.get())
                .define('W', ItemRegistry.WHITE_FABRIC.get())
                .pattern("WWW")
                .pattern("W W")
                .unlockedBy("has_fabric", has(ItemRegistry.FABRIC.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.CHEFS_HAT.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.BLUE_DUNGAREE.get())
                .define('W', ItemRegistry.BLUE_FABRIC.get())
                .pattern("WWW")
                .pattern("W W")
                .pattern("W W")
                .unlockedBy("has_fabric", has(ItemRegistry.FABRIC.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.BLUE_DUNGAREE.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.OLD_DUNGAREE.get())
                .define('W', ItemRegistry.BROWN_FABRIC.get())
                .pattern("WWW")
                .pattern("W W")
                .pattern("W W")
                .unlockedBy("has_fabric", has(ItemRegistry.FABRIC.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.OLD_DUNGAREE.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.BOOTS.get())
                .define('W', ItemRegistry.BROWN_FABRIC.get())
                .pattern("W W")
                .pattern("W W")
                .unlockedBy("has_fabric", has(ItemRegistry.FABRIC.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.BOOTS.get())));


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.DOUGH.get())
                .define('F', ItemRegistry.FLOUR.get())
                .define('E', Items.EGG)
                .define('O', Items.WATER_BUCKET)
                .pattern("FF ")
                .pattern("EO ")
                .unlockedBy("has_flour", has(ItemRegistry.FLOUR.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.DOUGH.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.DOUGH.get())
                .define('F', ItemRegistry.FLOUR.get())
                .define('E', Items.EGG)
                .define('O', Items.MILK_BUCKET)
                .pattern("FF ")
                .pattern("EO ")
                .unlockedBy("has_flour", has(ItemRegistry.FLOUR.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.DOUGH.get())  + "_from_" + "milk"  ));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.DOUGH.get())
                .define('F', ItemRegistry.FLOUR.get())
                .define('E', Items.EGG)
                .define('O', PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER).getItem())
                .pattern("FF ")
                .pattern("EO ")
                .unlockedBy("has_flour", has(ItemRegistry.FLOUR.get()))
                .save(consumer, modLoc(getItemName(  ItemRegistry.DOUGH.get()) + "_from_" + "water_bottle"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.TOAST_LOAF.get())
                .define('D', ItemRegistry.DOUGH.get())
                .pattern("DDD")
                .pattern("DDD")
                .unlockedBy("has_dough", has(ItemRegistry.DOUGH.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.TOAST_LOAF.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.JAR.get())
                .define('G', Items.GLASS)
                .pattern("G G")
                .pattern("GGG")
                .unlockedBy("has_toast", has(ItemRegistry.TOAST.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.JAR.get())));

/*        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.SUSHI.get())
                .define('K', Items.DRIED_KELP)
                .define('R', ItemRegistry.RICE.get())
                .define('S', Items.SALMON)
                .pattern("KRK")
                .pattern("RSR")
                .pattern("KRK")
                .unlockedBy("has_rice", has(ItemRegistry.RICE.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.SUSHI.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.SUSHI.get())
                .define('K', Items.DRIED_KELP)
                .define('R', ItemRegistry.RICE.get())
                .define('C', ItemRegistry.CUCUMBER_SLICE.get())
                .pattern("KRK")
                .pattern("RCR")
                .pattern("KRK")
                .unlockedBy("has_rice", has(ItemRegistry.RICE.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.CUCUMBER_SUSHI.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.SUSHI.get())
                .define('K', Items.DRIED_KELP)
                .define('R', ItemRegistry.RICE.get())
                .define('L', ItemRegistry.RAW_LOBSTER.get())
                .pattern("KRK")
                .pattern("RLR")
                .pattern("KRK")
                .unlockedBy("has_rice", has(ItemRegistry.RICE.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.LOBSTER_SUSHI.get())));
 */
    }

    private void addShapelessRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.TOMATO_SEEDS.get())
                .requires(ItemRegistry.UNRIPE_TOMATO.get())
                .unlockedBy("tomato_seeds", has(ItemRegistry.TOMATO_SEEDS.get()))
                .save(consumer, modLoc("tomato_seeds_from_green_tomato"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.TOMATO_SEEDS.get(), 3)
                .requires(ItemRegistry.TOMATO.get())
                .unlockedBy("tomato_seeds", has(ItemRegistry.TOMATO_SEEDS.get()))
                .save(consumer, modLoc("tomato_seeds_from_tomato"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.TOMATO_SEEDS.get())
                .requires(ItemRegistry.ROTTEN_TOMATO.get())
                .unlockedBy("tomato_seeds", has(ItemRegistry.TOMATO_SEEDS.get()))
                .save(consumer, modLoc("tomato_seeds_from_rotten_tomato"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.TOMATO_SEEDS.get())
                .requires(ItemRegistry.TOMATO_SLICE.get())
                .unlockedBy("tomato_seeds", has(ItemRegistry.TOMATO_SEEDS.get()))
                .save(consumer, modLoc("tomato_seeds_from_tomato_slice"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.LETTUCE_SEEDS.get(), 2)
                .requires(ItemRegistry.LETTUCE.get())
                .unlockedBy("lettuce_seeds", has(ItemRegistry.LETTUCE_SEEDS.get()))
                .save(consumer, modLoc("lettuce_seeds_from_lettuce"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.COTTON_SEEDS.get(), 2)
                .requires(ItemRegistry.COTTON.get())
                .unlockedBy("cotton_seeds", has(ItemRegistry.COTTON_SEEDS.get()))
                .save(consumer, modLoc("cotton_seeds_from_cotton"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.STRAWBERRY_SEEDS.get(), 3)
                .requires(ItemRegistry.STRAWBERRY.get())
                .unlockedBy("strawberry_seeds", has(ItemRegistry.STRAWBERRY_SEEDS.get()))
                .save(consumer, modLoc("strawberry_seeds_from_strawberry"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.PICKLE.get())
                .requires(ItemRegistry.CUCUMBER.get())
                .requires(TagRegistry.Items.BUCKETS_WATER)
                .requires(Items.SUGAR)
                .unlockedBy("cucumber_seeds", has(ItemRegistry.CUCUMBER_SEEDS.get()))
                .save(consumer, modLoc("pickle"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.CUCUMBER_SEEDS.get())
                .requires(ItemRegistry.CUCUMBER.get())
                .unlockedBy("cucumber_seeds", has(ItemRegistry.CUCUMBER_SEEDS.get()))
                .save(consumer, modLoc("cucumber_seeds"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.CORN_SEEDS.get())
                .requires(ItemRegistry.CORN.get())
                .unlockedBy("corn_seeds", has(ItemRegistry.CORN_SEEDS.get()))
                .save(consumer, modLoc("corn_seeds"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.SUGAR)
                .requires(ItemRegistry.SUGAR_BEET.get())
                .unlockedBy("sugar_beet", has(ItemRegistry.SUGAR_BEET.get()))
                .save(consumer, modLoc("sugar"));


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.TOAST_SCRAMBLED_EGG.get())
                .requires(ItemRegistry.SCRAMBLED_EGG.get())
                .requires(ItemRegistry.TOAST_SLICE.get())
                .unlockedBy("toast", has(ItemRegistry.TOAST.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.TOAST_SCRAMBLED_EGG.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.TOAST_BOILED_EGG.get())
                .requires(ItemRegistry.BOILED_EGG.get())
                .requires(ItemRegistry.TOAST_SLICE.get())
                .unlockedBy("toast", has(ItemRegistry.TOAST.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.TOAST_BOILED_EGG.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.TOAST_FRIED_EGG.get())
                .requires(ItemRegistry.FRIED_EGG.get())
                .requires(ItemRegistry.TOAST_SLICE.get())
                .unlockedBy("toast", has(ItemRegistry.TOAST.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.TOAST_FRIED_EGG.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.TOAST_WITH_HONEY.get())
                .requires(ItemRegistry.HONEY_JAR.get())
                .requires(ItemRegistry.TOAST_SLICE.get())
                .unlockedBy("toast", has(ItemRegistry.TOAST.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.TOAST_WITH_HONEY.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.TOAST_WITH_STRAWBERRY_JAM.get())
                .requires(ItemRegistry.STRAWBERRY_JAM.get())
                .requires(ItemRegistry.TOAST_SLICE.get())
                .unlockedBy("toast", has(ItemRegistry.TOAST.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.TOAST_WITH_STRAWBERRY_JAM.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.TOAST_WITH_BLUEBERRY_JAM.get())
                .requires(ItemRegistry.BLUEBERRY_JAM.get())
                .requires(ItemRegistry.TOAST_SLICE.get())
                .unlockedBy("toast", has(ItemRegistry.TOAST.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.TOAST_WITH_BLUEBERRY_JAM.get())));

        fabricRecipeBuilder(ItemRegistry.BLACK_FABRIC.get(), Tags.Items.DYES_BLACK, consumer);
        fabricRecipeBuilder(ItemRegistry.BLUE_FABRIC.get(), Tags.Items.DYES_BLUE, consumer);
        fabricRecipeBuilder(ItemRegistry.BROWN_FABRIC.get(), Tags.Items.DYES_BROWN, consumer);
        fabricRecipeBuilder(ItemRegistry.CYAN_FABRIC.get(), Tags.Items.DYES_CYAN, consumer);
        fabricRecipeBuilder(ItemRegistry.GRAY_FABRIC.get(), Tags.Items.DYES_GRAY, consumer);
        fabricRecipeBuilder(ItemRegistry.GREEN_FABRIC.get(), Tags.Items.DYES_GREEN, consumer);
        fabricRecipeBuilder(ItemRegistry.LIGHT_BLUE_FABRIC.get(), Tags.Items.DYES_LIGHT_BLUE, consumer);
        fabricRecipeBuilder(ItemRegistry.LIGHT_GRAY_FABRIC.get(), Tags.Items.DYES_LIGHT_GRAY, consumer);
        fabricRecipeBuilder(ItemRegistry.LIME_FABRIC.get(), Tags.Items.DYES_LIME, consumer);
        fabricRecipeBuilder(ItemRegistry.MAGENTA_FABRIC.get(), Tags.Items.DYES_MAGENTA, consumer);
        fabricRecipeBuilder(ItemRegistry.ORANGE_FABRIC.get(), Tags.Items.DYES_ORANGE, consumer);
        fabricRecipeBuilder(ItemRegistry.PINK_FABRIC.get(), Tags.Items.DYES_PINK, consumer);
        fabricRecipeBuilder(ItemRegistry.PURPLE_FABRIC.get(), Tags.Items.DYES_PURPLE, consumer);
        fabricRecipeBuilder(ItemRegistry.RED_FABRIC.get(), Tags.Items.DYES_RED, consumer);
        fabricRecipeBuilder(ItemRegistry.WHITE_FABRIC.get(), Tags.Items.DYES_WHITE, consumer);
        fabricRecipeBuilder(ItemRegistry.YELLOW_FABRIC.get(), Tags.Items.DYES_YELLOW, consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.FABRIC.get())
                .requires(ItemRegistry.COTTON.get())
                .requires(ItemRegistry.COTTON.get())
                .requires(ItemRegistry.COTTON.get())
                .unlockedBy("cotton", has(ItemRegistry.COTTON.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.FABRIC.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.FABRIC.get())
                .requires(TagRegistry.Items.FABRICS_COLORED)
                .requires(TagRegistry.Items.BUCKETS_WATER)
                .unlockedBy("fabric", has(ItemRegistry.FABRIC.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.FABRIC.get()) + "_cleaning"));

    }

    private void addSmithingRecipes(Consumer<FinishedRecipe> consumer) {
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(ItemRegistry.DIAMOND_KNIFE.get()),
                Ingredient.of(Items.NETHERITE_INGOT),
                RecipeCategory.COMBAT,
                ItemRegistry.NETHERITE_KNIFE.get())
                .unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .save(consumer, modLoc(getItemName(ItemRegistry.NETHERITE_KNIFE.get()) + "_smithing"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ItemRegistry.DIAMOND_SCYTHE.get()),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.COMBAT,
                        ItemRegistry.NETHERITE_SCYTHE.get())
                .unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .save(consumer, modLoc(getItemName(ItemRegistry.NETHERITE_SCYTHE.get()) + "_smithing"));
    }

    private void addCookingRecipes(Consumer<FinishedRecipe> consumer) {
        /*
        // The first argument is a list of inputs that can result in the same output
        blasting(List.of(ItemRegistry.TOAST_LOAF.get()), RecipeCategory.FOOD, ItemRegistry.TOAST.get(), 0.25f, 200, "food", consumer);
        campfire(List.of(ItemRegistry.TOAST_LOAF.get()), RecipeCategory.FOOD, ItemRegistry.TOAST.get(), 0.25f, 200, "food", consumer);
        smelting(List.of(ItemRegistry.TOAST_LOAF.get()), RecipeCategory.FOOD, ItemRegistry.TOAST.get(), 0.25f, 200, "food", consumer);
        smoking(List.of(ItemRegistry.TOAST_LOAF.get()), RecipeCategory.FOOD, ItemRegistry.TOAST.get(), 0.25f, 200, "food", consumer);
         */

        smelting(List.of(ItemRegistry.TOAST_LOAF.get()), RecipeCategory.FOOD, ItemRegistry.TOAST.get(), 0.25f, 200, "food", consumer);
    }

    //TODO look at JER plantdrops category

}
package com.christofmeg.brutalharvest.common.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import com.christofmeg.brutalharvest.common.init.TagRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class BrutalRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public BrutalRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        this.addShapedRecipes(consumer);
        this.addShapelessRecipes(consumer);
        this.addSmithingRecipes(consumer);
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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.PICKLE.get())
                .requires(ItemRegistry.CUCUMBER.get())
                .requires(TagRegistry.Items.BUCKETS_WATER)
                .requires(Items.SUGAR)
                .unlockedBy("cucumber_seeds", has(ItemRegistry.CUCUMBER_SEEDS.get()))
                .save(consumer, modLoc("pickle"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.SUGAR)
                .requires(ItemRegistry.SUGAR_BEET.get())
                .unlockedBy("sugar_beet", has(ItemRegistry.SUGAR_BEET.get()))
                .save(consumer, modLoc("sugar"));

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

    private ResourceLocation modLoc(String string) {
        return new ResourceLocation(CommonConstants.MOD_ID, string);
    }

    private void knifeBuilder(Item item, Ingredient ingredient, String string, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                .define('I', ingredient)
                .define('S', Tags.Items.RODS_WOODEN)
                .pattern(" I")
                .pattern("S ")
                .unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
                .save(consumer, modLoc(string));
    }

    private void scytheBuilder(Item item, Ingredient ingredient, String string, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                .define('I', ingredient)
                .define('S', Tags.Items.RODS_WOODEN)
                .pattern("I ")
                .pattern(" I")
                .pattern("S ")
                .unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
                .save(consumer, modLoc(string));
    }

    private void fabricRecipeBuilder(Item item, TagKey<Item> requires, Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, item)
                .requires(TagRegistry.Items.FABRICS)
                .requires(requires)
                .unlockedBy("fabric", has(ItemRegistry.FABRIC.get()))
                .save(consumer, modLoc(getItemName(item)));
    }

    //TODO look at JER plantdrops category

}
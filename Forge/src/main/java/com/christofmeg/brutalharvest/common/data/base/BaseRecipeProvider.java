package com.christofmeg.brutalharvest.common.data.base;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import com.christofmeg.brutalharvest.common.init.TagRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class BaseRecipeProvider extends VanillaRecipeProvider implements IConditionBuilder {

    public BaseRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {

    }

    protected ResourceLocation modLoc(String string) {
        return new ResourceLocation(CommonConstants.MOD_ID, string);
    }

    protected void knifeBuilder(Item item, Ingredient ingredient, String string, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                .define('I', ingredient)
                .define('S', Tags.Items.RODS_WOODEN)
                .pattern(" I")
                .pattern("S ")
                .unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
                .save(consumer, modLoc(string));
    }

    protected void scytheBuilder(Item item, Ingredient ingredient, String string, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                .define('I', ingredient)
                .define('S', Tags.Items.RODS_WOODEN)
                .pattern("I ")
                .pattern(" I")
                .pattern("S ")
                .unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
                .save(consumer, modLoc(string));
    }

    protected void fabricRecipeBuilder(Item item, TagKey<Item> requires, Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, item)
                .requires(TagRegistry.Items.FABRICS)
                .requires(requires)
                .unlockedBy("fabric", has(ItemRegistry.FABRIC.get()))
                .save(consumer, modLoc(getItemName(item)));
    }

    protected void woolRecipeBuilder(TagKey<Item> ingredient, Item banner, Item bed, Item carpet, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, banner)
                .define('#', ingredient)
                .define('|', Tags.Items.RODS_WOODEN)
                .pattern("###")
                .pattern("###")
                .pattern(" | ")
                .unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(consumer, modLoc(getItemName(banner)));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, bed)
                .define('#', ingredient)
                .define('X', ItemTags.PLANKS)
                .pattern("###")
                .pattern("XXX")
                .unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(consumer, modLoc(getItemName(bed)));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, carpet)
                .define('#', ingredient)
                .pattern("##")
                .unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(consumer, modLoc(getItemName(carpet)));
    }

    protected static void blasting(List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group, Consumer<FinishedRecipe> consumer) {
        genericCookingRecipe(RecipeSerializer.BLASTING_RECIPE, ingredients, category, result, experience, cookingTime, group, "_from_blasting", consumer);
    }

    protected static void campfire(List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group, Consumer<FinishedRecipe> consumer) {
        genericCookingRecipe(RecipeSerializer.CAMPFIRE_COOKING_RECIPE, ingredients, category, result, experience, cookingTime, group, "_from_campfire_cooking", consumer);
    }

    protected static void smelting(List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group, Consumer<FinishedRecipe> consumer) {
        genericCookingRecipe(RecipeSerializer.SMELTING_RECIPE, ingredients, category, result, experience, cookingTime, group, "_from_smelting", consumer);
    }

    protected static void smoking(List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group, Consumer<FinishedRecipe> consumer) {
        genericCookingRecipe(RecipeSerializer.SMOKING_RECIPE, ingredients, category, result, experience, cookingTime, group, "_from_smoking", consumer);
    }

    protected static void genericCookingRecipe(RecipeSerializer<? extends AbstractCookingRecipe> recipeSerializer, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group, String recipeName, Consumer<FinishedRecipe> consumer) {
        for(ItemLike itemlike : ingredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), category, result,
                            experience, cookingTime, recipeSerializer)
                    .group(group).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(consumer,  CommonConstants.MOD_ID + ":" + getItemName(result) + recipeName);
        }
    }

}

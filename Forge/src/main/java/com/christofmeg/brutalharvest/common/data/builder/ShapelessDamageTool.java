package com.christofmeg.brutalharvest.common.data.builder;

import com.christofmeg.brutalharvest.common.init.RecipeSerializerRegistry;
import com.google.common.collect.Lists;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class ShapelessDamageTool extends ShapelessRecipeBuilder {

    private final RecipeCategory category;
    private final Item result;
    private final int count;
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();
    @Nullable private String group;

    public ShapelessDamageTool(RecipeCategory pCategory, ItemLike pResult, int pCount) {
        super(pCategory, pResult, pCount);
        this.category = pCategory;
        this.result = pResult.asItem();
        this.count = pCount;
    }

    public static ShapelessDamageTool shapeless(@NotNull RecipeCategory pCategory, ItemLike pResult) {
        return new ShapelessDamageTool(pCategory, pResult, 1);
    }

    public static ShapelessDamageTool shapeless(@NotNull RecipeCategory pCategory, ItemLike pResult, int pCount) {
        return new ShapelessDamageTool(pCategory, pResult, pCount);
    }

    @Override
    public @NotNull ShapelessDamageTool requires(@NotNull TagKey<Item> pTag) {
        return this.requires(Ingredient.of(pTag));
    }

    @Override
    public @NotNull ShapelessDamageTool requires(@NotNull ItemLike pItem) {
        return this.requires(pItem, 1);
    }

    @Override
    public @NotNull ShapelessDamageTool requires(@NotNull ItemLike pItem, int pQuantity) {
        for(int $$2 = 0; $$2 < pQuantity; ++$$2) {
            this.requires(Ingredient.of(pItem));
        }
        return this;
    }

    @Override
    public @NotNull ShapelessDamageTool requires(@NotNull Ingredient pIngredient) {
        return this.requires(pIngredient, 1);
    }

    @Override
    public @NotNull ShapelessDamageTool requires(@NotNull Ingredient pIngredient, int pQuantity) {
        for(int $$2 = 0; $$2 < pQuantity; ++$$2) {
            this.ingredients.add(pIngredient);
        }
        return this;
    }

    @Override
    public @NotNull ShapelessDamageTool unlockedBy(@NotNull String pCriterionName, @NotNull CriterionTriggerInstance pCriterionTrigger) {
        this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
        return this;
    }

    @Override
    public @NotNull ShapelessDamageTool group(@Nullable String pGroupName) {
        this.group = pGroupName;
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return this.result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, @NotNull ResourceLocation pRecipeId) {
        this.ensureValid(pRecipeId);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId)).rewards(net.minecraft.advancements.AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
        pFinishedRecipeConsumer.accept(new CustomResult(pRecipeId, this.result, this.count, this.group == null ? "" : this.group, determineBookCategory(this.category), this.ingredients, this.advancement, pRecipeId.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private void ensureValid(ResourceLocation pId) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }

    public static class CustomResult extends Result {
        public CustomResult(ResourceLocation pId, Item pResult, int pCount, String pGroup, CraftingBookCategory pCategory, List<Ingredient> pIngredients, Advancement.Builder pAdvancement, ResourceLocation pAdvancementId) {
            super(pId, pResult, pCount, pGroup, pCategory, pIngredients, pAdvancement, pAdvancementId);
        }

        @Override
        public @NotNull RecipeSerializer<?> getType() {
            return RecipeSerializerRegistry.SHAPELESS_DAMAGE_TOOL.get();
        }
    }

}

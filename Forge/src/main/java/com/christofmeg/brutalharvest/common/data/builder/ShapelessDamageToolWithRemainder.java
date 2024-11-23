package com.christofmeg.brutalharvest.common.data.builder;

import com.christofmeg.brutalharvest.common.init.RecipeSerializerRegistry;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
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
public class ShapelessDamageToolWithRemainder extends ShapelessRecipeBuilder {

    final RecipeCategory category;
    final Item result;
    final int count;
    final List<Ingredient> ingredients = Lists.newArrayList();
    final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();
    @Nullable
    String group;
    final Ingredient remainderTrigger;
    final Ingredient remainder;

    public ShapelessDamageToolWithRemainder(RecipeCategory pCategory, ItemLike pResult, int pCount, Ingredient remainderTrigger, Ingredient remainder) {
        super(pCategory, pResult, pCount);
        this.category = pCategory;
        this.result = pResult.asItem();
        this.count = pCount;
        this.remainderTrigger = remainderTrigger;
        this.remainder = remainder;
    }

    public static ShapelessDamageToolWithRemainder shapeless(@NotNull RecipeCategory pCategory, ItemLike pResult, Ingredient remainderTrigger, Ingredient remainder) {
        return new ShapelessDamageToolWithRemainder(pCategory, pResult, 1, remainderTrigger, remainder);
    }

    public static ShapelessDamageToolWithRemainder shapeless(@NotNull RecipeCategory pCategory, ItemLike pResult, Ingredient remainderTrigger, int pCount, Ingredient remainder) {
        return new ShapelessDamageToolWithRemainder(pCategory, pResult, pCount, remainderTrigger, remainder);
    }

    @Override
    public @NotNull ShapelessDamageToolWithRemainder requires(@NotNull TagKey<Item> pTag) {
        return this.requires(Ingredient.of(pTag));
    }

    @Override
    public @NotNull ShapelessDamageToolWithRemainder requires(@NotNull ItemLike pItem) {
        return this.requires(pItem, 1);
    }

    @Override
    public @NotNull ShapelessDamageToolWithRemainder requires(@NotNull ItemLike pItem, int pQuantity) {
        for(int $$2 = 0; $$2 < pQuantity; ++$$2) {
            this.requires(Ingredient.of(pItem));
        }
        return this;
    }

    @Override
    public @NotNull ShapelessDamageToolWithRemainder requires(@NotNull Ingredient pIngredient) {
        return this.requires(pIngredient, 1);
    }

    @Override
    public @NotNull ShapelessDamageToolWithRemainder requires(@NotNull Ingredient pIngredient, int pQuantity) {
        for(int $$2 = 0; $$2 < pQuantity; ++$$2) {
            this.ingredients.add(pIngredient);
        }
        return this;
    }

    @Override
    public @NotNull ShapelessDamageToolWithRemainder unlockedBy(@NotNull String pCriterionName, @NotNull CriterionTriggerInstance pCriterionTrigger) {
        this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
        return this;
    }

    @Override
    public @NotNull ShapelessDamageToolWithRemainder group(@Nullable String pGroupName) {
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
        pFinishedRecipeConsumer.accept(new CustomResult(pRecipeId, this.result, this.count, this.remainderTrigger, this.remainder, this.group == null ? "" :
                this.group, determineBookCategory(this.category), this.ingredients, this.advancement, pRecipeId.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private void ensureValid(ResourceLocation pId) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }

    public static class CustomResult extends ShapelessRecipeBuilder.Result {
        private final Item result;
        private final int count;
        private final Ingredient remainderTrigger;
        private final Ingredient remainder;
        private final String group;
        private final List<Ingredient> ingredients;

        public CustomResult(ResourceLocation pId, Item pResult, int pCount, Ingredient remainderTrigger, Ingredient remainder, String pGroup, CraftingBookCategory pCategory, List<Ingredient> pIngredients, Advancement.Builder pAdvancement, ResourceLocation pAdvancementId) {
            super(pId, pResult, pCount, pGroup, pCategory, pIngredients, pAdvancement, pAdvancementId);
            this.result = pResult;
            this.count = pCount;
            this.remainderTrigger = remainderTrigger;
            this.remainder = remainder;
            this.group = pGroup;
            this.ingredients = pIngredients;
        }

        @Override
        public @NotNull RecipeSerializer<?> getType() {
            return RecipeSerializerRegistry.SHAPELESS_DAMAGE_TOOL_WITH_REMAINDER.get();
        }

        @SuppressWarnings("deprecation")
        @Override
        public void serializeRecipeData(@NotNull JsonObject pJson) {
            super.serializeRecipeData(pJson);
            if (!this.group.isEmpty()) {
                pJson.addProperty("group", this.group);
            }

            JsonArray ingredients = new JsonArray();
            for (Ingredient $$2 : this.ingredients) {
                ingredients.add($$2.toJson());
            }
            pJson.add("ingredients", ingredients);

            JsonObject result = new JsonObject();
            result.addProperty("item", BuiltInRegistries.ITEM.getKey(this.result).toString());
            if (this.count > 1) {
                result.addProperty("count", this.count);
            }
            pJson.add("result", result);

            pJson.add("remainderTrigger", remainderTrigger.toJson());
            pJson.add("remainder", remainder.toJson());
        }
    }

}

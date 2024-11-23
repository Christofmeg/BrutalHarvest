package com.christofmeg.brutalharvest.common.recipe.shapeless;

import com.christofmeg.brutalharvest.common.init.RecipeSerializerRegistry;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.*;
import org.jetbrains.annotations.NotNull;

public class WithRemainder extends ShapelessRecipe {

    final ItemStack result;
    final ItemStack remainderTrigger;
    final ItemStack remainder;

    public WithRemainder(ResourceLocation id, String group, ItemStack result, ItemStack remainderTrigger, ItemStack remainder, NonNullList<Ingredient> ingredients) {
        super(id, group, CraftingBookCategory.MISC, result, ingredients);
        this.result = result;
        this.remainderTrigger = remainderTrigger;
        this.remainder = remainder;
    }

    @Override
    public @NotNull NonNullList<ItemStack> getRemainingItems(CraftingContainer inv) {
        NonNullList<ItemStack> remainingItems = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);
        for (int i = 0; i < remainingItems.size(); ++i) {
            ItemStack stack = inv.getItem(i);
            if (stack.getItem() == Items.POTION && remainderTrigger.getItem() == Items.POTION) {
                if (PotionUtils.getPotion(stack) == PotionUtils.getPotion(remainderTrigger)) {
                    remainingItems.set(i, remainder.copy());
                }
            } else if (stack.getItem() == remainderTrigger.getItem()) {
                remainingItems.set(i, remainder.copy());
            } else {
                super.getRemainingItems(inv);
            }
        }
        return remainingItems;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return RecipeSerializerRegistry.SHAPELESS_RECIPE_WITH_REMAINDER.get();
    }

    public static class Serializer implements RecipeSerializer<WithRemainder> {

        public Serializer() {
        }

        @Override
        public @NotNull WithRemainder fromJson(@NotNull ResourceLocation pRecipeId, @NotNull JsonObject pJson) {
            String s = GsonHelper.getAsString(pJson, "group", "");
            NonNullList<Ingredient> nonnulllist = itemsFromJson(GsonHelper.getAsJsonArray(pJson, "ingredients"));
            if (nonnulllist.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else if (nonnulllist.size() > 3 * 3) {
                throw new JsonParseException("Too many ingredients for shapeless recipe. The maximum is " + 3 * 3);
            } else {
                ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "result"));
                ItemStack remainderTrigger = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "remainderTrigger"));
                ItemStack remainder = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "remainder"));
                return new WithRemainder(pRecipeId, s, itemstack, remainderTrigger, remainder, nonnulllist);
            }
        }

        private static NonNullList<Ingredient> itemsFromJson(JsonArray pIngredientArray) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();
            for(int i = 0; i < pIngredientArray.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(pIngredientArray.get(i), false);
                nonnulllist.add(ingredient);
            }
            return nonnulllist;
        }

        @Override
        public WithRemainder fromNetwork(@NotNull ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            String s = pBuffer.readUtf();
            int i = pBuffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);
            nonnulllist.replaceAll(ignored -> Ingredient.fromNetwork(pBuffer));
            ItemStack itemstack = pBuffer.readItem();
            ItemStack remainderTrigger = pBuffer.readItem();
            ItemStack remainder = pBuffer.readItem();
            return new WithRemainder(pRecipeId, s, itemstack, remainderTrigger, remainder, nonnulllist);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, WithRemainder pRecipe) {
            pBuffer.writeUtf(pRecipe.getGroup());
            pBuffer.writeEnum(pRecipe.category());
            pBuffer.writeVarInt(pRecipe.getIngredients().size());
            for (Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }
            pBuffer.writeItem(pRecipe.result);
            pBuffer.writeItem(pRecipe.remainderTrigger);
            pBuffer.writeItem(pRecipe.remainder);
        }

    }

}
package com.christofmeg.brutalharvest.common.recipe;

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
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.*;
import org.jetbrains.annotations.NotNull;

public class ShapelessRecipeWithRemainder extends ShapelessRecipe {

    final ItemStack result;

    public ShapelessRecipeWithRemainder(ResourceLocation id, String group, ItemStack result, NonNullList<Ingredient> ingredients) {
        super(id, group, CraftingBookCategory.MISC, result, ingredients);
        this.result = result;
    }

    @Override
    public @NotNull NonNullList<ItemStack> getRemainingItems(CraftingContainer inv) {
        NonNullList<ItemStack> remainingItems = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);
        for (int i = 0; i < remainingItems.size(); ++i) {
            ItemStack itemstack = inv.getItem(i);
            if (itemstack.getItem() == Items.POTION && PotionUtils.getPotion(itemstack) == Potions.WATER) {
                remainingItems.set(i, new ItemStack(Items.GLASS_BOTTLE));
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

    public static class Serializer implements RecipeSerializer<ShapelessRecipeWithRemainder> {

        public Serializer() {
        }

        @Override
        public @NotNull ShapelessRecipeWithRemainder fromJson(@NotNull ResourceLocation pRecipeId, @NotNull JsonObject pJson) {
            String s = GsonHelper.getAsString(pJson, "group", "");
            NonNullList<Ingredient> nonnulllist = itemsFromJson(GsonHelper.getAsJsonArray(pJson, "ingredients"));
            if (nonnulllist.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else if (nonnulllist.size() > 3 * 3) {
                throw new JsonParseException("Too many ingredients for shapeless recipe. The maximum is " + 3 * 3);
            } else {
                ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "result"));
                return new ShapelessRecipeWithRemainder(pRecipeId, s, itemstack, nonnulllist);
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
        public ShapelessRecipeWithRemainder fromNetwork(@NotNull ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            String s = pBuffer.readUtf();
            int i = pBuffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);

            nonnulllist.replaceAll(ignored -> Ingredient.fromNetwork(pBuffer));

            ItemStack itemstack = pBuffer.readItem();
            return new ShapelessRecipeWithRemainder(pRecipeId, s, itemstack, nonnulllist);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, ShapelessRecipeWithRemainder pRecipe) {
            pBuffer.writeUtf(pRecipe.getGroup());
            pBuffer.writeEnum(pRecipe.category());
            pBuffer.writeVarInt(pRecipe.getIngredients().size());

            for (Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeItem(pRecipe.result);
        }
    }

}
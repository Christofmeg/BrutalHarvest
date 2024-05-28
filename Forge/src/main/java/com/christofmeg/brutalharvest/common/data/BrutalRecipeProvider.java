package com.christofmeg.brutalharvest.common.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
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

    //TODO look at JER plantdrops category

}
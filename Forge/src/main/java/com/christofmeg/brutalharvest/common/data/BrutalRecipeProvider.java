package com.christofmeg.brutalharvest.common.data;

import com.christofmeg.brutalharvest.common.data.base.BaseRecipeProvider;
import com.christofmeg.brutalharvest.common.data.builder.ShapelessDamageTool;
import com.christofmeg.brutalharvest.common.data.builder.ShapelessDamageToolWithRemainder;
import com.christofmeg.brutalharvest.common.data.builder.ShapelessWithRemainder;
import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import com.christofmeg.brutalharvest.common.init.TagRegistry;
import com.christofmeg.brutalharvest.common.util.NBTIngredient;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.SEED_SATCHEL.get()).define('C', TagRegistry.Items.CROPS).define('F', ItemRegistry.FABRIC.get()).pattern("FCF").pattern("FFF").unlockedBy("has_cotton", has(ItemRegistry.COTTON.get())).save(consumer, modLoc(getItemName(ItemRegistry.SEED_SATCHEL.get())));

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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.GARDENERS_HAT.get()).define('W', Tags.Items.CROPS_WHEAT).pattern("WWW").pattern("W W").unlockedBy("has_wheat", has(Tags.Items.CROPS_WHEAT)).save(consumer, modLoc(getItemName(ItemRegistry.GARDENERS_HAT.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.CHEFS_HAT.get()).define('W', ItemRegistry.WHITE_FABRIC.get()).pattern("WWW").pattern("W W").unlockedBy("has_fabric", has(ItemRegistry.FABRIC.get())).save(consumer, modLoc(getItemName(ItemRegistry.CHEFS_HAT.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.CHEFS_APRON.get()).define('W', ItemRegistry.WHITE_FABRIC.get()).define('S', Tags.Items.STRING).pattern(" W ").pattern("SWS").pattern("WWW").unlockedBy("has_fabric", has(ItemRegistry.FABRIC.get())).save(consumer, modLoc(getItemName(ItemRegistry.CHEFS_APRON.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.BLUE_DUNGAREE.get()).define('W', ItemRegistry.BLUE_FABRIC.get()).pattern("WWW").pattern("W W").pattern("W W").unlockedBy("has_fabric", has(ItemRegistry.FABRIC.get())).save(consumer, modLoc(getItemName(ItemRegistry.BLUE_DUNGAREE.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.OLD_DUNGAREE.get()).define('W', ItemRegistry.BROWN_FABRIC.get()).pattern("WWW").pattern("W W").pattern("W W").unlockedBy("has_fabric", has(ItemRegistry.FABRIC.get())).save(consumer, modLoc(getItemName(ItemRegistry.OLD_DUNGAREE.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.BOOTS.get()).define('W', ItemRegistry.BROWN_FABRIC.get()).pattern("W W").pattern("W W").unlockedBy("has_fabric", has(ItemRegistry.FABRIC.get())).save(consumer, modLoc(getItemName(ItemRegistry.BOOTS.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.TOAST_LOAF.get()).define('D', ItemRegistry.DOUGH.get()).pattern("DDD").pattern("DDD").unlockedBy("has_dough", has(ItemRegistry.DOUGH.get())).save(consumer, modLoc(getItemName(ItemRegistry.TOAST_LOAF.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.JAR.get(), 3).define('G', Items.GLASS).pattern("G G").pattern("GGG").unlockedBy("has_toast", has(ItemRegistry.TOAST.get())).save(consumer, modLoc(getItemName(ItemRegistry.JAR.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockRegistry.RUBBER_WOOD.get(), 3).define('L', BlockRegistry.RUBBER_LOG.get()).pattern("LL").pattern("LL").unlockedBy("has_rubber_log", has(TagRegistry.Items.RUBBER_LOGS)).save(consumer, modLoc(getItemName(BlockRegistry.RUBBER_WOOD.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockRegistry.STRIPPED_RUBBER_WOOD.get(), 3).define('L', BlockRegistry.STRIPPED_RUBBER_LOG.get()).pattern("LL").pattern("LL").unlockedBy("has_rubber_log", has(TagRegistry.Items.RUBBER_LOGS)).save(consumer, modLoc(getItemName(BlockRegistry.STRIPPED_RUBBER_WOOD.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockRegistry.FARMLAND_SLAB.get(), 6).define('B', Blocks.FARMLAND).pattern("BBB").unlockedBy("has_dirt", has(ItemTags.DIRT)).save(consumer, modLoc(getItemName(BlockRegistry.FARMLAND_SLAB.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Blocks.FARMLAND).define('B', BlockRegistry.FARMLAND_SLAB.get()).pattern("B").pattern("B").unlockedBy("has_dirt", has(ItemTags.DIRT)).save(consumer, modLoc(getItemName(Blocks.FARMLAND)));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockRegistry.DIRT_SLAB.get(), 6).define('B', Blocks.DIRT).pattern("BBB").unlockedBy("has_dirt", has(ItemTags.DIRT)).save(consumer, modLoc(getItemName(BlockRegistry.DIRT_SLAB.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Blocks.DIRT).define('B', BlockRegistry.DIRT_SLAB.get()).pattern("B").pattern("B").unlockedBy("has_dirt", has(ItemTags.DIRT)).save(consumer, modLoc(getItemName(Blocks.DIRT)));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockRegistry.DIRT_PATH_SLAB.get(), 6).define('B', Blocks.DIRT_PATH).pattern("BBB").unlockedBy("has_dirt", has(ItemTags.DIRT)).save(consumer, modLoc(getItemName(BlockRegistry.DIRT_PATH_SLAB.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Blocks.DIRT_PATH).define('B', BlockRegistry.DIRT_PATH_SLAB.get()).pattern("B").pattern("B").unlockedBy("has_dirt", has(ItemTags.DIRT)).save(consumer, modLoc(getItemName(Blocks.DIRT_PATH)));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockRegistry.DIRT_TRACK_SLAB.get(), 6).define('B', BlockRegistry.DIRT_TRACK.get()).pattern("BBB").unlockedBy("has_dirt", has(ItemTags.DIRT)).save(consumer, modLoc(getItemName(BlockRegistry.DIRT_TRACK_SLAB.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockRegistry.DIRT_TRACK.get()).define('B', BlockRegistry.DIRT_TRACK_SLAB.get()).pattern("B").pattern("B").unlockedBy("has_dirt", has(ItemTags.DIRT)).save(consumer, modLoc(getItemName(BlockRegistry.DIRT_TRACK.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockRegistry.GRASS_SLAB.get(), 6).define('B', Blocks.GRASS_BLOCK).pattern("BBB").unlockedBy("has_dirt", has(ItemTags.DIRT)).save(consumer, modLoc(getItemName(BlockRegistry.GRASS_SLAB.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Blocks.GRASS_BLOCK).define('B', BlockRegistry.GRASS_SLAB.get()).pattern("B").pattern("B").unlockedBy("has_dirt", has(ItemTags.DIRT)).save(consumer, modLoc(getItemName(Blocks.GRASS_BLOCK)));

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


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.SCRAMBLED_EGG_TOAST.get())
                .requires(ItemRegistry.SCRAMBLED_EGG.get())
                .requires(ItemRegistry.TOAST_SLICE.get())
                .unlockedBy("toast", has(ItemRegistry.TOAST.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.SCRAMBLED_EGG_TOAST.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.BOILED_EGG_TOAST.get())
                .requires(ItemRegistry.BOILED_EGG.get())
                .requires(ItemRegistry.TOAST_SLICE.get())
                .unlockedBy("toast", has(ItemRegistry.TOAST.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.BOILED_EGG_TOAST.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.FRIED_EGG_TOAST.get())
                .requires(ItemRegistry.FRIED_EGG.get())
                .requires(ItemRegistry.TOAST_SLICE.get())
                .unlockedBy("toast", has(ItemRegistry.TOAST.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.FRIED_EGG_TOAST.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.HONEY_TOAST.get())
                .requires(ItemRegistry.HONEY_JAR.get())
                .requires(ItemRegistry.TOAST_SLICE.get())
                .unlockedBy("toast", has(ItemRegistry.TOAST.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.HONEY_TOAST.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.STRAWBERRY_TOAST.get())
                .requires(ItemRegistry.STRAWBERRY_JAM.get())
                .requires(ItemRegistry.TOAST_SLICE.get())
                .unlockedBy("toast", has(ItemRegistry.TOAST.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.STRAWBERRY_TOAST.get())));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.BLUEBERRY_TOAST.get())
                .requires(ItemRegistry.BLUEBERRY_JAM.get())
                .requires(ItemRegistry.TOAST_SLICE.get())
                .unlockedBy("toast", has(ItemRegistry.TOAST.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.BLUEBERRY_TOAST.get())));

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

        Ingredient waterBottle = new NBTIngredient(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.DOUGH.get())
                .requires(Ingredient.of(TagRegistry.Items.FLOUR), 2)
                .requires(Tags.Items.EGGS)
                .requires(TagRegistry.Items.BUCKETS_WATER)
                .unlockedBy("flour", has(ItemRegistry.FLOUR.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.DOUGH.get()) + "_from_" + "water_bucket"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.DOUGH.get())
                .requires(Ingredient.of(TagRegistry.Items.FLOUR), 2)
                .requires(Tags.Items.EGGS)
                .requires(TagRegistry.Items.BUCKETS_MILK)
                .unlockedBy("flour", has(ItemRegistry.FLOUR.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.DOUGH.get()) + "_from_" + "milk_bucket"));
        ShapelessWithRemainder.shapeless(RecipeCategory.MISC, ItemRegistry.DOUGH.get(), waterBottle, Ingredient.of(Items.GLASS_BOTTLE))
                .requires(Ingredient.of(TagRegistry.Items.FLOUR), 2)
                .requires(Tags.Items.EGGS)
                .requires(waterBottle)
                .unlockedBy("flour", has(ItemRegistry.FLOUR.get()))
                .save(consumer, modLoc(getItemName(ItemRegistry.DOUGH.get()) + "_from_" + "water_bottle"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.DOUGH.get()).requires(Ingredient.of(TagRegistry.Items.FLOUR), 2).requires(Tags.Items.EGGS).requires(TagRegistry.Items.BOTTLES_MILK).unlockedBy("flour", has(ItemRegistry.FLOUR.get())).save(consumer, modLoc(getItemName(ItemRegistry.DOUGH.get()) + "_from_" + "milk_bottle"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.TOMATO_DOUGH.get()).requires(TagRegistry.Items.FLOUR).requires(TagRegistry.Items.TOMATO).requires(Tags.Items.EGGS).requires(TagRegistry.Items.BUCKETS_WATER).unlockedBy("flour", has(ItemRegistry.FLOUR.get())).save(consumer, modLoc(getItemName(ItemRegistry.TOMATO_DOUGH.get()) + "_from_" + "water_bucket"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.TOMATO_DOUGH.get()).requires(TagRegistry.Items.FLOUR).requires(TagRegistry.Items.TOMATO).requires(Tags.Items.EGGS).requires(TagRegistry.Items.BUCKETS_MILK).unlockedBy("flour", has(ItemRegistry.FLOUR.get())).save(consumer, modLoc(getItemName(ItemRegistry.TOMATO_DOUGH.get()) + "_from_" + "milk_bucket"));
        ShapelessWithRemainder.shapeless(RecipeCategory.MISC, ItemRegistry.TOMATO_DOUGH.get(), waterBottle, Ingredient.of(Items.GLASS_BOTTLE)).requires(TagRegistry.Items.FLOUR).requires(TagRegistry.Items.TOMATO).requires(Tags.Items.EGGS).requires(waterBottle).unlockedBy("flour", has(ItemRegistry.FLOUR.get())).save(consumer, modLoc(getItemName(ItemRegistry.TOMATO_DOUGH.get()) + "_from_" + "water_bottle"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.TOMATO_DOUGH.get()).requires(Ingredient.of(TagRegistry.Items.FLOUR), 2).requires(Tags.Items.EGGS).requires(TagRegistry.Items.BOTTLES_MILK).unlockedBy("flour", has(ItemRegistry.FLOUR.get())).save(consumer, modLoc(getItemName(ItemRegistry.TOMATO_DOUGH.get()) + "_from_" + "milk_bottle"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BlockRegistry.RUBBER_PLANKS.get(), 4).requires(TagRegistry.Items.RUBBER_LOGS).unlockedBy("has_rubber_log", has(TagRegistry.Items.RUBBER_LOGS)).save(consumer, modLoc(getItemName(BlockRegistry.RUBBER_PLANKS.get())));

        ShapelessDamageTool.shapeless(RecipeCategory.MISC, Blocks.FARMLAND).requires(ItemTags.HOES).requires(TagRegistry.Items.BLOCKS_TILLABLE).unlockedBy("has_hoe", has(ItemTags.HOES)).save(consumer, modLoc(getItemName(Blocks.FARMLAND) + "_from_hoe"));
        ShapelessDamageTool.shapeless(RecipeCategory.MISC, Blocks.DIRT).requires(ItemTags.HOES).requires(Blocks.COARSE_DIRT).unlockedBy("has_hoe", has(ItemTags.HOES)).save(consumer, modLoc(getItemName(Blocks.DIRT) + "_from_hoe" + "_and_" + getItemName(Blocks.COARSE_DIRT)));
        ShapelessDamageToolWithRemainder.shapeless(RecipeCategory.MISC, Items.DIRT, Ingredient.of(Blocks.ROOTED_DIRT), Ingredient.of(Items.HANGING_ROOTS)).requires(ItemTags.HOES).requires(Blocks.ROOTED_DIRT).unlockedBy("has_hoe", has(ItemTags.HOES)).save(consumer, modLoc(getItemName(Blocks.DIRT) + "_from_hoe" + "_and_" + getItemName(Blocks.ROOTED_DIRT)));

        ShapelessDamageTool.shapeless(RecipeCategory.MISC, BlockRegistry.FARMLAND_SLAB.get()).requires(ItemTags.HOES).requires(TagRegistry.Items.SLABS_TILLABLE).unlockedBy("has_hoe", has(ItemTags.HOES)).save(consumer, modLoc(getItemName(BlockRegistry.FARMLAND_SLAB.get()) + "_from_hoe"));
        ShapelessDamageTool.shapeless(RecipeCategory.MISC, BlockRegistry.DIRT_PATH_SLAB.get()).requires(ItemTags.SHOVELS).requires(BlockRegistry.GRASS_SLAB.get()).unlockedBy("has_shovel", has(ItemTags.SHOVELS)).save(consumer, modLoc(getItemName(BlockRegistry.DIRT_PATH_SLAB.get()) + "_from_shovel"));
        ShapelessDamageTool.shapeless(RecipeCategory.MISC, Blocks.DIRT_PATH).requires(ItemTags.SHOVELS).requires(Items.GRASS_BLOCK).unlockedBy("has_shovel", has(ItemTags.SHOVELS)).save(consumer, modLoc("vanilla_" + getItemName(Blocks.DIRT_PATH) + "_from_shovel"));
        ShapelessDamageTool.shapeless(RecipeCategory.MISC, BlockRegistry.DIRT_TRACK_SLAB.get()).requires(ItemTags.SHOVELS).requires(BlockRegistry.DIRT_SLAB.get()).unlockedBy("has_shovel", has(ItemTags.SHOVELS)).save(consumer, modLoc(getItemName(BlockRegistry.DIRT_TRACK_SLAB.get()) + "_from_shovel"));
        ShapelessDamageTool.shapeless(RecipeCategory.MISC, BlockRegistry.DIRT_TRACK.get()).requires(ItemTags.SHOVELS).requires(Items.DIRT).unlockedBy("has_shovel", has(ItemTags.SHOVELS)).save(consumer, modLoc(getItemName(BlockRegistry.DIRT_TRACK.get()) + "_from_shovel"));
        ShapelessDamageTool.shapeless(RecipeCategory.MISC, BlockRegistry.DIRT_TRACK.get()).requires(ItemTags.SHOVELS).requires(Items.COARSE_DIRT).unlockedBy("has_shovel", has(ItemTags.SHOVELS)).save(consumer, modLoc(getItemName(Blocks.COARSE_DIRT) + "_from_shovel"));
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
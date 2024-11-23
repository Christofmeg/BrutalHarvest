package com.christofmeg.brutalharvest.common.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import com.christofmeg.brutalharvest.common.init.TagRegistry;
import com.christofmeg.brutalharvest.common.item.KnifeItem;
import com.christofmeg.brutalharvest.common.item.ScytheItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BrutalItemTagsProvider extends ItemTagsProvider {

    public BrutalItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, TagsProvider<Block> lookup, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, lookup.contentsGetter(), CommonConstants.MOD_ID, existingFileHelper);
    }

    @Override
    public @NotNull String getName() {
        return CommonConstants.MOD_NAME + " - Item Tags";
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {

        tag(Tags.Items.SEEDS)
            .addTag(TagRegistry.Items.TOMATO_SEEDS)
            .addTag(TagRegistry.Items.LETTUCE_SEEDS)
            .addTag(TagRegistry.Items.CORN_SEEDS)
            .addTag(TagRegistry.Items.CUCUMBER_SEEDS)
            .addTag(TagRegistry.Items.COTTON_SEEDS)
            .addTag(TagRegistry.Items.RAPESEEDS)
            .addTag(TagRegistry.Items.SUGAR_BEET_SEEDS)
            .addTag(TagRegistry.Items.STRAWBERRY_SEEDS);
/*            .addTag(TagRegistry.Items.ONION_SEEDS)
            .addTag(TagRegistry.Items.CHILI_PEPPER_SEEDS)
            .addTag(TagRegistry.Items.RICE);

 */
        tag(TagRegistry.Items.TOMATO_SEEDS).add(ItemRegistry.TOMATO_SEEDS.get());
        tag(TagRegistry.Items.LETTUCE_SEEDS).add(ItemRegistry.LETTUCE_SEEDS.get());
        tag(TagRegistry.Items.CORN_SEEDS).add(ItemRegistry.CORN_SEEDS.get());
        tag(TagRegistry.Items.CUCUMBER_SEEDS).add(ItemRegistry.CUCUMBER_SEEDS.get());
        tag(TagRegistry.Items.COTTON_SEEDS).add(ItemRegistry.COTTON_SEEDS.get());
        tag(TagRegistry.Items.RAPESEEDS).add(ItemRegistry.RAPESEEDS.get());
        tag(TagRegistry.Items.SUGAR_BEET_SEEDS).add(ItemRegistry.SUGAR_BEET_SEEDS.get());
        tag(TagRegistry.Items.STRAWBERRY_SEEDS).add(ItemRegistry.STRAWBERRY_SEEDS.get());
//      tag(TagRegistry.Items.ONION_SEEDS).add(ItemRegistry.ONION_SEEDS.get());
//     tag(TagRegistry.Items.CHILI_PEPPER_SEEDS).add(ItemRegistry.CHILI_PEPPER_SEEDS.get());
//      tag(TagRegistry.Items.RICE).add(ItemRegistry.RICE.get());

        tag(TagRegistry.Items.VEGETABLES)
            .addTag(TagRegistry.Items.TOMATO)
            .addTag(TagRegistry.Items.LETTUCE)
            .addTag(TagRegistry.Items.CORN)
            .addTag(TagRegistry.Items.CUCUMBER)
            .addTag(TagRegistry.Items.BEETROOT)
            .addTag(TagRegistry.Items.SUGAR_BEET);
//            .addTag(TagRegistry.Items.ONION)
//            .addTag(TagRegistry.Items.CHILI_PEPPER);

        tag(TagRegistry.Items.TOMATO).add(ItemRegistry.TOMATO.get());
        tag(TagRegistry.Items.LETTUCE).add(ItemRegistry.LETTUCE.get());
        tag(TagRegistry.Items.CORN).add(ItemRegistry.CORN.get());
        tag(TagRegistry.Items.CUCUMBER).add(ItemRegistry.CUCUMBER.get());
        tag(TagRegistry.Items.BEETROOT).add(Items.BEETROOT).add(ItemRegistry.SUGAR_BEET.get());
        tag(TagRegistry.Items.SUGAR_BEET).add(ItemRegistry.SUGAR_BEET.get());
//        tag(TagRegistry.Items.ONION).add(ItemRegistry.ONION.get());
//        tag(TagRegistry.Items.CHILI_PEPPER).add(ItemRegistry.CHILI_PEPPER.get());

        tag(TagRegistry.Items.FRUITS)
                .addTag(TagRegistry.Items.STRAWBERRY)
                .addTag(TagRegistry.Items.UNRIPE_STRAWBERRY)
                .addTag(TagRegistry.Items.BLUEBERRY);

        tag(TagRegistry.Items.STRAWBERRY).add(ItemRegistry.STRAWBERRY.get());
        tag(TagRegistry.Items.UNRIPE_STRAWBERRY).add(ItemRegistry.UNRIPE_STRAWBERRY.get());
        tag(TagRegistry.Items.BLUEBERRY).add(ItemRegistry.BLUEBERRY.get());

        tag(TagRegistry.Items.CROPS)
                .addTag(TagRegistry.Items.CROPS_TOMATO)
                .addTag(TagRegistry.Items.CROPS_LETTUCE)
                .addTag(TagRegistry.Items.CROPS_CORN)
                .addTag(TagRegistry.Items.CROPS_CUCUMBER)
                .addTag(TagRegistry.Items.CROPS_COTTON)
                .addTag(TagRegistry.Items.CROPS_RAPESEED)
                .addTag(TagRegistry.Items.CROPS_SUGAR_BEET)
                .addTag(TagRegistry.Items.CROPS_STRAWBERRY)
//                .addTag(TagRegistry.Items.CROPS_ONION)
        ;
        tag(TagRegistry.Items.CROPS_TOMATO).add(ItemRegistry.TOMATO.get());
        tag(TagRegistry.Items.CROPS_LETTUCE).add(ItemRegistry.LETTUCE.get());
        tag(TagRegistry.Items.CROPS_CORN).add(ItemRegistry.CORN.get());
        tag(TagRegistry.Items.CROPS_CUCUMBER).add(ItemRegistry.CUCUMBER.get());
        tag(TagRegistry.Items.CROPS_COTTON).add(ItemRegistry.COTTON.get());
        tag(TagRegistry.Items.CROPS_RAPESEED).add(ItemRegistry.RAPESEEDS.get());
        tag(TagRegistry.Items.CROPS_SUGAR_BEET).add(ItemRegistry.SUGAR_BEET.get());
        tag(TagRegistry.Items.CROPS_STRAWBERRY).add(ItemRegistry.STRAWBERRY.get());
//        tag(TagRegistry.Items.CROPS_ONION).add(ItemRegistry.ONION.get());

        tag(TagRegistry.Items.FOODS)
//              .addTag(TagRegistry.Items.LOBSTER)
//              .addTag(TagRegistry.Items.RAW_LOBSTER)
//              .addTag(TagRegistry.Items.SUSHI)
//              .addTag(TagRegistry.Items.CUCUMBER_SUSHI)
//              .addTag(TagRegistry.Items.LOBSTER_SUSHI)
                .addTag(TagRegistry.Items.FRIED_EGG)
                .addTag(TagRegistry.Items.SCRAMBLED_EGG)
                .addTag(TagRegistry.Items.BOILED_EGG)

                .addTag(TagRegistry.Items.TOAST_HONEY)
                .addTag(TagRegistry.Items.TOAST_STRAWBERRY)
                .addTag(TagRegistry.Items.TOAST_BLUEBERRY)

                .addTag(TagRegistry.Items.STRAWBERRY_JAM)
                .addTag(TagRegistry.Items.BLUEBERRY_JAM)

                .addTag(TagRegistry.Items.TOAST_FRIED_EGG)
                .addTag(TagRegistry.Items.TOAST_SCRAMBLED_EGG)
                .addTag(TagRegistry.Items.TOAST_BOILED_EGG)

                .addTag(TagRegistry.Items.TOAST_LOAF)
                .addTag(TagRegistry.Items.TOAST)
                .addTag(TagRegistry.Items.TOAST_SLICE);
//
//        tag(TagRegistry.Items.LOBSTER).add(ItemRegistry.LOBSTER.get());
//        tag(TagRegistry.Items.RAW_LOBSTER).add(ItemRegistry.RAW_LOBSTER.get());
//        tag(TagRegistry.Items.SUSHI).add(ItemRegistry.SUSHI.get());
//        tag(TagRegistry.Items.CUCUMBER_SUSHI).add(ItemRegistry.CUCUMBER_SUSHI.get());
//        tag(TagRegistry.Items.LOBSTER_SUSHI).add(ItemRegistry.LOBSTER_SUSHI.get());
        tag(TagRegistry.Items.FRIED_EGG).add(ItemRegistry.FRIED_EGG.get());
        tag(TagRegistry.Items.SCRAMBLED_EGG).add(ItemRegistry.SCRAMBLED_EGG.get());
        tag(TagRegistry.Items.BOILED_EGG).add(ItemRegistry.BOILED_EGG.get());

        tag(TagRegistry.Items.TOAST_HONEY).add(ItemRegistry.HONEY_TOAST.get());
        tag(TagRegistry.Items.TOAST_STRAWBERRY).add(ItemRegistry.STRAWBERRY_TOAST.get());
        tag(TagRegistry.Items.TOAST_BLUEBERRY).add(ItemRegistry.BLUEBERRY_TOAST.get());

        tag(TagRegistry.Items.HONEY_JAR).add(ItemRegistry.HONEY_JAR.get());
        tag(TagRegistry.Items.STRAWBERRY_JAM).add(ItemRegistry.STRAWBERRY_JAM.get());
        tag(TagRegistry.Items.BLUEBERRY_JAM).add(ItemRegistry.BLUEBERRY_JAM.get());

        tag(TagRegistry.Items.TOAST_FRIED_EGG).add(ItemRegistry.FRIED_EGG_TOAST.get());
        tag(TagRegistry.Items.TOAST_SCRAMBLED_EGG).add(ItemRegistry.SCRAMBLED_EGG_TOAST.get());
        tag(TagRegistry.Items.TOAST_BOILED_EGG).add(ItemRegistry.BOILED_EGG_TOAST.get());

        tag(TagRegistry.Items.TOAST_LOAF).add(ItemRegistry.TOAST_LOAF.get());
        tag(TagRegistry.Items.TOAST).add(ItemRegistry.TOAST.get());
        tag(TagRegistry.Items.TOAST_SLICE).add(ItemRegistry.TOAST_SLICE.get());

        tag(TagRegistry.Items.POPCORN).add(ItemRegistry.POPCORN.get());

        tag(ItemTags.VILLAGER_PLANTABLE_SEEDS)
                .add(ItemRegistry.TOMATO_SEEDS.get())
                .add(ItemRegistry.LETTUCE_SEEDS.get())
                .add(ItemRegistry.CORN_SEEDS.get())
                .add(ItemRegistry.CUCUMBER_SEEDS.get())
    //            .add(ItemRegistry.COFFEE_SEEDS.get())
//                .add(ItemRegistry.ONION_SEEDS.get())
//                .add(ItemRegistry.CHILI_PEPPER_SEEDS.get())
        ;

        tag(TagRegistry.Items.SALAD_INGREDIENTS).addTag(TagRegistry.Items.SALAD_INGREDIENTS_LETTUCE);
        tag(TagRegistry.Items.SALAD_INGREDIENTS_LETTUCE).add(ItemRegistry.LETTUCE.get());

        tag(TagRegistry.Items.TOOLS)
                .addTag(TagRegistry.Items.KNIVES)
                .addTag(TagRegistry.Items.SCYTHES);
        ItemRegistry.ITEMS.getEntries().stream().map(RegistryObject::get).filter(item -> item instanceof KnifeItem).forEach(item -> {
            tag(TagRegistry.Items.KNIVES).add(item);
            tag(TagRegistry.Items.FARMERS_DELIGHT_KNIVES).add(item);
        });
        ItemRegistry.ITEMS.getEntries().stream().map(RegistryObject::get).filter(item -> item instanceof ScytheItem).forEach(item -> tag(TagRegistry.Items.SCYTHES).add(item));

        tag(TagRegistry.Items.ITEMS)
                .addTag(TagRegistry.Items.SEED_SATCHEL)

                .addTag(TagRegistry.Items.JAR)

                .addTag(TagRegistry.Items.FLOUR)
                .addTag(TagRegistry.Items.DOUGH);

        tag(TagRegistry.Items.SEED_SATCHEL).add(ItemRegistry.SEED_SATCHEL.get());
        tag(TagRegistry.Items.JAR).add(ItemRegistry.JAR.get());
        tag(TagRegistry.Items.FLOUR).add(ItemRegistry.FLOUR.get());
        tag(TagRegistry.Items.DOUGH).add(ItemRegistry.DOUGH.get());


        tag(ItemTags.PIGLIN_LOVED).add(ItemRegistry.GOLDEN_KNIFE.get()).add(ItemRegistry.GOLDEN_SCYTHE.get());

        tag(TagRegistry.Items.BUCKETS_WATER).add(Items.WATER_BUCKET);
        tag(TagRegistry.Items.BUCKETS_MILK).add(Items.MILK_BUCKET);
        tag(TagRegistry.Items.BOTTLES_MILK).addTag(TagRegistry.Items.MILK_MILK_BOTTLES);
        tag(TagRegistry.Items.MILK_MILK_BOTTLES);

        tag(TagRegistry.Items.FABRICS)
                .add(ItemRegistry.FABRIC.get())
                .addTag(TagRegistry.Items.FABRICS_COLORED);

        tag(TagRegistry.Items.FABRICS_COLORED)
                .add(ItemRegistry.BLACK_FABRIC.get())
                .add(ItemRegistry.BLUE_FABRIC.get())
                .add(ItemRegistry.BROWN_FABRIC.get())
                .add(ItemRegistry.CYAN_FABRIC.get())
                .add(ItemRegistry.GRAY_FABRIC.get())
                .add(ItemRegistry.GREEN_FABRIC.get())
                .add(ItemRegistry.LIGHT_BLUE_FABRIC.get())
                .add(ItemRegistry.LIGHT_GRAY_FABRIC.get())
                .add(ItemRegistry.LIME_FABRIC.get())
                .add(ItemRegistry.MAGENTA_FABRIC.get())
                .add(ItemRegistry.ORANGE_FABRIC.get())
                .add(ItemRegistry.PINK_FABRIC.get())
                .add(ItemRegistry.PURPLE_FABRIC.get())
                .add(ItemRegistry.RED_FABRIC.get())
                .add(ItemRegistry.WHITE_FABRIC.get())
                .add(ItemRegistry.YELLOW_FABRIC.get());

        tag(ItemTags.WOOL).addTag(TagRegistry.Items.FABRICS);

        tag(TagRegistry.Items.WOOLS_BLACK).add(Items.BLACK_WOOL).add(ItemRegistry.BLACK_FABRIC.get());
        tag(TagRegistry.Items.WOOLS_BLUE).add(Items.BLUE_WOOL).add(ItemRegistry.BLUE_FABRIC.get());
        tag(TagRegistry.Items.WOOLS_BROWN).add(Items.BROWN_WOOL).add(ItemRegistry.BROWN_FABRIC.get());
        tag(TagRegistry.Items.WOOLS_CYAN).add(Items.CYAN_WOOL).add(ItemRegistry.CYAN_FABRIC.get());
        tag(TagRegistry.Items.WOOLS_GRAY).add(Items.GRAY_WOOL).add(ItemRegistry.GRAY_FABRIC.get());
        tag(TagRegistry.Items.WOOLS_GREEN).add(Items.GREEN_WOOL).add(ItemRegistry.GREEN_FABRIC.get());
        tag(TagRegistry.Items.WOOLS_LIGHT_BLUE).add(Items.LIGHT_BLUE_WOOL).add(ItemRegistry.LIGHT_BLUE_FABRIC.get());
        tag(TagRegistry.Items.WOOLS_LIGHT_GRAY).add(Items.LIGHT_GRAY_WOOL).add(ItemRegistry.LIGHT_GRAY_FABRIC.get());
        tag(TagRegistry.Items.WOOLS_LIME).add(Items.LIME_WOOL).add(ItemRegistry.LIME_FABRIC.get());
        tag(TagRegistry.Items.WOOLS_MAGENTA).add(Items.MAGENTA_WOOL).add(ItemRegistry.MAGENTA_FABRIC.get());
        tag(TagRegistry.Items.WOOLS_ORANGE).add(Items.ORANGE_WOOL).add(ItemRegistry.ORANGE_FABRIC.get());
        tag(TagRegistry.Items.WOOLS_PINK).add(Items.PINK_WOOL).add(ItemRegistry.PINK_FABRIC.get());
        tag(TagRegistry.Items.WOOLS_PURPLE).add(Items.PURPLE_WOOL).add(ItemRegistry.PURPLE_FABRIC.get());
        tag(TagRegistry.Items.WOOLS_RED).add(Items.RED_WOOL).add(ItemRegistry.RED_FABRIC.get());
        tag(TagRegistry.Items.WOOLS_WHITE).add(Items.WHITE_WOOL).add(ItemRegistry.WHITE_FABRIC.get());
        tag(TagRegistry.Items.WOOLS_YELLOW).add(Items.YELLOW_WOOL).add(ItemRegistry.YELLOW_FABRIC.get());

        copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        copy(BlockTags.LOGS, ItemTags.LOGS);
        copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        copy(TagRegistry.Blocks.RUBBER_LOGS, TagRegistry.Items.RUBBER_LOGS);
        copy(BlockTags.PLANKS, ItemTags.PLANKS);

        tag(TagRegistry.Items.HIGH_TIER_HOES)
                .add(ItemRegistry.DIAMOND_SCYTHE.get())
                .add(ItemRegistry.NETHERITE_SCYTHE.get());

        tag(TagRegistry.Items.MID_TIER_HOES)
                .add(ItemRegistry.GOLDEN_SCYTHE.get())
                .add(ItemRegistry.IRON_SCYTHE.get());

        tag(TagRegistry.Items.LOW_TIER_HOES)
                .add(ItemRegistry.COPPER_SCYTHE.get())
                .add(ItemRegistry.STONE_SCYTHE.get());

        tag(TagRegistry.Items.SLABS_TILLABLE)
                .add(BlockRegistry.DIRT_SLAB.get().asItem())
                .add(BlockRegistry.DIRT_PATH_SLAB.get().asItem())
                .add(BlockRegistry.DIRT_TRACK_SLAB.get().asItem())
                .add(BlockRegistry.GRASS_SLAB.get().asItem());

        tag(TagRegistry.Items.BLOCKS_TILLABLE)
                .add(Items.DIRT)
                .add(Items.DIRT_PATH)
                .add(BlockRegistry.DIRT_TRACK.get().asItem())
                .add(Items.GRASS_BLOCK);

        tag(ItemTags.SLABS).addTag(TagRegistry.Items.SLABS_TILLABLE).add(BlockRegistry.FARMLAND_SLAB.get().asItem());

    }
}

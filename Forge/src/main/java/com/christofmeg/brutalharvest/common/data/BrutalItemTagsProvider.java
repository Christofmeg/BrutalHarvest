package com.christofmeg.brutalharvest.common.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import com.christofmeg.brutalharvest.common.init.TagRegistry;
import com.christofmeg.brutalharvest.common.item.KnifeItem;
import com.christofmeg.brutalharvest.common.item.ScytheItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
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
            .addTag(TagRegistry.Items.STRAWBERRY_SEEDS)
            .addTag(TagRegistry.Items.ONION_SEEDS);
        tag(TagRegistry.Items.TOMATO_SEEDS).add(ItemRegistry.TOMATO_SEEDS.get());
        tag(TagRegistry.Items.LETTUCE_SEEDS).add(ItemRegistry.LETTUCE_SEEDS.get());
        tag(TagRegistry.Items.CORN_SEEDS).add(ItemRegistry.CORN_SEEDS.get());
        tag(TagRegistry.Items.CUCUMBER_SEEDS).add(ItemRegistry.CUCUMBER_SEEDS.get());
        tag(TagRegistry.Items.COTTON_SEEDS).add(ItemRegistry.COTTON_SEEDS.get());
        tag(TagRegistry.Items.RAPESEEDS).add(ItemRegistry.RAPESEEDS.get());
        tag(TagRegistry.Items.SUGAR_BEET_SEEDS).add(ItemRegistry.SUGAR_BEET_SEEDS.get());
        tag(TagRegistry.Items.STRAWBERRY_SEEDS).add(ItemRegistry.STRAWBERRY_SEEDS.get());
        tag(TagRegistry.Items.ONION_SEEDS).add(ItemRegistry.ONION_SEEDS.get());

        tag(TagRegistry.Items.VEGETABLES)
            .addTag(TagRegistry.Items.TOMATO)
            .addTag(TagRegistry.Items.LETTUCE)
            .addTag(TagRegistry.Items.CORN)
            .addTag(TagRegistry.Items.CUCUMBER)
            .addTag(TagRegistry.Items.BEETROOT)
            .addTag(TagRegistry.Items.SUGAR_BEET)
//          .addTag(TagRegistry.Items.ONTION)
            ;
        tag(TagRegistry.Items.TOMATO).add(ItemRegistry.TOMATO.get());
        tag(TagRegistry.Items.LETTUCE).add(ItemRegistry.LETTUCE.get());
        tag(TagRegistry.Items.CORN).add(ItemRegistry.CORN.get());
        tag(TagRegistry.Items.CUCUMBER).add(ItemRegistry.CUCUMBER.get());
        tag(TagRegistry.Items.BEETROOT).add(Items.BEETROOT).add(ItemRegistry.SUGAR_BEET.get());
        tag(TagRegistry.Items.SUGAR_BEET).add(ItemRegistry.SUGAR_BEET.get());
//        tag(TagRegistry.Items.ONTION).add(ItemRegistry.ONTION.get());

        tag(TagRegistry.Items.CROPS)
                .addTag(TagRegistry.Items.CROPS_TOMATO)
                .addTag(TagRegistry.Items.CROPS_LETTUCE)
                .addTag(TagRegistry.Items.CROPS_CORN)
                .addTag(TagRegistry.Items.CROPS_CUCUMBER)
                .addTag(TagRegistry.Items.CROPS_SUGAR_BEET)
//                .addTag(TagRegistry.Items.CROPS_ONION)
        ;
        tag(TagRegistry.Items.CROPS_TOMATO).add(ItemRegistry.TOMATO.get());
        tag(TagRegistry.Items.CROPS_LETTUCE).add(ItemRegistry.LETTUCE.get());
        tag(TagRegistry.Items.CROPS_CORN).add(ItemRegistry.CORN.get());
        tag(TagRegistry.Items.CROPS_CUCUMBER).add(ItemRegistry.CUCUMBER.get());
        tag(TagRegistry.Items.CROPS_SUGAR_BEET).add(ItemRegistry.SUGAR_BEET.get());
//        tag(TagRegistry.Items.CROPS_ONION).add(ItemRegistry.ONION.get());

        tag(TagRegistry.Items.CROPS_COTTON).add(ItemRegistry.COTTON.get());

        tag(ItemTags.VILLAGER_PLANTABLE_SEEDS)
                .add(ItemRegistry.TOMATO_SEEDS.get())
    //            .add(ItemRegistry.LETTUCE_SEEDS.get())
                .add(ItemRegistry.CORN_SEEDS.get())
    //            .add(ItemRegistry.CUCUMBER_SEEDS.get())
    //            .add(ItemRegistry.COFFEE_SEEDS.get())
    //            .add(ItemRegistry.ONION_SEEDS.get())
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
        ItemRegistry.ITEMS.getEntries().stream().map(RegistryObject::get).filter(item -> item instanceof ScytheItem).forEach(item -> {
            tag(TagRegistry.Items.SCYTHES).add(item);
        });

        tag(ItemTags.PIGLIN_LOVED).add(ItemRegistry.GOLDEN_KNIFE.get()).add(ItemRegistry.GOLDEN_SCYTHE.get());

        tag(TagRegistry.Items.BUCKETS_WATER).add(Items.WATER_BUCKET);

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

    }
}

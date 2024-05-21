package com.christofmeg.brutalharvest.common.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import com.christofmeg.brutalharvest.common.init.TagRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
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
            .addTag(TagRegistry.Items.CUCUMBER_SEEDS);
        tag(TagRegistry.Items.TOMATO_SEEDS).add(ItemRegistry.TOMATO_SEEDS.get());
        tag(TagRegistry.Items.LETTUCE_SEEDS).add(ItemRegistry.LETTUCE_SEEDS.get());
        tag(TagRegistry.Items.CORN_SEEDS).add(ItemRegistry.CORN_SEEDS.get());
        tag(TagRegistry.Items.CUCUMBER_SEEDS).add(ItemRegistry.CUCUMBER_SEEDS.get());

        tag(TagRegistry.Items.VEGETABLES)
            .addTag(TagRegistry.Items.TOMATO)
            .addTag(TagRegistry.Items.LETTUCE)
            .addTag(TagRegistry.Items.CORN)
            .addTag(TagRegistry.Items.CUCUMBER);
        tag(TagRegistry.Items.TOMATO).add(ItemRegistry.TOMATO.get());
        tag(TagRegistry.Items.LETTUCE).add(ItemRegistry.LETTUCE.get());
        tag(TagRegistry.Items.CORN).add(ItemRegistry.CORN.get());
        tag(TagRegistry.Items.CUCUMBER).add(ItemRegistry.CUCUMBER.get());

        tag(TagRegistry.Items.SALAD_INGREDIENTS).addTag(TagRegistry.Items.SALAD_INGREDIENTS_LETTUCE);
        tag(TagRegistry.Items.SALAD_INGREDIENTS_LETTUCE).add(ItemRegistry.LETTUCE.get());

    }
}

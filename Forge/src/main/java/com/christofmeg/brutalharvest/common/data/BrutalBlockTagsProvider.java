package com.christofmeg.brutalharvest.common.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import com.christofmeg.brutalharvest.common.init.TagRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BrutalBlockTagsProvider extends BlockTagsProvider {

    public BrutalBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CommonConstants.MOD_ID, existingFileHelper);
    }

    @Override
    public @NotNull String getName() {
        return CommonConstants.MOD_NAME + " - Block Tags";
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {

        tag(BlockTags.CROPS)
                .add(BlockRegistry.TOMATO.get())
                .add(BlockRegistry.LETTUCE.get())
                .add(BlockRegistry.CORN.get())
                .add(BlockRegistry.CUCUMBER.get())
                .add(BlockRegistry.COTTON.get())
                .add(BlockRegistry.RAPESEED.get())
                .add(BlockRegistry.SUGAR_BEET.get())
                .add(BlockRegistry.STRAWBERRY.get());
//                .add(BlockRegistry.ONION.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(BlockRegistry.TOMATO.get())
                .add(BlockRegistry.LETTUCE.get())
                .add(BlockRegistry.CORN.get())
                .add(BlockRegistry.CUCUMBER.get())
                .add(BlockRegistry.COTTON.get())
                .add(BlockRegistry.RAPESEED.get())
                .add(BlockRegistry.SUGAR_BEET.get())
                .add(BlockRegistry.STRAWBERRY.get())
//                .add(BlockRegistry.ONION.get())
                .add(BlockRegistry.BLUEBERRY.get())
        ;

        tag(BlockTags.MAINTAINS_FARMLAND)
                .add(BlockRegistry.TOMATO.get())
                .add(BlockRegistry.LETTUCE.get())
                .add(BlockRegistry.CORN.get())
                .add(BlockRegistry.CUCUMBER.get())
                .add(BlockRegistry.COTTON.get())
                .add(BlockRegistry.RAPESEED.get())
                .add(BlockRegistry.SUGAR_BEET.get())
                .add(BlockRegistry.STRAWBERRY.get());
//                .add(BlockRegistry.ONION.get());

        tag(BlockTags.BEE_GROWABLES)
                .add(BlockRegistry.BLUEBERRY.get());
        tag(BlockTags.FALL_DAMAGE_RESETTING)
                .add(BlockRegistry.BLUEBERRY.get());
        tag(BlockTags.SWORD_EFFICIENT)
                .add(BlockRegistry.BLUEBERRY.get());
        tag(TagRegistry.Blocks.AE2_GROWTH_ACCELERATABLE)
                .add(BlockRegistry.BLUEBERRY.get());

        tag(BlockTags.SAPLINGS)
                .add(BlockRegistry.RUBBER_SAPLING.get());

        tag(BlockTags.LOGS)
                .addTag(TagRegistry.Blocks.RUBBER_LOGS);

        tag(TagRegistry.Blocks.RUBBER_LOGS)
                .add(BlockRegistry.RUBBER_LOG.get())
                .add(BlockRegistry.RUBBER_WOOD.get())
                .add(BlockRegistry.STRIPPED_RUBBER_LOG.get())
                .add(BlockRegistry.STRIPPED_RUBBER_WOOD.get())
                .add(BlockRegistry.RUBBER_LOG_GENERATED.get());

        tag(BlockTags.LOGS_THAT_BURN)
                .addTag(TagRegistry.Blocks.RUBBER_LOGS);

        tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(BlockRegistry.RUBBER_LOG.get())
                .add(BlockRegistry.RUBBER_LOG_GENERATED.get());

        tag(BlockTags.SNAPS_GOAT_HORN)
                .add(BlockRegistry.RUBBER_LOG.get())
                .add(BlockRegistry.RUBBER_LOG_GENERATED.get());

        tag(BlockTags.PLANKS)
                .add(BlockRegistry.RUBBER_PLANKS.get());

        tag(BlockTags.LEAVES)
                .add(BlockRegistry.RUBBER_LEAVES.get());

        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(BlockRegistry.RUBBER_LEAVES.get());

        tag(TagRegistry.Blocks.MINEABLE_WITH_SCYTHE)
                .addTag(BlockTags.CROPS)
                .addTag(BlockTags.FLOWERS)
                .addTag(BlockTags.REPLACEABLE_BY_TREES);

        tag(BlockTags.NETHER_CARVER_REPLACEABLES).add(BlockRegistry.DIRT_SLAB.get()).add(BlockRegistry.GRASS_SLAB.get());
        tag(BlockTags.SNIFFER_DIGGABLE_BLOCK).add(BlockRegistry.DIRT_SLAB.get()).add(BlockRegistry.GRASS_SLAB.get());
        tag(BlockTags.LUSH_GROUND_REPLACEABLE).add(BlockRegistry.DIRT_SLAB.get()).add(BlockRegistry.GRASS_SLAB.get());
        tag(BlockTags.ENDERMAN_HOLDABLE).add(BlockRegistry.DIRT_SLAB.get()).add(BlockRegistry.GRASS_SLAB.get());
        tag(BlockTags.MOSS_REPLACEABLE).add(BlockRegistry.DIRT_SLAB.get()).add(BlockRegistry.GRASS_SLAB.get());
        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(BlockRegistry.DIRT_SLAB.get())
                .add(BlockRegistry.FARMLAND_SLAB.get())
                .add(BlockRegistry.DIRT_PATH_SLAB.get())
                .add(BlockRegistry.DIRT_TRACK_SLAB.get())
                .add(BlockRegistry.GRASS_SLAB.get())
                .add(BlockRegistry.DIRT_TRACK.get())
        ;
        tag(BlockTags.SCULK_REPLACEABLE_WORLD_GEN).add(BlockRegistry.DIRT_SLAB.get()).add(BlockRegistry.GRASS_SLAB.get());
        tag(BlockTags.AZALEA_ROOT_REPLACEABLE).add(BlockRegistry.DIRT_SLAB.get()).add(BlockRegistry.GRASS_SLAB.get());
        tag(BlockTags.SCULK_REPLACEABLE).add(BlockRegistry.DIRT_SLAB.get()).add(BlockRegistry.GRASS_SLAB.get());
        tag(BlockTags.OVERWORLD_CARVER_REPLACEABLES).add(BlockRegistry.DIRT_SLAB.get()).add(BlockRegistry.GRASS_SLAB.get());
        tag(BlockTags.DIRT).add(BlockRegistry.DIRT_SLAB.get()).add(BlockRegistry.GRASS_SLAB.get());

        tag(TagRegistry.Blocks.SLABS_DIRT)
                .add(BlockRegistry.DIRT_SLAB.get())
                .add(BlockRegistry.FARMLAND_SLAB.get())
                .add(BlockRegistry.DIRT_PATH_SLAB.get())
                .add(BlockRegistry.DIRT_TRACK_SLAB.get())
                .add(BlockRegistry.GRASS_SLAB.get());

        tag(BlockTags.SLABS).addTag(TagRegistry.Blocks.SLABS_DIRT);

    }
}
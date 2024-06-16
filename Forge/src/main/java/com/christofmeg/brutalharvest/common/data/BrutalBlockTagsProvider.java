package com.christofmeg.brutalharvest.common.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.BlockRegistry;
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
                .add(BlockRegistry.CORN.get())
                .add(BlockRegistry.COTTON.get())
                .add(BlockRegistry.SUGAR_BEET.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(BlockRegistry.TOMATO.get())
                .add(BlockRegistry.CORN.get())
                .add(BlockRegistry.COTTON.get())
                .add(BlockRegistry.SUGAR_BEET.get());

        tag(BlockTags.MAINTAINS_FARMLAND)
                .add(BlockRegistry.TOMATO.get())
                .add(BlockRegistry.CORN.get())
                .add(BlockRegistry.COTTON.get())
                .add(BlockRegistry.SUGAR_BEET.get());

        tag(BlockTags.BEE_GROWABLES)
                .add(BlockRegistry.TOMATO.get())
                .add(BlockRegistry.CORN.get())
                .add(BlockRegistry.COTTON.get())
                .add(BlockRegistry.SUGAR_BEET.get());

        tag(BlockTags.SWORD_EFFICIENT)
                .add(BlockRegistry.TOMATO.get())
                .add(BlockRegistry.CORN.get())
                .add(BlockRegistry.COTTON.get())
                .add(BlockRegistry.SUGAR_BEET.get());

    }
}
package com.christofmeg.brutalharvest.client.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import com.christofmeg.brutalharvest.common.item.ScytheItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BrutalItemModelProvider extends ItemModelProvider {

    public BrutalItemModelProvider(PackOutput generator, ExistingFileHelper existingFileHelper) {
        super(generator, CommonConstants.MOD_ID, existingFileHelper);
    }

    @Override
    public @NotNull String getName() {
        return CommonConstants.MOD_ID + " - ItemModel";
    }

    @Override
    protected void registerModels() {
        ItemRegistry.ITEMS.getEntries().stream().map(RegistryObject::get)
                .filter(item -> (!(item instanceof BlockItem)) && (!(item instanceof ScytheItem)))
                .forEach(this::basicItem);

        ItemRegistry.ITEMS.getEntries().stream().map(RegistryObject::get)
                .filter(item -> (item instanceof ItemNameBlockItem))
                .forEach(this::basicItem);

        ItemRegistry.ITEMS.getEntries().stream().map(RegistryObject::get)
                .filter(item -> (item instanceof ScytheItem))
                .forEach(this::handHeldItem);

        saplingItem(BlockRegistry.RUBBER_SAPLING);
        withExistingParent(getItemName(BlockRegistry.RUBBER_LOG.get()), modLoc("block/" + getItemName(BlockRegistry.RUBBER_LOG.get())));
        withExistingParent(getItemName(BlockRegistry.RUBBER_WOOD.get()), modLoc("block/" + getItemName(BlockRegistry.RUBBER_WOOD.get())));
        withExistingParent(getItemName(BlockRegistry.STRIPPED_RUBBER_LOG.get()), modLoc("block/" + getItemName(BlockRegistry.STRIPPED_RUBBER_LOG.get())));
        withExistingParent(getItemName(BlockRegistry.STRIPPED_RUBBER_WOOD.get()), modLoc("block/" + getItemName(BlockRegistry.STRIPPED_RUBBER_WOOD.get())));
        withExistingParent(getItemName(BlockRegistry.RUBBER_PLANKS.get()), modLoc("block/" + getItemName(BlockRegistry.RUBBER_PLANKS.get())));
        withExistingParent(getItemName(BlockRegistry.RUBBER_LEAVES.get()), modLoc("block/" + getItemName(BlockRegistry.RUBBER_LEAVES.get())));
        withExistingParent(getItemName(BlockRegistry.RUBBER_LOG_GENERATED.get()), modLoc("block/" + getItemName(BlockRegistry.RUBBER_LOG.get())));
        withExistingParent(getItemName(BlockRegistry.FARMLAND_SLAB.get()), modLoc("block/" + getItemName(BlockRegistry.FARMLAND_SLAB.get())));
        withExistingParent(getItemName(BlockRegistry.DIRT_SLAB.get()), modLoc("block/" + getItemName(BlockRegistry.DIRT_SLAB.get())));
        withExistingParent(getItemName(BlockRegistry.GRASS_SLAB.get()), modLoc("block/" + getItemName(BlockRegistry.GRASS_SLAB.get())));
        withExistingParent(getItemName(BlockRegistry.DIRT_PATH_SLAB.get()), modLoc("block/" + getItemName(BlockRegistry.DIRT_PATH_SLAB.get())));
        withExistingParent(getItemName(BlockRegistry.DIRT_TRACK_SLAB.get()), modLoc("block/" + getItemName(BlockRegistry.DIRT_TRACK_SLAB.get())));
        withExistingParent(getItemName(BlockRegistry.DIRT_TRACK.get()), modLoc("block/" + getItemName(BlockRegistry.DIRT_TRACK.get())));

    }

    @SuppressWarnings("deprecation")
    private String getItemName(ItemLike pItemLike) {
        return BuiltInRegistries.ITEM.getKey(pItemLike.asItem()).getPath();
    }

    private void saplingItem(RegistryObject<Block> item) {
        withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CommonConstants.MOD_ID, "block/" + item.getId().getPath()));
    }

    private void handHeldItem(Item item) {
        this.handHeldItem(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)));
    }

    private void handHeldItem(ResourceLocation item) {
        this.getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile("item/handheld")).texture("layer0", new ResourceLocation(item.getNamespace(), "item/" + item.getPath()));
    }

}
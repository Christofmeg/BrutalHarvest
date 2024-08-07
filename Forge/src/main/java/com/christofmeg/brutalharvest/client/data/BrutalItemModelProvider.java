package com.christofmeg.brutalharvest.client.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

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
                .filter(item -> (!(item instanceof BlockItem)))
                .forEach(this::basicItem);

        ItemRegistry.ITEMS.getEntries().stream().map(RegistryObject::get)
                .filter(item -> (item instanceof ItemNameBlockItem))
                .forEach(this::basicItem);

//        withExistingParent(getItemName(BlockRegistry.RUBBER_SAPLING.get()), modLoc("block/" + getItemName(BlockRegistry.RUBBER_SAPLING.get())));
        withExistingParent(getItemName(BlockRegistry.RUBBER_LOG.get()), modLoc("block/" + getItemName(BlockRegistry.RUBBER_LOG.get())));
        withExistingParent(getItemName(BlockRegistry.RUBBER_WOOD.get()), modLoc("block/" + getItemName(BlockRegistry.RUBBER_WOOD.get())));
        withExistingParent(getItemName(BlockRegistry.STRIPPED_RUBBER_LOG.get()), modLoc("block/" + getItemName(BlockRegistry.STRIPPED_RUBBER_LOG.get())));
        withExistingParent(getItemName(BlockRegistry.STRIPPED_RUBBER_WOOD.get()), modLoc("block/" + getItemName(BlockRegistry.STRIPPED_RUBBER_WOOD.get())));
        withExistingParent(getItemName(BlockRegistry.RUBBER_PLANKS.get()), modLoc("block/" + getItemName(BlockRegistry.RUBBER_PLANKS.get())));
        withExistingParent(getItemName(BlockRegistry.RUBBER_LEAVES.get()), modLoc("block/" + getItemName(BlockRegistry.RUBBER_LEAVES.get())));

    }

    @SuppressWarnings("deprecation")
    private String getItemName(ItemLike pItemLike) {
        return BuiltInRegistries.ITEM.getKey(pItemLike.asItem()).getPath();
    }

}
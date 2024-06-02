package com.christofmeg.brutalharvest.client.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import com.christofmeg.brutalharvest.common.item.KnifeItem;
import net.minecraft.data.PackOutput;
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

        basicItem(ItemRegistry.UNRIPE_TOMATO.get());
        basicItem(ItemRegistry.TOMATO.get());
        basicItem(ItemRegistry.ROTTEN_TOMATO.get());
        basicItem(ItemRegistry.LETTUCE.get());
        basicItem(ItemRegistry.SLICED_LETTUCE.get());
        basicItem(ItemRegistry.CORN.get());
        basicItem(ItemRegistry.CUCUMBER.get());
        basicItem(ItemRegistry.CUCUMBER_SLICES.get());
        basicItem(ItemRegistry.PICKLE.get());

        basicItem(ItemRegistry.TOMATO_SEEDS.get());
        basicItem(ItemRegistry.CORN_SEEDS.get());
        basicItem(ItemRegistry.CUCUMBER_SEEDS.get());

        ItemRegistry.ITEMS.getEntries().stream().map(RegistryObject::get).filter(item -> item instanceof KnifeItem).forEach(this::basicItem);

    }

}
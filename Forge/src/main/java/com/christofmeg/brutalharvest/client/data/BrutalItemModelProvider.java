package com.christofmeg.brutalharvest.client.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
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

        basicItem(ItemRegistry.GREEN_TOMATO.get());
        basicItem(ItemRegistry.TOMATO.get());
        basicItem(ItemRegistry.ROTTEN_TOMATO.get());
        basicItem(ItemRegistry.LETTUCE.get());
        basicItem(ItemRegistry.SLICED_LETTUCE.get());
        basicItem(ItemRegistry.CORN.get());
        basicItem(ItemRegistry.CUCUMBER.get());
        basicItem(ItemRegistry.CUCUMBER_SLICE.get());
        basicItem(ItemRegistry.PICKLE.get());

    }

}
package com.christofmeg.brutalharvest.client.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
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

        ItemRegistry.ITEMS.getEntries().stream().map(RegistryObject::get).forEach((this::basicItem));

        /*
        basicItem(ItemRegistry.UNRIPE_TOMATO.get());
        basicItem(ItemRegistry.TOMATO.get());
        basicItem(ItemRegistry.ROTTEN_TOMATO.get());
        basicItem(ItemRegistry.TOMATO_SLICE.get());
        basicItem(ItemRegistry.LETTUCE.get());
        basicItem(ItemRegistry.SLICED_LETTUCE.get());
        basicItem(ItemRegistry.CORN.get());
        basicItem(ItemRegistry.CUCUMBER.get());
        basicItem(ItemRegistry.CUCUMBER_SLICES.get());
        basicItem(ItemRegistry.PICKLE.get());
        basicItem(ItemRegistry.COTTON.get());
        basicItem(ItemRegistry.SUGAR_BEET.get());

        basicItem(ItemRegistry.TOMATO_SEEDS.get());
        basicItem(ItemRegistry.LETTUCE_SEEDS.get());
        basicItem(ItemRegistry.CORN_SEEDS.get());
        basicItem(ItemRegistry.CUCUMBER_SEEDS.get());
        basicItem(ItemRegistry.COTTON_SEEDS.get());
        basicItem(ItemRegistry.RAPESEEDS.get());
        basicItem(ItemRegistry.SUGAR_BEET_SEEDS.get());
        basicItem(ItemRegistry.STRAWBERRY_SEEDS.get());
        basicItem(ItemRegistry.ONION_SEEDS.get());

        basicItem(ItemRegistry.RUBBER_BUCKET.get());
        basicItem(ItemRegistry.RUBBER.get());

        ItemRegistry.ITEMS.getEntries().stream().map(RegistryObject::get).filter(item -> item instanceof KnifeItem).forEach(this::basicItem);
        ItemRegistry.ITEMS.getEntries().stream().map(RegistryObject::get).filter(item -> item instanceof ScytheItem).forEach((this::basicItem));

        basicItem(ItemRegistry.SEED_SATCHEL.get());
        basicItem(ItemRegistry.FABRIC.get());
*/
    }

}
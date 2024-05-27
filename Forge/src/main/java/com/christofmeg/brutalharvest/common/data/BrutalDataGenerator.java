package com.christofmeg.brutalharvest.common.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.client.data.BrutalItemModelProvider;
import com.christofmeg.brutalharvest.client.data.BrutalLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = CommonConstants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class BrutalDataGenerator {

    private BrutalDataGenerator() {
    }

    private static final String[] LOCALE_CODES = new String[] { "en_us", };

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        if (event.includeClient()) {
            gen.addProvider(true, new BrutalItemModelProvider(output, existingFileHelper));
        }

        if (event.includeServer()) {
            BrutalBlockTagsProvider blockTags = gen.addProvider(true, new BrutalBlockTagsProvider(output, lookupProvider, existingFileHelper));
            gen.addProvider(true, new BrutalItemTagsProvider(output, lookupProvider, blockTags, existingFileHelper));
            gen.addProvider(true, new BrutalRecipeProvider(output));
        }

        for (String locale : LOCALE_CODES) {
            gen.addProvider(true, new BrutalLanguageProvider(output, locale));

        }

    }

}
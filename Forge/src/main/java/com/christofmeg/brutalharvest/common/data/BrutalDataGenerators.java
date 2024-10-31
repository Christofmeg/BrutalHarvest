package com.christofmeg.brutalharvest.common.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.client.data.BrutalBlockStateProvider;
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
public class BrutalDataGenerators {

    private BrutalDataGenerators() {
    }

    private static final String[] LOCALE_CODES = new String[] { "en_us", };

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        if (event.includeClient()) {
            gen.addProvider(true, new BrutalBlockStateProvider(output, existingFileHelper));
            gen.addProvider(true, new BrutalItemModelProvider(output, existingFileHelper));
        }

        if (event.includeServer()) {
            BrutalBlockTagsProvider blockTags = gen.addProvider(true, new BrutalBlockTagsProvider(output, lookupProvider, existingFileHelper));
            gen.addProvider(true, new BrutalItemTagsProvider(output, lookupProvider, blockTags, existingFileHelper));
            gen.addProvider(true, new BrutalRecipeProvider(output));
            gen.addProvider(event.includeServer(), new BrutalAdvancementProvider(output, lookupProvider, existingFileHelper));
            gen.addProvider(event.includeServer(), new BrutalGlobalLootModifierProvider(gen));
            gen.addProvider(event.includeServer(), new BrutalLootTablesProvider(gen));
            gen.addProvider(event.includeServer(), new BrutalWorldGenProvider(output, lookupProvider));
        }

        for (String locale : LOCALE_CODES) {
            gen.addProvider(true, new BrutalLanguageProvider(output, locale));
        }

    }

}
package com.christofmeg.brutalharvest.client.data;

import com.christofmeg.brutalharvest.CommonConstants;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
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
        var gen = event.getGenerator();
        var packOutput = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        DataGenerator generator = event.getGenerator();

        if (event.includeClient()) {
            gen.addProvider(true, new BrutalItemModelProvider(packOutput, existingFileHelper));
        }

        if (event.includeServer()) {

        }

        for (String locale : LOCALE_CODES) {
            gen.addProvider(true, new BrutalLanguageProvider(packOutput, locale));

        }

    }

}
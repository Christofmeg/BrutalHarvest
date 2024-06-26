package com.christofmeg.brutalharvest.client.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import org.codehaus.plexus.util.StringUtils;

public class BrutalLanguageProvider extends LanguageProvider {

    public BrutalLanguageProvider(PackOutput output, String locale) {
        super(output, CommonConstants.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        String locale = this.getName().replace("Languages: ", "");
        if (locale.equals("en_us")) {
            add("itemGroup." + CommonConstants.MOD_ID, CommonConstants.MOD_NAME);

            ItemRegistry.ITEMS.getEntries().stream().map(RegistryObject::get)
                .filter(item -> !(item == ItemRegistry.CORN_SEEDS.get()))
                .forEach(item -> addItem(() -> item,
                StringUtils.capitaliseAllWords(item.getDescription().getString()
                    .replace("item." + CommonConstants.MOD_ID + ".", "")
                        .replace("block." + CommonConstants.MOD_ID + ".", "")
                    .replace("_", " ")
                )
            ));

            addItem(ItemRegistry.CORN_SEEDS, "Corn Seeds (Kernel)");

            add(CommonConstants.MOD_ID + "." + "advancement" + "." + "root" + ".desc", "Obtain some tomatoes");
            add(CommonConstants.MOD_ID + "." + "advancement" + "." + "rotten_tomatoes", "Rotten Tomatoes");
            add(CommonConstants.MOD_ID + "." + "advancement" + "." + "rotten_tomatoes" + ".desc", "Throw a tomato at a villager");
            add(CommonConstants.MOD_ID + "." + "advancement" + "." + "grim_reaper", "Grim Reaper");
            add(CommonConstants.MOD_ID + "." + "advancement" + "." + "grim_reaper" + ".desc", "Craft a scythe");

            add("sounds." + CommonConstants.MOD_ID + "." + "tomato_splat", "Tomato Splat");

        }
    }

}
package com.christofmeg.brutalharvest.client.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import com.christofmeg.brutalharvest.common.init.EnchantmentRegistry;
import com.christofmeg.brutalharvest.common.init.EntityTypeRegistry;
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
                .filter(item -> !(item == ItemRegistry.CORN_SEEDS.get()) &&
                        !(item == BlockRegistry.RUBBER_LOG_GENERATED.get().asItem()) &&
                        !(item == ItemRegistry.CHEFS_HAT.get().asItem())
                )
                .forEach(item -> addItem(() -> item,
                StringUtils.capitaliseAllWords(item.getDescription().getString()
                    .replace("item." + CommonConstants.MOD_ID + ".", "")
                        .replace("block." + CommonConstants.MOD_ID + ".", "")
                    .replace("_", " ")
                )
            ));

            addItem(ItemRegistry.CORN_SEEDS, "Corn Seeds (Kernel)");
            addItem(ItemRegistry.CHEFS_HAT, "Chef's Hat");
            add("block." + CommonConstants.MOD_ID + "." + "rubber_log_generated", "Rubber Log");

            add(CommonConstants.MOD_ID + "." + "advancement" + "." + "root" + ".desc", "Obtain some tomatoes");
            add(CommonConstants.MOD_ID + "." + "advancement" + "." + "rotten_tomatoes", "Rotten Tomatoes");
            add(CommonConstants.MOD_ID + "." + "advancement" + "." + "rotten_tomatoes" + ".desc", "Throw a tomato at a villager");
            add(CommonConstants.MOD_ID + "." + "advancement" + "." + "grim_reaper", "Grim Reaper");
            add(CommonConstants.MOD_ID + "." + "advancement" + "." + "grim_reaper" + ".desc", "Craft a scythe");
            add(CommonConstants.MOD_ID + "." + "advancement" + "." + "reaperang", "Reaparang");
            add(CommonConstants.MOD_ID + "." + "advancement" + "." + "reaperang" + ".desc", "Launch a scythe with the Boomerang enchantment");
            add(CommonConstants.MOD_ID + "." + "advancement" + "." + "corn_seeds", "Colonel Cornelius Cornwall");
            add(CommonConstants.MOD_ID + "." + "advancement" + "." + "corn_seeds" + ".desc", "Plant a Corn Seed (Kernel)");
            add(CommonConstants.MOD_ID + "." + "advancement" + "." + "corn", "IT'S CORN");
            add(CommonConstants.MOD_ID + "." + "advancement" + "." + "corn" + ".desc", "Obtain some corn");

            add("sounds." + CommonConstants.MOD_ID + "." + "tomato_splat", "Tomato Splat");

            EnchantmentRegistry.ENCHANTMENTS.getEntries().stream().map(RegistryObject::get)
                .forEach(enchantment -> addEnchantment(() -> enchantment,
                    StringUtils.capitaliseAllWords(enchantment.getDescriptionId()
                        .replace("enchantment." + CommonConstants.MOD_ID + ".", "")
                    )
                ));

            addEntityType(EntityTypeRegistry.TOMATO_PROJECTILE, "Tomato");
            addEntityType(EntityTypeRegistry.THROWN_SCYTHE, "Scythe");

        }
    }

}
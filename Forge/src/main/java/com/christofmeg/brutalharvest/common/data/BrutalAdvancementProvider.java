package com.christofmeg.brutalharvest.common.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.advancement.TomatoProjectileTrigger;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.RecipeCraftedTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BrutalAdvancementProvider extends ForgeAdvancementProvider {

    public BrutalAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, existingFileHelper, List.of(new BrutalAdvancements()));
    }

    private static class BrutalAdvancements implements AdvancementGenerator {

        @Override
        public void generate(HolderLookup.Provider registries, Consumer<Advancement> consumer, ExistingFileHelper existingFileHelper) {
            Advancement root = Advancement.Builder.advancement()
                    .display(ItemRegistry.TOMATO.get(),
                            Component.translatable(CommonConstants.MOD_NAME),
                            Component.translatable(CommonConstants.MOD_ID + "." + "advancement.root.desc"),
                            new ResourceLocation("minecraft:textures/block/rooted_dirt.png"),
                            FrameType.TASK, true, true, false)
                    .addCriterion("0", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.TOMATO.get()))
                    .save(consumer, getNameId("root"));

            Advancement.Builder.advancement()
                    .display(ItemRegistry.ROTTEN_TOMATO.get(),
                            Component.translatable(CommonConstants.MOD_ID + "." + "advancement.rotten_tomatoes"),
                            Component.translatable(CommonConstants.MOD_ID + "." + "advancement.rotten_tomatoes.desc"),
                            null,
                            FrameType.TASK, true, true, false)
                    .parent(root)
                    .addCriterion("0", TomatoProjectileTrigger.TriggerInstance.simple())
                    .save(consumer, getNameId("rotten_tomatoes"));

            Advancement.Builder.advancement()
                    .display(ItemRegistry.STONE_SCYTHE.get(),
                            Component.translatable(CommonConstants.MOD_ID + "." + "advancement.grim_reaper"),
                            Component.translatable(CommonConstants.MOD_ID + "." + "advancement.grim_reaper.desc"),
                            null,
                            FrameType.TASK, true, true, false)
                    .parent(root)
                    .addCriterion("0", RecipeCraftedTrigger.TriggerInstance.craftedItem(ItemRegistry.STONE_SCYTHE.getId()))
                    .save(consumer, getNameId("grim_reaper"));
        }

        private String getNameId(String id) {
            return CommonConstants.MOD_ID + ":" + id;
        }
    }
}
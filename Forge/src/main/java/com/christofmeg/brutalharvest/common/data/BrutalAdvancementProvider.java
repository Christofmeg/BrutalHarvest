package com.christofmeg.brutalharvest.common.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.advancement.TomatoProjectileTrigger;
import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import com.christofmeg.brutalharvest.common.init.TagRegistry;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger;
import net.minecraft.advancements.critereon.RecipeCraftedTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
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
                    .addCriterion("0", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.UNRIPE_TOMATO.get()))
                    .addCriterion("1", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.TOMATO.get()))
                    .addCriterion("2", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.ROTTEN_TOMATO.get()))
                    .requirements(RequirementsStrategy.OR)
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
                    .addCriterion("1", RecipeCraftedTrigger.TriggerInstance.craftedItem(ItemRegistry.COPPER_SCYTHE.getId()))
                    .addCriterion("2", RecipeCraftedTrigger.TriggerInstance.craftedItem(ItemRegistry.IRON_SCYTHE.getId()))
                    .addCriterion("3", RecipeCraftedTrigger.TriggerInstance.craftedItem(ItemRegistry.GOLDEN_SCYTHE.getId()))
                    .addCriterion("4", RecipeCraftedTrigger.TriggerInstance.craftedItem(ItemRegistry.DIAMOND_SCYTHE.getId()))
                    .addCriterion("5", RecipeCraftedTrigger.TriggerInstance.craftedItem(ItemRegistry.NETHERITE_SCYTHE.getId()))
                    .requirements(RequirementsStrategy.OR)
                    .save(consumer, getNameId("grim_reaper"));

            Advancement corn_seeds = Advancement.Builder.advancement()
                    .display(ItemRegistry.CORN_SEEDS.get(),
                            Component.translatable(CommonConstants.MOD_ID + "." + "advancement.corn_seeds"),
                            Component.translatable(CommonConstants.MOD_ID + "." + "advancement.corn_seeds.desc"),
                            null,
                            FrameType.TASK, true, true, false)
                    .parent(root)
                    .addCriterion("0", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(BlockRegistry.CORN.get()))
                    .save(consumer, getNameId("corn_seeds"));

            Advancement.Builder.advancement()
                    .display(ItemRegistry.CORN.get(),
                            Component.translatable(CommonConstants.MOD_ID + "." + "advancement.corn"),
                            Component.translatable(CommonConstants.MOD_ID + "." + "advancement.corn.desc"),
                            null,
                            FrameType.TASK, true, true, false)
                    .parent(corn_seeds)
                    .addCriterion("0", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.CORN.get()))
                    .save(consumer, getNameId("corn"));
        }

        private String getNameId(String id) {
            return CommonConstants.MOD_ID + ":" + id;
        }
    }
}
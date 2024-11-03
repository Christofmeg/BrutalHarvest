package com.christofmeg.brutalharvest.common.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.advancement.ThrownKnifeTrigger;
import com.christofmeg.brutalharvest.common.advancement.ThrownScytheTrigger;
import com.christofmeg.brutalharvest.common.advancement.TomatoProjectileTrigger;
import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import com.christofmeg.brutalharvest.common.init.EnchantmentRegistry;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
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
                            Component.translatable("advancement" + "." + CommonConstants.MOD_ID + "." + "root.desc"),
                            new ResourceLocation("minecraft:textures/block/rooted_dirt.png"),
                            FrameType.TASK, true, true, false)
                    .addCriterion("0", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.UNRIPE_TOMATO.get()))
                    .addCriterion("1", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.TOMATO.get()))
                    .addCriterion("2", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.ROTTEN_TOMATO.get()))
                    .requirements(RequirementsStrategy.OR)
                    .save(consumer, getNameId("root"));

            Advancement.Builder.advancement()
                    .display(ItemRegistry.ROTTEN_TOMATO.get(),
                            Component.translatable("advancement" + "." + CommonConstants.MOD_ID + "." + "rotten_tomatoes"),
                            Component.translatable("advancement" + "." + CommonConstants.MOD_ID + "." + "rotten_tomatoes.desc"),
                            null,
                            FrameType.TASK, true, true, false)
                    .parent(root)
                    .addCriterion("0", TomatoProjectileTrigger.TriggerInstance.simple())
                    .save(consumer, getNameId("rotten_tomatoes"));

            Advancement grim_reaper = Advancement.Builder.advancement()
                    .display(ItemRegistry.STONE_SCYTHE.get(),
                            Component.translatable("advancement" + "." + CommonConstants.MOD_ID + "." + "grim_reaper"),
                            Component.translatable("advancement" + "." + CommonConstants.MOD_ID + "." + "grim_reaper.desc"),
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

            Advancement.Builder.advancement()
                    .display(Items.ENCHANTED_BOOK,
                            Component.translatable("advancement" + "." + CommonConstants.MOD_ID + "." + "reaperang"),
                            Component.translatable("advancement" + "." + CommonConstants.MOD_ID + "." + "reaperang.desc"),
                            null,
                            FrameType.TASK, true, true, false)
                    .parent(grim_reaper)
                    .addCriterion("0", ThrownScytheTrigger.TriggerInstance.item(ItemPredicate.Builder.item().of(ItemRegistry.STONE_SCYTHE.get()).hasEnchantment(new EnchantmentPredicate(EnchantmentRegistry.BOOMERANG.get(), MinMaxBounds.Ints.ANY))))
                    .addCriterion("1", ThrownScytheTrigger.TriggerInstance.item(ItemPredicate.Builder.item().of(ItemRegistry.COPPER_SCYTHE.get()).hasEnchantment(new EnchantmentPredicate(EnchantmentRegistry.BOOMERANG.get(), MinMaxBounds.Ints.ANY))))
                    .addCriterion("2", ThrownScytheTrigger.TriggerInstance.item(ItemPredicate.Builder.item().of(ItemRegistry.IRON_SCYTHE.get()).hasEnchantment(new EnchantmentPredicate(EnchantmentRegistry.BOOMERANG.get(), MinMaxBounds.Ints.ANY))))
                    .addCriterion("3", ThrownScytheTrigger.TriggerInstance.item(ItemPredicate.Builder.item().of(ItemRegistry.GOLDEN_SCYTHE.get()).hasEnchantment(new EnchantmentPredicate(EnchantmentRegistry.BOOMERANG.get(), MinMaxBounds.Ints.ANY))))
                    .addCriterion("4", ThrownScytheTrigger.TriggerInstance.item(ItemPredicate.Builder.item().of(ItemRegistry.DIAMOND_SCYTHE.get()).hasEnchantment(new EnchantmentPredicate(EnchantmentRegistry.BOOMERANG.get(), MinMaxBounds.Ints.ANY))))
                    .addCriterion("5", ThrownScytheTrigger.TriggerInstance.item(ItemPredicate.Builder.item().of(ItemRegistry.NETHERITE_SCYTHE.get()).hasEnchantment(new EnchantmentPredicate(EnchantmentRegistry.BOOMERANG.get(), MinMaxBounds.Ints.ANY))))
                    .requirements(RequirementsStrategy.OR)
                    .save(consumer, getNameId("reaperang"));

            Advancement.Builder.advancement()
                    .display(ItemRegistry.FLINT_KNIFE.get(),
                            Component.translatable("advancement" + "." + CommonConstants.MOD_ID + "." + "throwing_knives"),
                            Component.translatable("advancement" + "." + CommonConstants.MOD_ID + "." + "throwing_knives.desc"),
                            null,
                            FrameType.TASK, true, true, false)
                    .parent(root)
                    .addCriterion("0", ThrownKnifeTrigger.TriggerInstance.item(ItemPredicate.Builder.item().of(ItemRegistry.FLINT_KNIFE.get()).hasEnchantment(new EnchantmentPredicate(EnchantmentRegistry.LAUNCH.get(), MinMaxBounds.Ints.ANY))))
                    .addCriterion("1", ThrownKnifeTrigger.TriggerInstance.item(ItemPredicate.Builder.item().of(ItemRegistry.WOODEN_KNIFE.get()).hasEnchantment(new EnchantmentPredicate(EnchantmentRegistry.LAUNCH.get(), MinMaxBounds.Ints.ANY))))
                    .addCriterion("2", ThrownKnifeTrigger.TriggerInstance.item(ItemPredicate.Builder.item().of(ItemRegistry.STONE_KNIFE.get()).hasEnchantment(new EnchantmentPredicate(EnchantmentRegistry.LAUNCH.get(), MinMaxBounds.Ints.ANY))))
                    .addCriterion("3", ThrownKnifeTrigger.TriggerInstance.item(ItemPredicate.Builder.item().of(ItemRegistry.COPPER_KNIFE.get()).hasEnchantment(new EnchantmentPredicate(EnchantmentRegistry.LAUNCH.get(), MinMaxBounds.Ints.ANY))))
                    .addCriterion("4", ThrownKnifeTrigger.TriggerInstance.item(ItemPredicate.Builder.item().of(ItemRegistry.IRON_KNIFE.get()).hasEnchantment(new EnchantmentPredicate(EnchantmentRegistry.LAUNCH.get(), MinMaxBounds.Ints.ANY))))
                    .addCriterion("5", ThrownKnifeTrigger.TriggerInstance.item(ItemPredicate.Builder.item().of(ItemRegistry.GOLDEN_KNIFE.get()).hasEnchantment(new EnchantmentPredicate(EnchantmentRegistry.LAUNCH.get(), MinMaxBounds.Ints.ANY))))
                    .addCriterion("6", ThrownKnifeTrigger.TriggerInstance.item(ItemPredicate.Builder.item().of(ItemRegistry.DIAMOND_KNIFE.get()).hasEnchantment(new EnchantmentPredicate(EnchantmentRegistry.LAUNCH.get(), MinMaxBounds.Ints.ANY))))
                    .addCriterion("7", ThrownKnifeTrigger.TriggerInstance.item(ItemPredicate.Builder.item().of(ItemRegistry.NETHERITE_KNIFE.get()).hasEnchantment(new EnchantmentPredicate(EnchantmentRegistry.LAUNCH.get(), MinMaxBounds.Ints.ANY))))
                    .requirements(RequirementsStrategy.OR)
                    .save(consumer, getNameId("throwing_knives"));

            Advancement corn_seeds = Advancement.Builder.advancement()
                    .display(ItemRegistry.CORN_SEEDS.get(),
                            Component.translatable("advancement" + "." + CommonConstants.MOD_ID + "." + "corn_seeds"),
                            Component.translatable("advancement" + "." + CommonConstants.MOD_ID + "." + "corn_seeds.desc"),
                            null,
                            FrameType.TASK, true, true, false)
                    .parent(root)
                    .addCriterion("0", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(BlockRegistry.CORN.get()))
                    .save(consumer, getNameId("corn_seeds"));

            Advancement.Builder.advancement()
                    .display(ItemRegistry.CORN.get(),
                            Component.translatable("advancement" + "." + CommonConstants.MOD_ID + "." + "corn"),
                            Component.translatable("advancement" + "." + CommonConstants.MOD_ID + "." + "corn.desc"),
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
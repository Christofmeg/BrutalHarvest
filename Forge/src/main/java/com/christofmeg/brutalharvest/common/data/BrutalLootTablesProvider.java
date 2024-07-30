package com.christofmeg.brutalharvest.common.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import com.christofmeg.brutalharvest.common.data.loot.BrutalBlockLootTables;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

public class BrutalLootTablesProvider extends LootTableProvider {

    public BrutalLootTablesProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn.getPackOutput(), Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(CustomDungeonLootProvider::new, LootContextParamSets.CHEST),
                new LootTableProvider.SubProviderEntry(BrutalBlockLootTables::new, LootContextParamSets.BLOCK)
        ));
    }

    private static class CustomDungeonLootProvider implements LootTableSubProvider {
        @Override
        public void generate(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
            LootPool.Builder commonPool = LootPool.lootPool();
            commonPool.setRolls(UniformGenerator.between(3, 8))
                    .add(createEntry(ItemRegistry.TOMATO_SEEDS.get(), 10, 1, 5))
                    .add(createEntry(ItemRegistry.LETTUCE_SEEDS.get(), 10, 1, 5))
                    .add(createEntry(ItemRegistry.CORN_SEEDS.get(), 5, 1, 8))
                    .add(createEntry(ItemRegistry.CUCUMBER_SEEDS.get(), 10, 1, 5))
                    .add(createEntry(ItemRegistry.COTTON_SEEDS.get(), 10, 1, 5))
            //        .add(createEntry(ItemRegistry.COFFEE_CHERRY.get(), 10, 1, 5))
                    .add(createEntry(ItemRegistry.RAPESEED_BEANS.get(), 10, 1, 5))
                    .add(createEntry(ItemRegistry.RAPESEEDS.get(), 10, 1, 5))
                    .add(createEntry(ItemRegistry.SUGAR_BEET_SEEDS.get(), 10, 1, 5))
                    .add(createEntry(ItemRegistry.STRAWBERRY_SEEDS.get(), 10, 1, 5))
            //        .add(createEntry(ItemRegistry.ONION_SEEDS.get(), 10, 1, 5))
            //        .add(createEntry(ItemRegistry.CHILI_PEPPER_SEEDS.get(), 10, 1, 5))
            //        .add(createEntry(ItemRegistry.RED_CHILI_PEPPET.get(), 10, 1, 5))
                    .add(createEntry(ItemRegistry.BLUEBERRY.get(), 10, 1, 5))

                    .add(EmptyLootItem.emptyItem().setWeight(10));
            LootTable.Builder commonTable = LootTable.lootTable();
            commonTable.withPool(commonPool);
            consumer.accept(new ResourceLocation(CommonConstants.MOD_ID, "chests/village/village_loot"), commonTable);
        }

        private LootPoolEntryContainer.Builder<?> createEntry(ItemLike item, int weight, int min, int max) {
            return createEntry(new ItemStack(item), weight)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)));
        }

        @SuppressWarnings("deprecation")
        private LootPoolSingletonContainer.Builder<?> createEntry(ItemStack item, int weight) {
            LootPoolSingletonContainer.Builder<?> ret = LootItem.lootTableItem(item.getItem()).setWeight(weight);
            if (item.hasTag())
                ret.apply(SetNbtFunction.setTag(item.getOrCreateTag()));
            return ret;
        }
    }
}
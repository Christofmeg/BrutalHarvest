package com.christofmeg.brutalharvest.common.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
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
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

public class BrutalLootTablesProvider extends LootTableProvider {

    public BrutalLootTablesProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn.getPackOutput(), Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(CustomDungeonLootProvider::new, LootContextParamSets.CHEST)
        ));
    }

    private static class CustomDungeonLootProvider implements LootTableSubProvider {
        @Override
        public void generate(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
            LootPool.Builder commonPool = LootPool.lootPool();
            commonPool.setRolls(ConstantValue.exactly(3))
                    .add(createEntry(ItemRegistry.UNRIPE_TOMATO.get(), 10,1, 3))
                    .add(createEntry(ItemRegistry.TOMATO.get(), 10,5, 10))
                    .add(createEntry(ItemRegistry.ROTTEN_TOMATO.get(), 3,1, 1))
                    .add(createEntry(ItemRegistry.TOMATO_SEEDS.get(), 3,1, 1))
                    .add(EmptyLootItem.emptyItem().setWeight(20));
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
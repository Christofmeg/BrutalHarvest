package com.christofmeg.brutalharvest.common.data.loot;

import com.christofmeg.brutalharvest.common.block.LettuceCropBlock;
import com.christofmeg.brutalharvest.common.block.SugarBeetCropBlock;
import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class BrutalBlockLootTables extends BlockLootSubProvider {

    public BrutalBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        //TODO block loot to Corn, Tomato, Cotton

        LootItemCondition.Builder sugarBeetBuilder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(BlockRegistry.SUGAR_BEET.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SugarBeetCropBlock.AGE, 3))
      //          .or(LootItemBlockStatePropertyCondition
      //                  .hasBlockStateProperties(BlockRegistry.SUGAR_BEET.get())
      //                  .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CornCropBlock.AGE, 4)))
                ;
        this.add(BlockRegistry.SUGAR_BEET.get(), createCropDrops(BlockRegistry.SUGAR_BEET.get(), ItemRegistry.SUGAR_BEET.get(),
                ItemRegistry.SUGAR_BEET_SEEDS.get(), sugarBeetBuilder));

        LootItemCondition.Builder age4Condition = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(BlockRegistry.LETTUCE.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LettuceCropBlock.AGE, 4));

        LootItemCondition.Builder age5Condition = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(BlockRegistry.LETTUCE.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LettuceCropBlock.AGE, 5));

        this.add(BlockRegistry.LETTUCE.get(), createCropDrops(
                BlockRegistry.LETTUCE.get(),
                ItemRegistry.LETTUCE.get(),
                ItemRegistry.LETTUCE_SEEDS.get(),
                age4Condition,
                age5Condition
        ));



    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
                .filter(block ->
                        block instanceof SugarBeetCropBlock ||
                        block instanceof LettuceCropBlock
                )
                ::iterator;
    }

    protected LootTable.Builder createCropDrops(Block pCropBlock, Item pGrownCropItem, Item pSeedsItem, LootItemCondition.Builder pDropGrownCropConditionAge4, LootItemCondition.Builder pDropGrownCropConditionAge5) {
        return this.applyExplosionDecay(pCropBlock, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(pGrownCropItem).when(pDropGrownCropConditionAge4)))
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(pSeedsItem)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                .when(pDropGrownCropConditionAge4)))
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(pSeedsItem)
                                .when(pDropGrownCropConditionAge5.and(LootItemRandomChanceCondition.randomChance(0.25F))))
                ));
    }
}

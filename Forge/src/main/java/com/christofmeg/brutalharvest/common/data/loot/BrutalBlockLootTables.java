package com.christofmeg.brutalharvest.common.data.loot;

import com.christofmeg.brutalharvest.common.block.CottonCropBlock;
import com.christofmeg.brutalharvest.common.block.LettuceCropBlock;
import com.christofmeg.brutalharvest.common.block.SugarBeetCropBlock;
import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
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

        //TODO block loot to Corn, Tomato

        LootItemCondition.Builder lettuceCondition1 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(BlockRegistry.LETTUCE.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LettuceCropBlock.AGE, 4));
        LootItemCondition.Builder lettuceCondition2 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(BlockRegistry.LETTUCE.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LettuceCropBlock.AGE, 5));
        this.add(BlockRegistry.LETTUCE.get(), createLettuceCropDrops(
                BlockRegistry.LETTUCE.get(),
                ItemRegistry.LETTUCE.get(),
                ItemRegistry.LETTUCE_SEEDS.get(),
                lettuceCondition1,
                lettuceCondition2
        ));

        LootItemCondition.Builder sugarBeetCondition1 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(BlockRegistry.SUGAR_BEET.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SugarBeetCropBlock.AGE, 3));
        LootItemCondition.Builder sugarBeetCondition2 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(BlockRegistry.SUGAR_BEET.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SugarBeetCropBlock.AGE, 4));
        this.add(BlockRegistry.SUGAR_BEET.get(), createSugarBeetCropDrops(
                BlockRegistry.SUGAR_BEET.get(),
                ItemRegistry.SUGAR_BEET.get(),
                ItemRegistry.SUGAR_BEET_SEEDS.get(),
                sugarBeetCondition1,
                sugarBeetCondition2
        ));

        LootItemCondition.Builder cottonCondition1 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(BlockRegistry.COTTON.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CottonCropBlock.AGE, 5));
        LootItemCondition.Builder cottonCondition2 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(BlockRegistry.COTTON.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CottonCropBlock.AGE, 6));
        this.add(BlockRegistry.COTTON.get(), createCottonCropDrops(
                BlockRegistry.COTTON.get(),
                ItemRegistry.COTTON.get(),
                ItemRegistry.COTTON_SEEDS.get(),
                cottonCondition1,
                cottonCondition2
        ));

    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
                .filter(block ->
                        block instanceof SugarBeetCropBlock ||
                        block instanceof LettuceCropBlock ||
                        block instanceof CottonCropBlock
                )
                ::iterator;
    }

    protected LootTable.Builder createLettuceCropDrops(Block pCropBlock, Item pGrownCropItem, Item pSeedsItem, LootItemCondition.Builder pDropGrownCropConditionAge4, LootItemCondition.Builder pDropGrownCropConditionAge5) {
        return this.applyExplosionDecay(pCropBlock, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(pDropGrownCropConditionAge4)
                        .add(LootItem.lootTableItem(pGrownCropItem))
                )
                .withPool(LootPool.lootPool()
                        .when(pDropGrownCropConditionAge4)
                        .add(LootItem.lootTableItem(pSeedsItem)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                )
                .withPool(LootPool.lootPool()
                        .when(pDropGrownCropConditionAge5)
                        .add(LootItem.lootTableItem(pSeedsItem)
                                .when(LootItemRandomChanceCondition.randomChance(0.25F))))
                .withPool(LootPool.lootPool()
                        .when(pDropGrownCropConditionAge4.invert().and(pDropGrownCropConditionAge5.invert()))
                        .add(LootItem.lootTableItem(pSeedsItem))
                )
        );
    }

    protected LootTable.Builder createSugarBeetCropDrops(Block pCropBlock, Item pGrownCropItem, Item pSeedsItem, LootItemCondition.Builder pDropGrownCropConditionAge3, LootItemCondition.Builder pDropGrownCropConditionAge4) {
        return this.applyExplosionDecay(pCropBlock, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(pDropGrownCropConditionAge3)
                        .add(LootItem.lootTableItem(pGrownCropItem))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                )
                .withPool(LootPool.lootPool()
                        .when(pDropGrownCropConditionAge3)
                        .add(LootItem.lootTableItem(pSeedsItem)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))))
                )
                .withPool(LootPool.lootPool()
                        .when(pDropGrownCropConditionAge4)
                        .add(LootItem.lootTableItem(pSeedsItem)
                                .when(LootItemRandomChanceCondition.randomChance(0.25F))))
                .withPool(LootPool.lootPool()
                        .when(pDropGrownCropConditionAge3.invert().and(pDropGrownCropConditionAge4.invert()))
                        .add(LootItem.lootTableItem(pSeedsItem))
                )
        );
    }

    protected LootTable.Builder createCottonCropDrops(Block pCropBlock, Item pGrownCropItem, Item pSeedsItem, LootItemCondition.Builder pDropGrownCropConditionAge5, LootItemCondition.Builder pDropGrownCropConditionAge6) {
        return this.applyExplosionDecay(pCropBlock, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(pDropGrownCropConditionAge5)
                        .add(LootItem.lootTableItem(pGrownCropItem))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                )
                .withPool(LootPool.lootPool()
                        .when(pDropGrownCropConditionAge5)
                        .add(LootItem.lootTableItem(pSeedsItem)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))))
                )
                .withPool(LootPool.lootPool()
                        .when(pDropGrownCropConditionAge6.and(LootItemRandomChanceCondition.randomChance(0.25F)))
                        .add(LootItem.lootTableItem(pSeedsItem).setWeight(1))
                        .add(LootItem.lootTableItem(Items.STICK).setWeight(1))
                )
                .withPool(LootPool.lootPool()
                        .when(pDropGrownCropConditionAge5.invert().and(pDropGrownCropConditionAge6.invert()))
                        .add(LootItem.lootTableItem(pSeedsItem))
                )
        );
    }

}

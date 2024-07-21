package com.christofmeg.brutalharvest.common.data.loot;

import com.christofmeg.brutalharvest.common.block.*;
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

        this.add(BlockRegistry.TOMATO.get(), createGenericUnripeRottenCropDrops(
                BlockRegistry.TOMATO.get(),
                ItemRegistry.UNRIPE_TOMATO.get(), 3.0F, 6.0F,
                ItemRegistry.TOMATO.get(), 3.0F, 6.0F,
                ItemRegistry.ROTTEN_TOMATO.get(), 3.0F, 6.0F,
                ItemRegistry.TOMATO_SEEDS.get(), 1.0F, 2.0F,
                LootItemBlockStatePropertyCondition
                        .hasBlockStateProperties(BlockRegistry.TOMATO.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, 4))
                        .or(LootItemBlockStatePropertyCondition
                                .hasBlockStateProperties(BlockRegistry.TOMATO.get())
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, 5)))
                        .or(LootItemBlockStatePropertyCondition
                                .hasBlockStateProperties(BlockRegistry.TOMATO.get())
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, 6)))
                ,
                LootItemBlockStatePropertyCondition
                        .hasBlockStateProperties(BlockRegistry.TOMATO.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, 7)),
                LootItemBlockStatePropertyCondition
                        .hasBlockStateProperties(BlockRegistry.TOMATO.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, 8))
        ));

        this.add(BlockRegistry.LETTUCE.get(), createLettuceCropDrops(
                BlockRegistry.LETTUCE.get(),
                ItemRegistry.LETTUCE.get(),
                ItemRegistry.LETTUCE_SEEDS.get(),
                LootItemBlockStatePropertyCondition
                        .hasBlockStateProperties(BlockRegistry.LETTUCE.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LettuceCropBlock.AGE, 4)),
                LootItemBlockStatePropertyCondition
                        .hasBlockStateProperties(BlockRegistry.LETTUCE.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LettuceCropBlock.AGE, 5))
        ));

        this.add(BlockRegistry.SUGAR_BEET.get(), createGenericCropDrops(
                BlockRegistry.SUGAR_BEET.get(),
                ItemRegistry.SUGAR_BEET.get(), 1.0F, 3.0F,
                ItemRegistry.SUGAR_BEET_SEEDS.get(), 2.0F, 3.0F,
                LootItemBlockStatePropertyCondition
                        .hasBlockStateProperties(BlockRegistry.SUGAR_BEET.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SugarBeetCropBlock.AGE, 3)),
                LootItemBlockStatePropertyCondition
                        .hasBlockStateProperties(BlockRegistry.SUGAR_BEET.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SugarBeetCropBlock.AGE, 4))
        ));

        this.add(BlockRegistry.COTTON.get(), createCottonCropDrops(
                BlockRegistry.COTTON.get(),
                ItemRegistry.COTTON.get(),
                ItemRegistry.COTTON_SEEDS.get(),
                LootItemBlockStatePropertyCondition
                        .hasBlockStateProperties(BlockRegistry.COTTON.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CottonCropBlock.AGE, 5)),
                LootItemBlockStatePropertyCondition
                        .hasBlockStateProperties(BlockRegistry.COTTON.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CottonCropBlock.AGE, 6))
        ));

        this.add(BlockRegistry.STRAWBERRY.get(), createGenericUnripeCropDrops(
                BlockRegistry.STRAWBERRY.get(),
                ItemRegistry.UNRIPE_STRAWBERRY.get(), 1.0F, 4.0F,
                ItemRegistry.STRAWBERRY.get(), 1.0F, 4.0F,
                ItemRegistry.STRAWBERRY_SEEDS.get(), 1.0F, 2.0F,
                LootItemBlockStatePropertyCondition
                        .hasBlockStateProperties(BlockRegistry.STRAWBERRY.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, 5)),
                LootItemBlockStatePropertyCondition
                        .hasBlockStateProperties(BlockRegistry.STRAWBERRY.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, 6)),
                LootItemBlockStatePropertyCondition
                        .hasBlockStateProperties(BlockRegistry.STRAWBERRY.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, 7))
        ));

        this.add(BlockRegistry.BLUEBERRY.get(), this.applyExplosionDecay(BlockRegistry.BLUEBERRY.get(), LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .when(LootItemBlockStatePropertyCondition
                                        .hasBlockStateProperties(BlockRegistry.BLUEBERRY.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlueberryBushBlock.AGE, 3)))
                                .add(LootItem.lootTableItem(ItemRegistry.BLUEBERRY.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        )
                        .withPool(LootPool.lootPool()
                                .when(LootItemBlockStatePropertyCondition
                                        .hasBlockStateProperties(BlockRegistry.BLUEBERRY.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlueberryBushBlock.AGE, 3)).invert()
                                        .and(LootItemBlockStatePropertyCondition
                                                .hasBlockStateProperties(BlockRegistry.BLUEBERRY.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlueberryBushBlock.AGE, 4)).invert()))
                                .add(LootItem.lootTableItem(ItemRegistry.BLUEBERRY.get()))
                        )
                ));

    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
                .filter(block ->
                        block instanceof TomatoCropBlock ||
                        block instanceof LettuceCropBlock ||
                        block instanceof SugarBeetCropBlock ||
                        block instanceof CottonCropBlock ||
                        block instanceof StrawberryCropBlock ||
                        block instanceof BlueberryBushBlock
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

    protected LootTable.Builder createGenericCropDrops(Block pCropBlock, Item pGrownCropItem, float minCropItem, float maxCropItem, Item pSeedsItem, float minSeedItem, float maxSeedItem, LootItemCondition.Builder matureCondition, LootItemCondition.Builder deadCondition) {
        return this.applyExplosionDecay(pCropBlock, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(matureCondition)
                        .add(LootItem.lootTableItem(pGrownCropItem))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minCropItem, maxCropItem)))
                )
                .withPool(LootPool.lootPool()
                        .when(matureCondition)
                        .add(LootItem.lootTableItem(pSeedsItem)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minSeedItem, maxSeedItem))))
                )
                .withPool(LootPool.lootPool()
                        .when(deadCondition)
                        .add(LootItem.lootTableItem(pSeedsItem)
                                .when(LootItemRandomChanceCondition.randomChance(0.25F))))
                .withPool(LootPool.lootPool()
                        .when(matureCondition.invert().and(deadCondition.invert()))
                        .add(LootItem.lootTableItem(pSeedsItem))
                )
        );
    }

    protected LootTable.Builder createGenericUnripeCropDrops(Block pCropBlock, Item unripeCropItem, float minUnripeCropItem, float maxUnripeCropItem, Item pGrownCropItem, float minCropItem, float maxCropItem, Item pSeedsItem, float minSeedItem, float maxSeedItem, LootItemCondition.Builder unripeConditon, LootItemCondition.Builder matureCondition, LootItemCondition.Builder deadCondition) {
        return this.applyExplosionDecay(pCropBlock, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(unripeConditon)
                        .add(LootItem.lootTableItem(unripeCropItem))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minUnripeCropItem, maxUnripeCropItem)))
                )
                .withPool(LootPool.lootPool()
                        .when(matureCondition)
                        .add(LootItem.lootTableItem(pGrownCropItem))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minCropItem, maxCropItem)))
                )
                .withPool(LootPool.lootPool()
                        .when(matureCondition)
                        .add(LootItem.lootTableItem(pSeedsItem)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minSeedItem, maxSeedItem))))
                )
                .withPool(LootPool.lootPool()
                        .when(deadCondition)
                        .add(LootItem.lootTableItem(pSeedsItem)
                                .when(LootItemRandomChanceCondition.randomChance(0.25F))))
                .withPool(LootPool.lootPool()
                        .when(unripeConditon.invert().and(matureCondition.invert().and(deadCondition.invert())))
                        .add(LootItem.lootTableItem(pSeedsItem))
                )
        );
    }

    protected LootTable.Builder createGenericUnripeRottenCropDrops(Block pCropBlock, Item unripeCropItem, float minUnripeCropItem, float maxUnripeCropItem, Item pGrownCropItem, float minCropItem, float maxCropItem, Item rottenCropItem, float minRottenCropItem, float maxRottenCropItem, Item pSeedsItem, float minSeedItem, float maxSeedItem, LootItemCondition.Builder unripeConditon, LootItemCondition.Builder matureCondition, LootItemCondition.Builder deadCondition) {
        return this.applyExplosionDecay(pCropBlock, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(unripeConditon)
                        .add(LootItem.lootTableItem(unripeCropItem))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minUnripeCropItem, maxUnripeCropItem)))
                )
                .withPool(LootPool.lootPool()
                        .when(matureCondition)
                        .add(LootItem.lootTableItem(pGrownCropItem))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minCropItem, maxCropItem)))
                )
                .withPool(LootPool.lootPool()
                        .when(matureCondition)
                        .add(LootItem.lootTableItem(pSeedsItem)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minSeedItem, maxSeedItem))))
                )
                .withPool(LootPool.lootPool()
                        .when(deadCondition)
                        .add(LootItem.lootTableItem(rottenCropItem))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minRottenCropItem, maxRottenCropItem)))
                )
                .withPool(LootPool.lootPool()
                        .when(deadCondition)
                        .add(LootItem.lootTableItem(pSeedsItem))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1)))
                )
                .withPool(LootPool.lootPool()
                        .when(unripeConditon.invert().and(matureCondition.invert().and(deadCondition.invert())))
                        .add(LootItem.lootTableItem(pSeedsItem))
                )
        );
    }

}

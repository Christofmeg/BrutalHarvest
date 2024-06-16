package com.christofmeg.brutalharvest.common.data.loot;

import com.christofmeg.brutalharvest.common.block.SugarBeetCropBlock;
import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
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

        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(BlockRegistry.SUGAR_BEET.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SugarBeetCropBlock.AGE, 3))
      //          .or(LootItemBlockStatePropertyCondition
      //                  .hasBlockStateProperties(BlockRegistry.SUGAR_BEET.get())
      //                  .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CornCropBlock.AGE, 4)))
                ;

        this.add(BlockRegistry.SUGAR_BEET.get(), createCropDrops(BlockRegistry.SUGAR_BEET.get(), ItemRegistry.SUGAR_BEET.get(),
                ItemRegistry.SUGAR_BEET_SEEDS.get(), lootItemConditionBuilder));

    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
                .filter(block -> block instanceof SugarBeetCropBlock)
                ::iterator;
    }

}

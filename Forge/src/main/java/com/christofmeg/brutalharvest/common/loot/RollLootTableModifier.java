package com.christofmeg.brutalharvest.common.loot;

import com.christofmeg.brutalharvest.CommonConstants;
import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class RollLootTableModifier extends LootModifier {
    public static final Supplier<Codec<RollLootTableModifier>> CODEC = Suppliers.memoize(
            () -> RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, RollLootTableModifier::new))
    );

    public RollLootTableModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.addAll(CustomPools.roll(context));
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }

    private static class CustomPools {
        private static LootPool commonPool = null;

        private static List<ItemStack> roll(LootContext ctx) {
            if (commonPool == null) {
                commonPool = buildLootPool();
            }
            List<ItemStack> res = new ArrayList<>();
            commonPool.addRandomItems(res::add, ctx);
            return res;
        }

        private static LootPool buildLootPool() {
            return LootPool.lootPool()
                    .add(LootTableReference.lootTableReference(new ResourceLocation(CommonConstants.MOD_ID, "chests/village/village_loot")).setWeight(1))
                    .name(CommonConstants.MOD_ID + "_" + "village_loot")
                    .build();
        }
    }
}
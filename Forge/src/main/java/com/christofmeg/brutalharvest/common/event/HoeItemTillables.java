package com.christofmeg.brutalharvest.common.event;

import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class HoeItemTillables {
    public static final Map<Block, Pair<Predicate<UseOnContext>, Consumer<UseOnContext>>> TILLABLES = Maps.newHashMap();

    public static void registerTillables() {
        HoeItemTillables.addTillables(BlockRegistry.DIRT_SLAB.get(), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState())));
    }

    public static void addTillables(@NotNull Block block, Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> context) {
        synchronized (TILLABLES) {
            TILLABLES.put(block, context);
        }
    }


}
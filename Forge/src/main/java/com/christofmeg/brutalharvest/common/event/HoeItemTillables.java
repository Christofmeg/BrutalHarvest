package com.christofmeg.brutalharvest.common.event;

import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class HoeItemTillables {
    public static final Map<BlockState, Pair<Predicate<UseOnContext>, Consumer<UseOnContext>>> TILLABLES = Maps.newHashMap();

    public static void register() {
        HoeItemTillables.put(BlockRegistry.DIRT_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM))));
        HoeItemTillables.put(BlockRegistry.DIRT_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP))));
        HoeItemTillables.put(BlockRegistry.GRASS_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM))));
        HoeItemTillables.put(BlockRegistry.GRASS_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP))));
        HoeItemTillables.put(BlockRegistry.DIRT_PATH_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM))));
        HoeItemTillables.put(BlockRegistry.DIRT_PATH_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP))));
        HoeItemTillables.put(BlockRegistry.DIRT_TRACK_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM))));
        HoeItemTillables.put(BlockRegistry.DIRT_TRACK_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP))));

        HoeItemTillables.put(BlockRegistry.DIRT_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.DOUBLE), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.DOUBLE))));
        HoeItemTillables.put(BlockRegistry.GRASS_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.DOUBLE), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.DOUBLE))));
        HoeItemTillables.put(BlockRegistry.DIRT_PATH_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.DOUBLE), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.DOUBLE))));
        HoeItemTillables.put(BlockRegistry.DIRT_TRACK_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.DOUBLE), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.DOUBLE))));
        HoeItemTillables.put(BlockRegistry.DIRT_TRACK.get().defaultBlockState(), Pair.of(a -> true, HoeItem.changeIntoState(Blocks.FARMLAND.defaultBlockState())));

        HoeItemTillables.put(BlockRegistry.DIRT_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM).setValue(BlockStateProperties.WATERLOGGED, true), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM).setValue(BlockStateProperties.WATERLOGGED, true))));
        HoeItemTillables.put(BlockRegistry.DIRT_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP).setValue(BlockStateProperties.WATERLOGGED, true), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP).setValue(BlockStateProperties.WATERLOGGED, true))));
        HoeItemTillables.put(BlockRegistry.GRASS_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM).setValue(BlockStateProperties.WATERLOGGED, true), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM).setValue(BlockStateProperties.WATERLOGGED, true))));
        HoeItemTillables.put(BlockRegistry.GRASS_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP).setValue(BlockStateProperties.WATERLOGGED, true), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP).setValue(BlockStateProperties.WATERLOGGED, true))));
        HoeItemTillables.put(BlockRegistry.DIRT_PATH_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM).setValue(BlockStateProperties.WATERLOGGED, true), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM).setValue(BlockStateProperties.WATERLOGGED, true))));
        HoeItemTillables.put(BlockRegistry.DIRT_PATH_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP).setValue(BlockStateProperties.WATERLOGGED, true), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP).setValue(BlockStateProperties.WATERLOGGED, true))));
        HoeItemTillables.put(BlockRegistry.DIRT_TRACK_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM).setValue(BlockStateProperties.WATERLOGGED, true), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM).setValue(BlockStateProperties.WATERLOGGED, true))));
        HoeItemTillables.put(BlockRegistry.DIRT_TRACK_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP).setValue(BlockStateProperties.WATERLOGGED, true), Pair.of(a -> true, HoeItem.changeIntoState(BlockRegistry.FARMLAND_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP).setValue(BlockStateProperties.WATERLOGGED, true))));
    }

    public static void put(BlockState block, Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> context) {
        synchronized (TILLABLES) {
            TILLABLES.put(block, context);
        }
    }

    //TODO check if these show up in REI/EMI categories

}
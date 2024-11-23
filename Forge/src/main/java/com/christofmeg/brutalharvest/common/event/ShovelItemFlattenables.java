package com.christofmeg.brutalharvest.common.event;

import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ShovelItemFlattenables{
    public static final Map<BlockState, Pair<Predicate<UseOnContext>, Consumer<UseOnContext>>> FLATTENABLES = Maps.newHashMap();
    public static final Map<BlockState, Pair<Predicate<UseOnContext>, Consumer<UseOnContext>>> FLATTENABLES_OVERRIDES = Maps.newHashMap();

    public static void register() {
        ShovelItemFlattenables.put(BlockRegistry.GRASS_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM), Pair.of(a -> true, ShovelItemFlattenables.changeIntoState(BlockRegistry.DIRT_PATH_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM))));
        ShovelItemFlattenables.put(BlockRegistry.GRASS_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP), Pair.of(a -> true, ShovelItemFlattenables.changeIntoState(BlockRegistry.DIRT_PATH_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP))));
        ShovelItemFlattenables.put(BlockRegistry.DIRT_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM), Pair.of(a -> true, ShovelItemFlattenables.changeIntoState(BlockRegistry.DIRT_TRACK_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM))));
        ShovelItemFlattenables.put(BlockRegistry.DIRT_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP), Pair.of(a -> true, ShovelItemFlattenables.changeIntoState(BlockRegistry.DIRT_TRACK_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP))));

        ShovelItemFlattenables.put(BlockRegistry.GRASS_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.DOUBLE), Pair.of(a -> true, ShovelItemFlattenables.changeIntoState(BlockRegistry.DIRT_PATH_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.DOUBLE))));
        ShovelItemFlattenables.put(BlockRegistry.DIRT_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.DOUBLE), Pair.of(a -> true, ShovelItemFlattenables.changeIntoState(BlockRegistry.DIRT_TRACK_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.DOUBLE))));
        ShovelItemFlattenables.putOverride(Blocks.DIRT.defaultBlockState(), Pair.of(a -> true, ShovelItemFlattenables.changeIntoState(BlockRegistry.DIRT_TRACK.get().defaultBlockState())));
        ShovelItemFlattenables.putOverride(Blocks.COARSE_DIRT.defaultBlockState(), Pair.of(a -> true, ShovelItemFlattenables.changeIntoState(BlockRegistry.DIRT_TRACK.get().defaultBlockState())));
        ShovelItemFlattenables.putOverride(Blocks.ROOTED_DIRT.defaultBlockState(), Pair.of(a -> true, ShovelItemFlattenables.changeIntoState(BlockRegistry.DIRT_TRACK.get().defaultBlockState())));

        ShovelItemFlattenables.put(BlockRegistry.GRASS_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM).setValue(BlockStateProperties.WATERLOGGED, true), Pair.of(a -> true, ShovelItemFlattenables.changeIntoState(BlockRegistry.DIRT_PATH_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM).setValue(BlockStateProperties.WATERLOGGED, true))));
        ShovelItemFlattenables.put(BlockRegistry.GRASS_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP).setValue(BlockStateProperties.WATERLOGGED, true), Pair.of(a -> true, ShovelItemFlattenables.changeIntoState(BlockRegistry.DIRT_PATH_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP).setValue(BlockStateProperties.WATERLOGGED, true))));
        ShovelItemFlattenables.put(BlockRegistry.DIRT_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM).setValue(BlockStateProperties.WATERLOGGED, true), Pair.of(a -> true, ShovelItemFlattenables.changeIntoState(BlockRegistry.DIRT_TRACK_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM).setValue(BlockStateProperties.WATERLOGGED, true))));
        ShovelItemFlattenables.put(BlockRegistry.DIRT_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP).setValue(BlockStateProperties.WATERLOGGED, true), Pair.of(a -> true, ShovelItemFlattenables.changeIntoState(BlockRegistry.DIRT_TRACK_SLAB.get().defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP).setValue(BlockStateProperties.WATERLOGGED, true))));
    }

    public static void put(BlockState block, Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> context) {
        synchronized (FLATTENABLES) {
            FLATTENABLES.put(block, context);
        }
    }

    public static void putOverride(BlockState block, Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> context) {
        synchronized (FLATTENABLES_OVERRIDES) {
            FLATTENABLES_OVERRIDES.put(block, context);
        }
    }

    public static Consumer<UseOnContext> changeIntoState(BlockState pState) {
        return (p_238241_) -> {
            p_238241_.getLevel().setBlock(p_238241_.getClickedPos(), pState, 11);
            p_238241_.getLevel().gameEvent(GameEvent.BLOCK_CHANGE, p_238241_.getClickedPos(), GameEvent.Context.of(p_238241_.getPlayer(), pState));
        };
    }

    //TODO check if these show up in REI/EMI categories

}
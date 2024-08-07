package com.christofmeg.brutalharvest.client.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.client.base.BaseBlockStateProvider;
import com.christofmeg.brutalharvest.common.block.*;
import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public class BrutalBlockStateProvider extends BaseBlockStateProvider {

    public BrutalBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, exFileHelper);
    }

    @Override
    public @NotNull String getName() {
        return CommonConstants.MOD_ID + " - BlockModel & BlockState";
    }

    @Override
    protected void registerStatesAndModels() {

        makeCrop(BlockRegistry.TOMATO.get(), TomatoCropBlock.AGE, modLoc("block/lowered_cross"));
        makeCrop(BlockRegistry.LETTUCE.get(), LettuceCropBlock.AGE);
        makeDoubleCrop(BlockRegistry.CORN.get(), 8, 2, modLoc("block/lowered_cross"));
        makeDoubleCrop(BlockRegistry.CUCUMBER.get(), 7, 4, modLoc("block/lowered_cross"));

        makeCrop(BlockRegistry.COTTON.get(), CottonCropBlock.AGE, modLoc("block/lowered_cross"));
        makeDoubleCrop(BlockRegistry.RAPESEED.get(), 8, 4, modLoc("block/lowered_cross"));
        makeCrop(BlockRegistry.SUGAR_BEET.get(), SugarBeetCropBlock.AGE);
        makeCrop(BlockRegistry.STRAWBERRY.get(), StrawberryCropBlock.AGE, modLoc("block/lowered_cross"));
    //    makeCrop(BlockRegistry.ONION.get(), OnionCropBlock.AGE);
        makeBush(BlockRegistry.BLUEBERRY.get(), BlueberryBushBlock.AGE);

        saplingBlock(BlockRegistry.RUBBER_SAPLING);
        logBlock((RotatedPillarBlock) BlockRegistry.RUBBER_LOG.get());
        axisBlock((RotatedPillarBlock) BlockRegistry.RUBBER_WOOD.get(), blockTexture(BlockRegistry.RUBBER_LOG.get()), blockTexture(BlockRegistry.RUBBER_LOG.get()));
        logBlock((RotatedPillarBlock) BlockRegistry.STRIPPED_RUBBER_LOG.get());
        axisBlock((RotatedPillarBlock) BlockRegistry.STRIPPED_RUBBER_WOOD.get(), blockTexture(BlockRegistry.STRIPPED_RUBBER_LOG.get()), blockTexture(BlockRegistry.STRIPPED_RUBBER_LOG.get()));
        simpleBlock(BlockRegistry.RUBBER_PLANKS.get());
        leavesBlock(BlockRegistry.RUBBER_LEAVES);
    }

}

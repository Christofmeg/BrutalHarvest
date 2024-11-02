package com.christofmeg.brutalharvest.client.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.client.data.base.BaseBlockStateProvider;
import com.christofmeg.brutalharvest.common.block.*;
import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraftforge.client.model.generators.ModelFile;
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
        makeDoubleCrop(BlockRegistry.CORN.get(), 8, 2, modLoc("block/lowered_cross"), mcLoc("block/cross"));
        makeDoubleCrop(BlockRegistry.CUCUMBER.get(), 7, 4, modLoc("block/lowered_cross"), mcLoc("block/cross"));

        makeCrop(BlockRegistry.COTTON.get(), CottonCropBlock.AGE, modLoc("block/lowered_cross"));
        makeDoubleCrop(BlockRegistry.RAPESEED.get(), 8, 4, modLoc("block/lowered_cross"), mcLoc("block/cross"));
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

        this.slabBlock(BlockRegistry.DIRT_SLAB.get(), mcLoc("block/dirt"), mcLoc("block/dirt"));

        ModelFile farmland = vanillaModels().withExistingParent("farmland", mcLoc("block/farmland_moist"));
        ModelFile farmland_moist = vanillaModels().withExistingParent("farmland_moist", mcLoc("block/farmland_moist"));
        ModelFile farmland_slab = models().withExistingParent("farmland_slab", modLoc("block/lowered_slab"))
                .texture("bottom", mcLoc("block/farmland"))
                .texture("side", mcLoc("block/farmland"))
                .texture("top", mcLoc("block/farmland"));
        ModelFile farmland_slab_top = models().withExistingParent("farmland_slab_top", modLoc("block/lowered_slab_top"))
                .texture("bottom", mcLoc("block/farmland"))
                .texture("side", mcLoc("block/farmland"))
                .texture("top", mcLoc("block/farmland"));
        ModelFile farmland_slab_moist = models().withExistingParent("farmland_slab_moist", modLoc("block/lowered_slab"))
                .texture("bottom", mcLoc("block/farmland"))
                .texture("side", mcLoc("block/farmland"))
                .texture("top", mcLoc("block/farmland_moist"));
        ModelFile farmland_slab_moist_top = models().withExistingParent("farmland_slab_moist_top", modLoc("block/lowered_slab_top"))
                .texture("bottom", mcLoc("block/farmland"))
                .texture("side", mcLoc("block/farmland"))
                .texture("top", mcLoc("block/farmland_moist"));
        for (int moisture = 0; moisture <= 7; moisture++) {
            ModelFile bottomModel = farmland_slab;
            ModelFile topModel = farmland_slab_top;
            ModelFile doubleModel = farmland;

            if (moisture == 7) {
                bottomModel = farmland_slab_moist;
                topModel = farmland_slab_moist_top;
                doubleModel = farmland_moist;
            }

            getVariantBuilder(BlockRegistry.FARMLAND_SLAB.get())
                    .partialState().with(FarmlandSlabBlock.MOISTURE, moisture).with(SlabBlock.TYPE, SlabType.BOTTOM).modelForState().modelFile(bottomModel).addModel()
                    .partialState().with(FarmlandSlabBlock.MOISTURE, moisture).with(SlabBlock.TYPE, SlabType.TOP).modelForState().modelFile(topModel).addModel()
                    .partialState().with(FarmlandSlabBlock.MOISTURE, moisture).with(SlabBlock.TYPE, SlabType.DOUBLE).modelForState().modelFile(doubleModel).addModel();
        }

        ModelFile normal = models().withExistingParent("rubber_log", "minecraft:block/cube_column")
                .texture("end", modLoc("block/rubber_log_top"))
                .texture("side", modLoc("block/rubber_log"));
        ModelFile uncut = models().withExistingParent("rubber_log_drained", "minecraft:block/cube_column_horizontal")
                .texture("end", modLoc("block/rubber_log_top"))
                .texture("north", modLoc("block/rubber_log_drained"))
                .texture("south", modLoc("block/rubber_log"))
                .texture("east", modLoc("block/rubber_log"))
                .texture("west", modLoc("block/rubber_log"))
                .texture("particle", modLoc("block/rubber_log_drained"));
        ModelFile cut = models().withExistingParent("rubber_log_open", "minecraft:block/cube_column_horizontal")
                .texture("end", modLoc("block/rubber_log_top"))
                .texture("north", modLoc("block/rubber_log_open"))
                .texture("south", modLoc("block/rubber_log"))
                .texture("east", modLoc("block/rubber_log"))
                .texture("west", modLoc("block/rubber_log"))
                .texture("particle", modLoc("block/rubber_log_open"));
        getVariantBuilder(BlockRegistry.RUBBER_LOG_GENERATED.get())
            .partialState().with(RubberLogGeneratedBlock.OPEN, false).with(RubberLogGeneratedBlock.CUT, false).modelForState().modelFile(normal).addModel()
            .partialState().with(RubberLogGeneratedBlock.OPEN, false).with(RubberLogGeneratedBlock.CUT, true).modelForState().modelFile(normal).addModel()

            // CLOSED STATES
            .partialState().with(RubberLogGeneratedBlock.OPEN, true).with(RubberLogGeneratedBlock.CUT, false).with(RubberLogGeneratedBlock.FACING, Direction.NORTH).modelForState().modelFile(uncut).rotationY(0).addModel()
            .partialState().with(RubberLogGeneratedBlock.OPEN, true).with(RubberLogGeneratedBlock.CUT, false).with(RubberLogGeneratedBlock.FACING, Direction.SOUTH).modelForState().modelFile(uncut).rotationY(180).addModel()
            .partialState().with(RubberLogGeneratedBlock.OPEN, true).with(RubberLogGeneratedBlock.CUT, false).with(RubberLogGeneratedBlock.FACING, Direction.EAST).modelForState().modelFile(uncut).rotationY(90).addModel()
            .partialState().with(RubberLogGeneratedBlock.OPEN, true).with(RubberLogGeneratedBlock.CUT, false).with(RubberLogGeneratedBlock.FACING, Direction.WEST).modelForState().modelFile(uncut).rotationY(270).addModel()

            // OPEN STATES
            .partialState().with(RubberLogGeneratedBlock.OPEN, true).with(RubberLogGeneratedBlock.CUT, true).with(RubberLogGeneratedBlock.FACING, Direction.NORTH).modelForState().modelFile(cut).rotationY(0).addModel()
            .partialState().with(RubberLogGeneratedBlock.OPEN, true).with(RubberLogGeneratedBlock.CUT, true).with(RubberLogGeneratedBlock.FACING, Direction.SOUTH).modelForState().modelFile(cut).rotationY(180).addModel()
            .partialState().with(RubberLogGeneratedBlock.OPEN, true).with(RubberLogGeneratedBlock.CUT, true).with(RubberLogGeneratedBlock.FACING, Direction.EAST).modelForState().modelFile(cut).rotationY(90).addModel()
            .partialState().with(RubberLogGeneratedBlock.OPEN, true).with(RubberLogGeneratedBlock.CUT, true).with(RubberLogGeneratedBlock.FACING, Direction.WEST).modelForState().modelFile(cut).rotationY(270).addModel();
        
    }

}

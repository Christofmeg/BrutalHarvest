package com.christofmeg.brutalharvest.client.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.block.*;
import com.christofmeg.brutalharvest.common.block.base.BaseDoubleCropBlock;
import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public class BrutalBlockStateProvider extends BlockStateProvider {

    private final ResourceLocation CUTOUT = new ResourceLocation("minecraft:cutout");

    public BrutalBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CommonConstants.MOD_ID, exFileHelper);
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
    }

    public void makeCrop(Block block, IntegerProperty ageProperty) {
        String name = block.getDescriptionId().replace("block.brutalharvest.", "");
        getVariantBuilder(block).forAllStates(state -> {
            String modelName = name + "_stage" + state.getValue(ageProperty);
            ResourceLocation textureLocation = new ResourceLocation(CommonConstants.MOD_ID, "block/" + modelName);
            ConfiguredModel model = new ConfiguredModel(models().crop(modelName, textureLocation).renderType(CUTOUT));
            return new ConfiguredModel[]{model};
        });
    }

    public void makeCrop(Block block, IntegerProperty ageProperty, ResourceLocation customModel) {
        String name = block.getDescriptionId().replace("block.brutalharvest.", "");
        getVariantBuilder(block).forAllStates(state -> {
            String modelName = name + "_stage" + state.getValue(ageProperty);
            ResourceLocation textureLocation = new ResourceLocation(CommonConstants.MOD_ID, "block/" + modelName);
            ConfiguredModel model = new ConfiguredModel(models().withExistingParent(modelName, customModel).texture("cross", textureLocation).renderType(CUTOUT));
            return new ConfiguredModel[]{model};
        });
    }

    @SuppressWarnings("unused")
    public void makeDoubleCrop(Block block, int growthStages, int firstStageWithLowerUpper) {
        String name = block.getDescriptionId().replace("block.brutalharvest.", "");
        if (block instanceof BaseDoubleCropBlock baseCropBlock) {
            getVariantBuilder(block).forAllStates(state -> {
                int age = baseCropBlock.getAge(state);
                String modelName = name + "_stage" + age;
                if (age >= firstStageWithLowerUpper) {
                    if (age < growthStages) {
                        modelName = name + "_stage" + age + "_lower";
                    } else {
                        modelName = name + "_stage" + (age - growthStages + firstStageWithLowerUpper) + "_upper";
                    }
                }
                ResourceLocation textureLocation = new ResourceLocation(CommonConstants.MOD_ID, "block/" + modelName);
                ConfiguredModel model = new ConfiguredModel(models().crop(modelName, textureLocation).renderType(CUTOUT));
                return new ConfiguredModel[]{model};
            });
        }
    }

    public void makeDoubleCrop(Block block, int growthStages, int firstStageWithLowerUpper, ResourceLocation customModel) {
        String name = block.getDescriptionId().replace("block.brutalharvest.", "");
        if (block instanceof BaseDoubleCropBlock baseCropBlock) {
            getVariantBuilder(block).forAllStates(state -> {
                int age = baseCropBlock.getAge(state);
                String modelName = name + "_stage" + age;
                if (age >= firstStageWithLowerUpper) {
                    if (age < growthStages) {
                        modelName = name + "_stage" + age + "_lower";
                    } else {
                        modelName = name + "_stage" + (age - growthStages + firstStageWithLowerUpper) + "_upper";
                    }
                }
                ResourceLocation textureLocation = new ResourceLocation(CommonConstants.MOD_ID, "block/" + modelName);
                ConfiguredModel model = new ConfiguredModel(models().withExistingParent(modelName, customModel).texture("cross", textureLocation).renderType(CUTOUT));
                return new ConfiguredModel[]{model};
            });
        }
    }

    public void makeBush(Block block, IntegerProperty ageProperty) {
        String name = block.getDescriptionId().replace("block.brutalharvest.", "");
        getVariantBuilder(block).forAllStates(state -> {
            String modelName = name + "_stage" + state.getValue(ageProperty);
            ResourceLocation textureLocation = new ResourceLocation(CommonConstants.MOD_ID, "block/" + modelName);
            ConfiguredModel model = new ConfiguredModel(models().cross(modelName, textureLocation).renderType(CUTOUT));
            return new ConfiguredModel[]{model};
        });
    }

}

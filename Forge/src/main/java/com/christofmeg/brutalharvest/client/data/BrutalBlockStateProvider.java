package com.christofmeg.brutalharvest.client.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.block.CottonCropBlock;
import com.christofmeg.brutalharvest.common.block.SugarBeetCropBlock;
import com.christofmeg.brutalharvest.common.block.TomatoCropBlock;
import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

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

        makeTomatoCrop(BlockRegistry.TOMATO.get());

        cornBlockCrop(BlockRegistry.CORN.get(), "0");
        cornBlockCrop(BlockRegistry.CORN.get(), "1");
        cornBlockCrop(BlockRegistry.CORN.get(), "2_lower");
        cornBlockFull(BlockRegistry.CORN.get(), "2_upper");
        cornBlockCrop(BlockRegistry.CORN.get(), "3_lower");
        cornBlockFull(BlockRegistry.CORN.get(), "3_upper");
        cornBlockCrop(BlockRegistry.CORN.get(), "4_lower");
        cornBlockFull(BlockRegistry.CORN.get(), "4_upper");
        cornBlockCrop(BlockRegistry.CORN.get(), "5_lower");
        cornBlockFull(BlockRegistry.CORN.get(), "5_upper");

        makeCottonCrop(BlockRegistry.COTTON.get());
        makeSugarBeetCrop(BlockRegistry.SUGAR_BEET.get());
    }

    private void cornBlockFull(Block block, String name) {
        this.models().cross(name(block) + "_stage" + name,
                new ResourceLocation(CommonConstants.MOD_ID,
                        "block/" + name(block) + "_stage" + name)).renderType(CUTOUT);
    }

    private void cornBlockCrop(Block block, String name) {
        this.models().singleTexture(name(block) + "_stage" + name,  new ResourceLocation(CommonConstants.MOD_ID + ":" + "block/lowered_cross"), "cross", new ResourceLocation(CommonConstants.MOD_ID,
                "block/" + name(block) + "_stage" + name)).renderType(CUTOUT);
    }

    private String name(Block block) {
        return Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
    }

    public void makeTomatoCrop(Block block) {
        getVariantBuilder(block).forAllStates(state -> {
            String modelName = "tomato_stage" + state.getValue(((TomatoCropBlock) block).getAgeProperty());
            ResourceLocation textureLocation = new ResourceLocation(CommonConstants.MOD_ID, "block/" + modelName);
            ConfiguredModel model = new ConfiguredModel(models().crop(modelName, textureLocation).renderType(CUTOUT));
            return new ConfiguredModel[]{model};
        });
    }

    public void makeCottonCrop(Block block) {
        getVariantBuilder(block).forAllStates(state -> {
            String modelName = "cotton_stage" + state.getValue(((CottonCropBlock) block).getAgeProperty());
            ResourceLocation textureLocation = new ResourceLocation(CommonConstants.MOD_ID, "block/" + modelName);
            ConfiguredModel model = new ConfiguredModel(models().crop(modelName, textureLocation).renderType(CUTOUT));
            return new ConfiguredModel[]{model};
        });
    }

    public void makeSugarBeetCrop(Block block) {
        getVariantBuilder(block).forAllStates(state -> {
            String modelName = "sugar_beet_stage" + state.getValue(((SugarBeetCropBlock) block).getAgeProperty());
            ResourceLocation textureLocation = new ResourceLocation(CommonConstants.MOD_ID, "block/" + modelName);
            ConfiguredModel model = new ConfiguredModel(models().crop(modelName, textureLocation).renderType(CUTOUT));
            return new ConfiguredModel[]{model};
        });
    }

}

package com.christofmeg.brutalharvest.client.data.base;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.block.base.BaseDoubleCropBlock;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class BaseBlockStateProvider extends BlockStateProvider {

    private final BlockModelProvider vanillaBlockModels;
    private final ResourceLocation CUTOUT = new ResourceLocation("minecraft:cutout");

    public BaseBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CommonConstants.MOD_ID, exFileHelper);
        this.vanillaBlockModels = new BlockModelProvider(output, "minecraft", exFileHelper) {
            public CompletableFuture<?> run(CachedOutput cache) {
                return CompletableFuture.allOf();
            }
            protected void registerModels() {
            }
        };
    }

    public BlockModelProvider vanillaModels() {
        return this.vanillaBlockModels;
    }

    @Override
    protected void registerStatesAndModels() {}

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

    public void makeDoubleCrop(Block block, int growthStages, int firstStageWithLowerUpper, ResourceLocation lowerModel, ResourceLocation upperModel) {
        String name = block.getDescriptionId().replace("block.brutalharvest.", "");
        if (block instanceof BaseDoubleCropBlock baseCropBlock) {
            getVariantBuilder(block).forAllStates(state -> {
                int age = baseCropBlock.getAge(state);
                String modelName = name + "_stage" + age;
                if (age >= firstStageWithLowerUpper) {
                    if (age < growthStages) {
                        modelName = name + "_stage" + age + "_lower";
                        ResourceLocation textureLocation = new ResourceLocation(CommonConstants.MOD_ID, "block/" + modelName);
                        ConfiguredModel model = new ConfiguredModel(models().withExistingParent(modelName, lowerModel).texture("cross", textureLocation).renderType(CUTOUT));
                        return new ConfiguredModel[]{model};
                    } else {
                        modelName = name + "_stage" + (age - growthStages + firstStageWithLowerUpper) + "_upper";
                        ResourceLocation textureLocation = new ResourceLocation(CommonConstants.MOD_ID, "block/" + modelName);
                        ConfiguredModel model = new ConfiguredModel(models().withExistingParent(modelName, upperModel).texture("cross", textureLocation).renderType(CUTOUT));
                        return new ConfiguredModel[]{model};
                    }
                }
                ResourceLocation textureLocation = new ResourceLocation(CommonConstants.MOD_ID, "block/" + modelName);
                ConfiguredModel model = new ConfiguredModel(models().withExistingParent(modelName, lowerModel).texture("cross", textureLocation).renderType(CUTOUT));
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

    public void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get())).getPath(), blockTexture(blockRegistryObject.get())).renderType(CUTOUT));
    }

    public void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get())).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType(CUTOUT));
    }

    public void slabBlock(Block block, ResourceLocation doubleslab, ResourceLocation texture) {
        this.slabBlock(block, doubleslab, texture, texture, texture);
    }

    public void slabBlock(Block block, ResourceLocation doubleslab, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        this.slabBlock(block,
                this.models().slab(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath(), side, bottom, top),
                this.models().slabTop(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath() + "_top", side, bottom, top),
                this.models().getExistingFile(doubleslab)
        );
    }

    public void slabBlock(Block block, ModelFile bottom, ModelFile top, ModelFile doubleslab) {
        this.getVariantBuilder(block).partialState().with(SlabBlock.TYPE, SlabType.BOTTOM).addModels(new ConfiguredModel(bottom)).partialState().with(SlabBlock.TYPE, SlabType.TOP).addModels(new ConfiguredModel(top)).partialState().with(SlabBlock.TYPE, SlabType.DOUBLE).addModels(new ConfiguredModel(doubleslab));
    }

}

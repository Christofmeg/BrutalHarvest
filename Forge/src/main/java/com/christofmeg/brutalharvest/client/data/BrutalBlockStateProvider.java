package com.christofmeg.brutalharvest.client.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
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

        cropBlock(BlockRegistry.TOMATO.get(), 8);

        BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
            .forEach(block -> {



            });

    }

    public void cropBlock(Block block, int stages) {
        List<ConfiguredModel> modelsList = new ArrayList<>();

        for (int i = 0; i <= stages; i++) {
            modelsList.add(new ConfiguredModel(models()
                    .crop(name(block) + "_stage" + i,
                            new ResourceLocation(CommonConstants.MOD_ID,
                                    "block/" + name(block) + "_stage" + i))
                    .renderType(CUTOUT)
            ));
        }

  //      ConfiguredModel[] modelsArray = modelsList.toArray(new ConfiguredModel[0]);
  //      simpleBlock(block, modelsArray);


//        this.models().crop(name(block) + "stage_1", new ResourceLocation(CommonConstants.MOD_ID,
//                "block/" + name(block) + "_stage" + 1));

    }

    private String name(Block block) {
        return Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
    }

}

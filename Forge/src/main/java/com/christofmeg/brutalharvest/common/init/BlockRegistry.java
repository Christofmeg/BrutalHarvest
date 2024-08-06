package com.christofmeg.brutalharvest.common.init;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.block.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CommonConstants.MOD_ID);
    public static BlockBehaviour. Properties cropPropeties = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY);

//    public static final RegistryObject<Block> CROP_SUPPORT;
    public static final RegistryObject<Block> TOMATO;
    public static final RegistryObject<Block> LETTUCE;
    public static final RegistryObject<Block> CORN;
    public static final RegistryObject<Block> CUCUMBER;
    public static final RegistryObject<Block> COTTON;
    public static final RegistryObject<Block> RAPESEED;
    public static final RegistryObject<Block> SUGAR_BEET;
    public static final RegistryObject<Block> STRAWBERRY;
//    public static final RegistryObject<Block> ONION;
    public static final RegistryObject<Block> BLUEBERRY;
    public static final RegistryObject<Block> RUBBER_SAPLING;
    public static final RegistryObject<Block> RUBBER_LOG;
    public static final RegistryObject<Block> RUBBER_WOOD;
    public static final RegistryObject<Block> STRIPPED_RUBBER_LOG;
    public static final RegistryObject<Block> STRIPPED_RUBBER_WOOD;
    public static final RegistryObject<Block> RUBBER_PLANKS;
    public static final RegistryObject<Block> RUBBER_LEAVES;

//    public static final RegistryObject<Block> RUBBER_CAULDRON;
//    public static final RegistryObject<Block> MILLSTONE;

    private BlockRegistry() {
    }

    public static void init(@Nonnull IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }

    static {
//        CROP_SUPPORT = BLOCKS.register("crop_support", () -> new CropSupportBlock(BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.BAMBOO_WOOD).pushReaction(PushReaction.DESTROY)));
//        ItemRegistry.ITEMS.register("crop_support", () -> new BlockItem(CROP_SUPPORT.get(), new Item.Properties()));

        TOMATO = BLOCKS.register("tomato", () -> new TomatoCropBlock(cropPropeties));
        LETTUCE = BLOCKS.register("lettuce", () -> new LettuceCropBlock(cropPropeties));
        CORN = BLOCKS.register("corn", () -> new CornCropBlock(cropPropeties));
        CUCUMBER = BLOCKS.register("cucumber", () -> new CucumberCropBlock(cropPropeties));
        COTTON = BLOCKS.register("cotton", () -> new CottonCropBlock(cropPropeties));
        RAPESEED = BLOCKS.register("rapeseed", () -> new RapeseedCropBlock(cropPropeties));
        SUGAR_BEET = BLOCKS.register("sugar_beet", () -> new SugarBeetCropBlock(cropPropeties));
        STRAWBERRY = BLOCKS.register("strawberry", () -> new StrawberryCropBlock(cropPropeties));
//        ONION = BLOCKS.register("onion", () -> new OnionCropBlock(cropPropeties));
        BLUEBERRY = BLOCKS.register("blueberry", () -> new BlueberryBushBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY)));

        RUBBER_SAPLING = block("rubber_sapling", BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING));
        RUBBER_LOG = block("rubber_log", BlockBehaviour.Properties.copy(Blocks.OAK_LOG));
        RUBBER_WOOD = block("rubber_wood", BlockBehaviour.Properties.copy(Blocks.OAK_WOOD));
        STRIPPED_RUBBER_LOG = block("stripped_rubber_log", BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG));
        STRIPPED_RUBBER_WOOD = block("stripped_rubber_wood", BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD));
        RUBBER_PLANKS = block("rubber_planks", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS));
        RUBBER_LEAVES = BLOCKS.register("rubber_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));




        //        RUBBER_CAULDRON = BLOCKS.register("rubber_cauldron", () -> new LayeredCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON), LayeredCauldronBlock.RAIN, CauldronInteraction.WATER));

    }

    private static RegistryObject<Block> block(String name, BlockBehaviour.Properties blockProperties) {
        RegistryObject<Block> block = BLOCKS.register(name, () -> new Block(blockProperties));
        ItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

}

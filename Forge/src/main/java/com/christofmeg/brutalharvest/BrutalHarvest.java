package com.christofmeg.brutalharvest;

import com.christofmeg.brutalharvest.client.event.ClientSetupEvent;
import com.christofmeg.brutalharvest.common.event.CommonSetupEvent;
import com.christofmeg.brutalharvest.common.init.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib.GeckoLib;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
@Mod(CommonConstants.MOD_ID)
public class BrutalHarvest {

    public BrutalHarvest() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        GeckoLib.initialize();
        init(bus);
        bus.addListener(new ClientSetupEvent()::clientSetupEvent);
        bus.addListener(new ClientSetupEvent()::registerLayerDefinitions);
        bus.addListener(new ClientSetupEvent()::registerBlockColors);
        bus.addListener(new ClientSetupEvent()::registerItemColors);

        bus.addListener(new CommonSetupEvent()::commonSetupEvent);
    }

    private void init(@Nonnull IEventBus modEventBus) {
        MinecraftForge.EVENT_BUS.register(this);
        BlockRegistry.init(modEventBus);
        ItemRegistry.init(modEventBus);
        CreativeModeTabRegistry.init(modEventBus);
        EntityTypeRegistry.init(modEventBus);
        AdvancementRegistry.register();
        LootModifierRegistry.init(modEventBus);
        SoundRegistry.init(modEventBus);
        RecipeSerializerRegistry.init(modEventBus);
        TrunkPlacerTypeRegistry.init(modEventBus);
        EnchantmentRegistry.init(modEventBus);
    }

    /*TODO
     * FarmingForBlockheads data/farmingforblockheads/recipes/market/brutalharvest/tomato_seeds.json
     * {"type":"farmingforblockheads:market","category":"farmingforblockheads:seeds","preset":"brutalharvest:seeds","result":{"item":"brutalharvest:tomato_seeds"}}
     * + optional dependency in build.gradle file
     */

    //TODO serene seasons crops https://github.com/vectorwing/FarmersDelight/tree/1.19/src/generated/resources/data/sereneseasons/tags/items

}

package com.christofmeg.brutalharvest;

import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import com.christofmeg.brutalharvest.common.init.CreativeModeTabRegistry;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
@Mod(CommonConstants.MOD_ID)
public class BrutalHarvest {

    public BrutalHarvest() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        init(bus);
    }

    private void init(@Nonnull IEventBus modEventBus) {
        MinecraftForge.EVENT_BUS.register(this);
        BlockRegistry.init(modEventBus);
        ItemRegistry.init(modEventBus);
        CreativeModeTabRegistry.REGISTRY.register(modEventBus);
    }



    /*TODO
     * FarmingForBlockheads data/farmersdelight/tags/items/tools/knives.json
     * {
     * "values": [
     * "brutalharvest:flint_knife",
     * "brutalharvest:wooden_knife",
     * "brutalharvest:stone_knife",
     * "brutalharvest:copper_knife",
     * "brutalharvest:iron_knife",
     * "brutalharvest:golden_knife",
     * "brutalharvest:diamond_knife",
     * "brutalharvest:netherite_knife"
     * ]
     * }
     * + optional dependency in build.gradle file
     */

    /*TODO
     * FarmingForBlockheads data/farmingforblockheads/recipes/market/brutalharvest/tomato_seeds.json
     * {"type":"farmingforblockheads:market","category":"farmingforblockheads:seeds","preset":"brutalharvest:seeds","result":{"item":"brutalharvest:tomato_seeds"}}
     * + optional dependency in build.gradle file
     */

    //TODO Piglin loved on golden items
    //TODO serene seasons crops https://github.com/vectorwing/FarmersDelight/tree/1.19/src/generated/resources/data/sereneseasons/tags/items

    //TODO "#forge:crops/tomato" tag "#forge:crops/lettuce" tag ...

}

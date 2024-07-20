package com.christofmeg.brutalharvest.common.event;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryObject;


@Mod.EventBusSubscriber(modid = CommonConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonSetupEvent {

    public void commonSetupEvent(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            compost(ItemRegistry.UNRIPE_TOMATO, 0.30F);
            compost(ItemRegistry.TOMATO, 0.65F);
            compost(ItemRegistry.ROTTEN_TOMATO, 0.85F);
            compost(ItemRegistry.TOMATO_SLICE, 0.30F);

            compost(ItemRegistry.LETTUCE, 0.65F);
            compost(ItemRegistry.SLICED_LETTUCE, 0.50F);

            compost(ItemRegistry.CORN, 0.65F);

            compost(ItemRegistry.CUCUMBER, 0.65F);
            compost(ItemRegistry.CUCUMBER_SLICE, 0.50F);
            compost(ItemRegistry.PICKLE, 0.65F);
/*
            compost(ItemRegistry.COFFEE_BEANS, 0.30F);
            compost(ItemRegistry.DRIED_COFFEE_BEANS, 0.30F);
            compost(ItemRegistry.COFFEE_POWDER, 0.30F);
*/
//          compost(ItemRegistry.RAPESEED_BEANS, 0.30F);

            compost(ItemRegistry.SUGAR_BEET, 0.65F);

            compost(ItemRegistry.UNRIPE_STRAWBERRY, 0.45F);
            compost(ItemRegistry.STRAWBERRY, 0.65F);

//          compost(ItemRegistry.ONION, 0.65F);
/*
            compost(ItemRegistry.GREEN_CHILI_PEPPER, 0.85F);
            compost(ItemRegistry.YELLOW_CHILI_PEPPER, 0.85F);
            compost(ItemRegistry.RED_PEPPER_SEEDS, 0.65F);
*/
//          compost(ItemRegistry.COOKED_RICE, 0.40F);

            compost(ItemRegistry.TOMATO_SEEDS, 0.30F);
            compost(ItemRegistry.LETTUCE_SEEDS, 0.30F);
            compost(ItemRegistry.CORN_SEEDS, 0.30F);
            compost(ItemRegistry.CUCUMBER_SEEDS, 0.30F);
            compost(ItemRegistry.COTTON_SEEDS, 0.30F);
//          compost(ItemRegistry.COFFEE_CHERRY, 0.30F);
            compost(ItemRegistry.RAPESEEDS, 0.30F);
            compost(ItemRegistry.SUGAR_BEET_SEEDS, 0.30F);
            compost(ItemRegistry.STRAWBERRY_SEEDS, 0.30F);
//          compost(ItemRegistry.ONION_SEEDS, 0.30F);
//          compost(ItemRegistry.CHILI_PEPPER_SEEDS, 0.30F);
//          compost(ItemRegistry.RICE, 0.30F);

            compost(ItemRegistry.BLUEBERRY, 0.50F);

        });
    }

    private void compost(RegistryObject<Item> item, float value) {
        ComposterBlock.COMPOSTABLES.put(item.get(), value);
    }

    /*
    @SubscribeEvent
    public static void onLivingSpecialSpawn(final MobSpawnEvent.FinalizeSpawn event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Zombie && !(entity instanceof Drowned) && !(entity instanceof Husk) && !(entity instanceof ZombifiedPiglin) && !(entity instanceof ZombieVillager)) {
            RandomSource random = event.getLevel().getRandom();
            float randomF = random.nextFloat();
            if (randomF < 0.05F) {
                if (entity.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
                    ItemStack stack = ItemRegistry.GARDENERS_HAT.get().getDefaultInstance();
                    entity.setItemSlot(EquipmentSlot.HEAD, stack);
                }
            }
        }
    }
     */

}

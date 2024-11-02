package com.christofmeg.brutalharvest.common.event;

import com.christofmeg.brutalharvest.CommonConstants;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = CommonConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonSetupEvent {

    public void commonSetupEvent(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlockCompostables.registerCompostables();
            HoeItemTillables.registerTillables();
        });
    }

    @SubscribeEvent
    public static void tillFarmlandEvent(final BlockEvent.BlockToolModificationEvent event) {
        BlockState state = event.getState();
        ToolAction toolAction = event.getToolAction();
        UseOnContext context = event.getContext();
        if (!event.isSimulated() && toolAction == ToolActions.HOE_TILL) {
            var pair = HoeItemTillables.TILLABLES.get(state.getBlock());
            if (pair != null && pair.getFirst().test(context)) {
                pair.getSecond().accept(context);
                event.getLevel().playSound(event.getPlayer(), event.getPos(), SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                event.setResult(Event.Result.ALLOW);
                Objects.requireNonNull(event.getPlayer()).swing(context.getHand());
            }
        }
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

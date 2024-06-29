package com.christofmeg.brutalharvest.client.event;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.client.model.BrutalModelLayers;
import com.christofmeg.brutalharvest.client.model.KnifeModel;
import com.christofmeg.brutalharvest.client.renderer.entity.ThrownKnifeRenderer;
import com.christofmeg.brutalharvest.common.init.EntityTypeRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = CommonConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetupEvent {

    public void clientSetupEvent(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            EntityRenderers.register(EntityTypeRegistry.TOMATO_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(EntityTypeRegistry.THROWING_KNIFE.get(), ThrownKnifeRenderer::new);
        });
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BrutalModelLayers.THROWING_KNIFE, KnifeModel::createLayer);
    }

}

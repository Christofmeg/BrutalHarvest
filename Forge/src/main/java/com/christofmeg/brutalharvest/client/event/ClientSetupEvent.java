package com.christofmeg.brutalharvest.client.event;

import com.christofmeg.brutalharvest.client.model.ThrownKnifeModel;
import com.christofmeg.brutalharvest.client.model.ThrownScytheModel;
import com.christofmeg.brutalharvest.client.renderer.RenderLayers;
import com.christofmeg.brutalharvest.client.renderer.ThrownKnifeRenderer;
import com.christofmeg.brutalharvest.client.renderer.ThrownScytheRenderer;
import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import com.christofmeg.brutalharvest.common.init.EntityTypeRegistry;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.level.GrassColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
public class ClientSetupEvent {

    public void clientSetupEvent(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            EntityRenderers.register(EntityTypeRegistry.TOMATO_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(EntityTypeRegistry.THROWN_SCYTHE.get(), ThrownScytheRenderer::new);
            EntityRenderers.register(EntityTypeRegistry.THROWN_KNIFE.get(), ThrownKnifeRenderer::new);
        });
    }

    public void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(RenderLayers.register("scythe"), ThrownScytheModel::createLayer);
        event.registerLayerDefinition(RenderLayers.register("knife"), ThrownKnifeModel::createLayer);
    }

    public void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : -1, BlockRegistry.GRASS_SLAB.get());
    }

    public void registerItemColors(final RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> GrassColor.get(0.5D, 1.0D), BlockRegistry.GRASS_SLAB.get());
    }

}

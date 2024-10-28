package com.christofmeg.brutalharvest.client.renderer;

import com.christofmeg.brutalharvest.CommonConstants;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderLayers {

    public static ModelLayerLocation register(String name) {
        return new ModelLayerLocation(new ResourceLocation(CommonConstants.MOD_ID, name), "main");
    }

}

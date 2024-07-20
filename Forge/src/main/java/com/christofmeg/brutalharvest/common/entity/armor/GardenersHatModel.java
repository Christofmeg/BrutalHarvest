package com.christofmeg.brutalharvest.common.entity.armor;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.item.GardenersHatItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GardenersHatModel extends GeoModel<GardenersHatItem> {

    @Override
    public ResourceLocation getModelResource(GardenersHatItem object) {
        return new ResourceLocation(CommonConstants.MOD_ID, "geo/gardeners_hat.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GardenersHatItem object) {
        return new ResourceLocation(CommonConstants.MOD_ID, "textures/models/armor/gardeners_hat.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GardenersHatItem animatable) {
        return new ResourceLocation(CommonConstants.MOD_ID, "animations/armor.animation.json");
    }

}
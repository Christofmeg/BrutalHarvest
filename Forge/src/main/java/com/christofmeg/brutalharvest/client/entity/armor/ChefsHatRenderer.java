package com.christofmeg.brutalharvest.client.entity.armor;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.item.ChefsHatItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class ChefsHatRenderer extends AbstractRenderer<ChefsHatItem> {

    public ChefsHatRenderer() {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(CommonConstants.MOD_ID, "textures/models/armor/chefs_hat_layer_1.png")) {
            @Override
            public ResourceLocation getModelResource(ChefsHatItem object) {
                return new ResourceLocation(CommonConstants.MOD_ID, "geo/chefs_hat.geo.json");
            }

            @Override
            public ResourceLocation getTextureResource(ChefsHatItem object) {
                return new ResourceLocation(CommonConstants.MOD_ID, "textures/models/armor/chefs_hat_layer_1.png");
            }

            @Override
            public ResourceLocation getAnimationResource(ChefsHatItem animatable) {
                return new ResourceLocation(CommonConstants.MOD_ID, "animations/armor.animation.json");
            }
        });
    }

}
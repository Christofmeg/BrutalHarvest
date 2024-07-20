package com.christofmeg.brutalharvest.client.entity.armor;

import com.christofmeg.brutalharvest.common.item.CosmeticItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public abstract class AbstractRenderer<T extends CosmeticItem> extends GeoArmorRenderer<T> {

    public AbstractRenderer(GeoModel<T> model) {
        super(model);
    }

}

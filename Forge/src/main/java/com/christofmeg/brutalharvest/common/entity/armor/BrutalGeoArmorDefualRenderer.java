package com.christofmeg.brutalharvest.common.entity.armor;

import net.minecraft.world.item.ArmorItem;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public abstract class BrutalGeoArmorDefualRenderer<T extends ArmorItem & GeoItem> extends GeoArmorRenderer<T> {

    public BrutalGeoArmorDefualRenderer(GeoModel<T> modelProvider) {
        super(modelProvider);
    }

}
package com.christofmeg.brutalharvest.common.item;

import com.christofmeg.brutalharvest.common.entity.armor.BrutalGeoArmorDefualRenderer;
import com.christofmeg.brutalharvest.common.entity.armor.GardenersHatRenderer;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GardenersHatItem extends CosmeticItem {

    public GardenersHatItem(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial, type, pProperties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    protected BrutalGeoArmorDefualRenderer<?> getRenderer() {
        return new GardenersHatRenderer();
    }

}
package com.christofmeg.brutalharvest.common.item;

import com.christofmeg.brutalharvest.client.entity.armor.ChefsApronRenderer;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class ChefsApronItem extends CosmeticItem {

    public ChefsApronItem(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial, type, pProperties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    protected GeoArmorRenderer<?> getRenderer() {
        return new ChefsApronRenderer();
    }

}
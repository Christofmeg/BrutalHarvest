package com.christofmeg.brutalharvest.common.item;

import com.christofmeg.brutalharvest.client.entity.armor.AbstractRenderer;
import com.christofmeg.brutalharvest.client.entity.armor.ChefsHatRenderer;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ChefsHatItem extends CosmeticItem {

    public ChefsHatItem(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial, type, pProperties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    protected AbstractRenderer<?> getRenderer() {
        return new ChefsHatRenderer();
    }

}
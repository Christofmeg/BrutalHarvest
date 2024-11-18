package com.christofmeg.brutalharvest.common.item;

import com.christofmeg.brutalharvest.client.entity.armor.AbstractRenderer;
import com.christofmeg.brutalharvest.client.entity.armor.DungareeRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.function.Consumer;

public class DungareeItem extends CosmeticItem {

    private final String name;
    public DungareeItem(ArmorMaterial pMaterial, Type type, Properties pProperties, String name) {
        super(pMaterial, type, pProperties);
        this.name = name;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    protected GeoArmorRenderer<?> getRenderer() {
        return new DungareeRenderer(name);
    }

}
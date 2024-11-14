package com.christofmeg.brutalharvest.common.item;

import com.christofmeg.brutalharvest.client.entity.armor.AbstractRenderer;
import com.christofmeg.brutalharvest.client.entity.armor.ChefsApronRenderer;
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

public class ChefsApronItem extends CosmeticItem {

    public ChefsApronItem(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial, type, pProperties);
    }

    @Override
    protected AbstractRenderer<?> getRenderer() {
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer){
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null) {
                    this.renderer = new ChefsApronRenderer();
                }

                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }

}
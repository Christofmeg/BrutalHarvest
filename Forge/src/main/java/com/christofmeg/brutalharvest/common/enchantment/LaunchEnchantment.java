package com.christofmeg.brutalharvest.common.enchantment;

import com.christofmeg.brutalharvest.common.item.KnifeItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class LaunchEnchantment extends Enchantment {

    public LaunchEnchantment() {
        super(Rarity.COMMON, EnchantmentCategory.create("launch", item -> item instanceof KnifeItem), new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof KnifeItem && super.canEnchant(stack);
    }

}

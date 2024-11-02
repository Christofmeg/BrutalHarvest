package com.christofmeg.brutalharvest.common.enchantment;

import com.christofmeg.brutalharvest.common.item.KnifeItem;
import com.christofmeg.brutalharvest.common.item.ScytheItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BoomerangEnchantment extends Enchantment {

    public BoomerangEnchantment() {
        super(Rarity.COMMON, EnchantmentCategory.create("boomerang", item -> item instanceof ScytheItem || item instanceof KnifeItem), new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof ScytheItem && super.canEnchant(stack);
    }

}

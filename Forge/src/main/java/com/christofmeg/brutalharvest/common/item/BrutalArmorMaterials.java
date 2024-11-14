package com.christofmeg.brutalharvest.common.item;

import com.christofmeg.brutalharvest.CommonConstants;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum BrutalArmorMaterials implements ArmorMaterial {

    GARDENERS_HAT("gardeners_hat", new int[] { 0, 0, 0, 0 }, 0,
    SoundEvents.ARMOR_EQUIP_GENERIC, 0, 0, () -> Ingredient.EMPTY),

    CHEFS_HAT("chefs_hat", new int[] { 0, 0, 0, 0 }, 0,
    SoundEvents.ARMOR_EQUIP_GENERIC, 0, 0, () -> Ingredient.EMPTY),

    BLUE_DUNGAREE("blue_dungaree", new int[] { 0, 0, 0, 0 }, 0,
    SoundEvents.ARMOR_EQUIP_GENERIC, 0, 0, () -> Ingredient.EMPTY),

    OLD_DUNGAREE("old_dungaree", new int[] { 0, 0, 0, 0 }, 0,
    SoundEvents.ARMOR_EQUIP_GENERIC, 0, 0, () -> Ingredient.EMPTY),

    BOOTS("boots", new int[] { 0, 0, 0, 0 }, 0,
    SoundEvents.ARMOR_EQUIP_GENERIC, 0, 0, () -> Ingredient.EMPTY),

    CHEFS_APRON("chefs_apron", new int[] { 0, 0, 0, 0 }, 0,
    SoundEvents.ARMOR_EQUIP_GENERIC, 0, 0, () -> Ingredient.EMPTY);

    private final String name;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    BrutalArmorMaterials(String name, int[] protectionAmounts, int enchantmentValue, SoundEvent equipSound,
                         float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.protectionAmounts = protectionAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.@NotNull Type pType) {
        return 0;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionAmounts[pType.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public @NotNull String getName() {
        return CommonConstants.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
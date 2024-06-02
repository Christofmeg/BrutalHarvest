package com.christofmeg.brutalharvest.common.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class BrutalFoods {

    public static final FoodProperties UNRIPE_TOMATO = new FoodProperties.Builder().nutrition(1).saturationMod(1).build();
    public static final FoodProperties TOMATO = new FoodProperties.Builder().nutrition(3).saturationMod(2.5F).build();
    public static final FoodProperties TOMATO_SLICE = new FoodProperties.Builder().nutrition(2).saturationMod(1.7F).build();
    public static final FoodProperties ROTTEN_TOMATO = new FoodProperties.Builder().nutrition(-2).saturationMod(1.2F).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 100), 1).build();
    public static final FoodProperties LETTUCE = new FoodProperties.Builder().nutrition(3).saturationMod(5).build();
    public static final FoodProperties SLICED_LETTUCE = new FoodProperties.Builder().nutrition(2).saturationMod(3.5F).build();
    public static final FoodProperties CORN = new FoodProperties.Builder().nutrition(4).saturationMod(3.4F).build();
    public static final FoodProperties CUCUMBER = new FoodProperties.Builder().nutrition(3).saturationMod(2.4F).build();
    public static final FoodProperties CUCUMBER_SLICES = new FoodProperties.Builder().nutrition(1).saturationMod(1).build();
    public static final FoodProperties PICKLES = new FoodProperties.Builder().nutrition(3).saturationMod(2.4F).build();

}

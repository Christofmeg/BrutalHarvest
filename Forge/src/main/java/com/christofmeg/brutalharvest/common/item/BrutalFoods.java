package com.christofmeg.brutalharvest.common.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

@SuppressWarnings("unused")
public class BrutalFoods {
    public static final FoodProperties UNRIPE_TOMATO = builder(1, 1F).effect(() -> new MobEffectInstance(MobEffects.POISON, 100), 0.6F).build();
    public static final FoodProperties TOMATO = builder(3, 2.5F).build();
    public static final FoodProperties ROTTEN_TOMATO = builder(-2, 1.2F).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200), 0.5F).build();
    public static final FoodProperties TOMATO_SLICE = builder(2, 1.7F).build();

    public static final FoodProperties LETTUCE = builder(3, 5F).build();
    public static final FoodProperties SLICED_LETTUCE = builder(1, 3.5F).build();

    public static final FoodProperties STRAWBERRY = builder(3, 2.4F).build();
    public static final FoodProperties UNRIPE_STRAWBERRY = builder(1, 1.3F).build();
    public static final FoodProperties BLUEBERRY = builder(2, 2.5F).build();

    public static final FoodProperties CORN = builder(4, 3.4F).build();
    public static final FoodProperties SUGAR_BEET = builder(2, 2.4F).build();

    public static final FoodProperties CUCUMBER = builder(3, 2.4F).build();
    public static final FoodProperties CUCUMBER_SLICES = builder(1, 1F).build();
    public static final FoodProperties PICKLES = builder(3, 2.4F).build();

    /*
    public static final FoodProperties ONION = builder(2, 2.4F).build();
    public static final FoodProperties CHILI_PEPPER = builder(2, 2.4F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600), 1.0F).build();
    public static final FoodProperties RICE = builder(1, 1.6F).build();

    public static final FoodProperties LOBSTER = builder(2, 2.4F).build();
    public static final FoodProperties RAW_LOBSTER = builder(-2, 1.6F).build();
    public static final FoodProperties SUSHI = builder(3, 2.3F).build();
    public static final FoodProperties CUCUMBER_SUSHI = builder(3, 2.3F).build();
    public static final FoodProperties LOBSTER_SUSHI = builder(4, 2.3F).build();
     */

    public static final FoodProperties FRIED_EGG = builder(4, 3.7F).build();
    public static final FoodProperties SCRAMBLED_EGG = builder(4, 2.8F).build();
    public static final FoodProperties BOILED_EGG = builder(4, 2.3F).build();

    public static final FoodProperties TOAST_HONEY = builder(4, 2.4F).build();
    public static final FoodProperties TOAST_STRAWBERRY = builder(3, 2.4F).build();
    public static final FoodProperties TOAST_BLUEBERRY = builder(3, 2.4F).build();

    public static final FoodProperties HONEY_JAR = builder(2, 1.3F).build();
    public static final FoodProperties STRAWBERRY_JAM = builder(3, 1.3F).build();
    public static final FoodProperties BLUEBERRY_JAM = builder(3, 1.3F).build();

    public static final FoodProperties TOAST_FRIED_EGG = builder(5, 3.5F).build();
    public static final FoodProperties TOAST_SCRAMBLED_EGG = builder(5, 2.7F).build();
    public static final FoodProperties TOAST_BOILED_EGG = builder(5, 1.3F).build();

    public static final FoodProperties TOAST_LOAF = builder(-1, 2.6F).build();
    public static final FoodProperties TOAST = builder(3, 3.5F).build();
    public static final FoodProperties TOAST_SLICE = builder(1, 1.9F).build();

    public static final FoodProperties POPCORN = builder(2, 3.2F).build();

    private static FoodProperties.Builder builder(int nutrition, float saturation) {
        return new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation / nutrition);
    }
}
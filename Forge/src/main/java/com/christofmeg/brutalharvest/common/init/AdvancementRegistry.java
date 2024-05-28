package com.christofmeg.brutalharvest.common.init;

import com.christofmeg.brutalharvest.common.advancement.TomatoProjectileTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class AdvancementRegistry {

    public static TomatoProjectileTrigger ROTTEN_TOMATOES = new TomatoProjectileTrigger();

    public static void register() {
        CriteriaTriggers.register(ROTTEN_TOMATOES);
    }

}

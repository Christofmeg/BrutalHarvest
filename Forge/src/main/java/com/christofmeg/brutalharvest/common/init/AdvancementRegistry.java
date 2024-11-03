package com.christofmeg.brutalharvest.common.init;

import com.christofmeg.brutalharvest.common.advancement.ThrownKnifeTrigger;
import com.christofmeg.brutalharvest.common.advancement.ThrownScytheTrigger;
import com.christofmeg.brutalharvest.common.advancement.TomatoProjectileTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class AdvancementRegistry {

    public static TomatoProjectileTrigger ROTTEN_TOMATOES = new TomatoProjectileTrigger();
    public static ThrownScytheTrigger REPERANG = new ThrownScytheTrigger();
    public static ThrownKnifeTrigger THROWING_KNIVES = new ThrownKnifeTrigger();

    public static void register() {
        CriteriaTriggers.register(ROTTEN_TOMATOES);
        CriteriaTriggers.register(REPERANG);
        CriteriaTriggers.register(THROWING_KNIVES);
    }

}

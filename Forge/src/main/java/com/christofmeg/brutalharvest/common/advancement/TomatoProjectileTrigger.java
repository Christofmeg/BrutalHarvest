package com.christofmeg.brutalharvest.common.advancement;

import com.christofmeg.brutalharvest.CommonConstants;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public class TomatoProjectileTrigger extends SimpleCriterionTrigger<TomatoProjectileTrigger.TriggerInstance> {

    public static final ResourceLocation ID = new ResourceLocation(CommonConstants.MOD_ID, "throw_tomato_at_villager");

    public @NotNull ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, TriggerInstance::test);
    }

    @Override
    protected @NotNull TriggerInstance createInstance(@NotNull JsonObject json, @NotNull ContextAwarePredicate player, @NotNull DeserializationContext conditionsParser) {
        return new TriggerInstance(player);
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance
    {
        public TriggerInstance(ContextAwarePredicate player) {
            super(TomatoProjectileTrigger.ID, player);
        }

        public static TriggerInstance simple() {
            return new TriggerInstance(ContextAwarePredicate.ANY);
        }

        public boolean test() {
            return true;
        }
    }
}

package com.christofmeg.brutalharvest.common.advancement;

import com.christofmeg.brutalharvest.CommonConstants;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ThrownKnifeTrigger extends SimpleCriterionTrigger<ThrownKnifeTrigger.TriggerInstance> {

    public static final ResourceLocation ID = new ResourceLocation(CommonConstants.MOD_ID, "throwing_knives");

    public @NotNull ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer player, ItemStack pItem) {
        this.trigger(player, (triggerInstance) -> triggerInstance.matches(pItem));
    }

    @Override
    protected @NotNull TriggerInstance createInstance(@NotNull JsonObject json, @NotNull ContextAwarePredicate player, @NotNull DeserializationContext conditionsParser) {
        ItemPredicate $$3 = ItemPredicate.fromJson(json.get("item"));
        return new TriggerInstance(player, $$3);
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final ItemPredicate item;

        public TriggerInstance(ContextAwarePredicate player, ItemPredicate pItem) {
            super(ThrownKnifeTrigger.ID, player);
            this.item = pItem;
        }

        public static TriggerInstance item(ItemPredicate.Builder pItemPredicateBuilder) {
            return new TriggerInstance(ContextAwarePredicate.ANY, pItemPredicateBuilder.build());
        }

        public boolean matches(ItemStack pItem) {
            return this.item.matches(pItem);
        }

        public @NotNull JsonObject serializeToJson(@NotNull SerializationContext pConditions) {
            JsonObject $$1 = super.serializeToJson(pConditions);
            $$1.add("item", this.item.serializeToJson());
            return $$1;
        }
    }
}

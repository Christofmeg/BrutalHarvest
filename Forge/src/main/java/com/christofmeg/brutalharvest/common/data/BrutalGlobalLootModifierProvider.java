package com.christofmeg.brutalharvest.common.data;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.loot.RollLootTableModifier;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import org.apache.commons.lang3.Validate;

public class BrutalGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public BrutalGlobalLootModifierProvider(DataGenerator gen) {
        super(gen.getPackOutput(), CommonConstants.MOD_ID);
    }

    @Override
    protected void start() {
        add("village_loot", new RollLootTableModifier(new LootItemCondition[] {
                getList(new String[]{
                        "village_desert_house",
                        "village_plains_house",
                        "village_savanna_house",
                        "village_snowy_house",
                        "village_taiga_house"
                })
        }));
    }

    private LootItemCondition getList(String[] chests) {
        Validate.isTrue(chests.length > 0);
        LootItemCondition.Builder condition = null;
        for (String s : chests) {
            LootTableIdCondition.Builder b = LootTableIdCondition.builder(new ResourceLocation("chests/village/" + s));
            condition = condition == null ? b : condition.or(b);
        }
        return condition.build();
    }
}
package com.christofmeg.brutalharvest.common.item;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class ScytheItem extends SwordItem {

    public ScytheItem(Tier tier, int attackDamage, Properties properties) {
        super(tier, attackDamage, 2, properties);
    }

}
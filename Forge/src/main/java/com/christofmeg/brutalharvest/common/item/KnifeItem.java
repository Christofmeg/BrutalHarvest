package com.christofmeg.brutalharvest.common.item;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class KnifeItem extends SwordItem {

    public KnifeItem(Tier tier, int attackDamage, Properties properties) {
        super(tier, attackDamage, -2, properties);
    }

}

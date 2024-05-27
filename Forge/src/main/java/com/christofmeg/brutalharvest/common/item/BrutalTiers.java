package com.christofmeg.brutalharvest.common.item;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.init.TagRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class BrutalTiers {

    public static final Tier FLINT = TierSortingRegistry.registerTier(
            new ForgeTier(0, 26, 1.5f, 0.5f, 8, TagRegistry.Blocks.NEEDS_FLINT_TOOL, () -> Ingredient.of(Items.FLINT)),
            new ResourceLocation(CommonConstants.MOD_ID, "flint"), List.of(), List.of(Tiers.WOOD));

    public static final Tier COPPER = TierSortingRegistry.registerTier(
            new ForgeTier(1, 190, 5.0f, 2f, 10, TagRegistry.Blocks.NEEDS_COPPER_TOOL, () -> Ingredient.of(Items.COPPER_INGOT)),
            new ResourceLocation(CommonConstants.MOD_ID, "copper"), List.of(Tiers.STONE), List.of(Tiers.IRON));

}
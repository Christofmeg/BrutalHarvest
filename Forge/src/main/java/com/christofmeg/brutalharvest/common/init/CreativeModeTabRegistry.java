package com.christofmeg.brutalharvest.common.init;

import com.christofmeg.brutalharvest.CommonConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeModeTabRegistry {

    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CommonConstants.MOD_ID);

    @SuppressWarnings("unused")
    public static final RegistryObject<CreativeModeTab> TAB = REGISTRY.register(CommonConstants.MOD_ID, () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + CommonConstants.MOD_ID)).icon(
                    () -> new ItemStack(ItemRegistry.TOMATO.get()))
            .displayItems((parameters, tabData) ->
                    ItemRegistry.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(tabData::accept))
            .build());

}
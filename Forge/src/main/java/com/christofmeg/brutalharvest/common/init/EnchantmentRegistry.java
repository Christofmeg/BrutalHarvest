package com.christofmeg.brutalharvest.common.init;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.enchantment.BoomerangEnchantment;
import com.christofmeg.brutalharvest.common.enchantment.LaunchEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;

public class EnchantmentRegistry {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, CommonConstants.MOD_ID);

    public static void init(@Nonnull IEventBus modEventBus) {
        ENCHANTMENTS.register(modEventBus);
    }

    public static final RegistryObject<Enchantment> BOOMERANG;
    public static final RegistryObject<Enchantment> LAUNCH;

    static {
        BOOMERANG = ENCHANTMENTS.register("boomerang", BoomerangEnchantment::new);
        LAUNCH = ENCHANTMENTS.register("launch", LaunchEnchantment::new);
    }

}

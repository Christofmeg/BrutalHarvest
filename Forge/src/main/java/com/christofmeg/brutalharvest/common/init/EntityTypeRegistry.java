package com.christofmeg.brutalharvest.common.init;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.entity.ThrownKnifeEntity;
import com.christofmeg.brutalharvest.common.entity.ThrownScytheEntity;
import com.christofmeg.brutalharvest.common.entity.TomatoProjectileEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;

public class EntityTypeRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CommonConstants.MOD_ID);

    public static void init(@Nonnull IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);
    }

    public static final RegistryObject<EntityType<TomatoProjectileEntity>> TOMATO_PROJECTILE;
    public static final RegistryObject<EntityType<ThrownScytheEntity>> THROWN_SCYTHE;
    public static final RegistryObject<EntityType<ThrownKnifeEntity>> THROWN_KNIFE;

    static {
        TOMATO_PROJECTILE =
                ENTITY_TYPES.register("tomato_projectile", () -> EntityType.Builder.<TomatoProjectileEntity>of(TomatoProjectileEntity::new, MobCategory.MISC)
                        .clientTrackingRange(4).updateInterval(10).sized(0.25F, 0.25F).build("tomato_projectile"));

        THROWN_SCYTHE =
                ENTITY_TYPES.register("thrown_scythe", () -> EntityType.Builder.<ThrownScytheEntity>of(ThrownScytheEntity::new, MobCategory.MISC)
                        .clientTrackingRange(4).updateInterval(10).sized(0.25F, 0.25F).build("thrown_scythe"));

        THROWN_KNIFE =
                ENTITY_TYPES.register("thrown_knife", () -> EntityType.Builder.<ThrownKnifeEntity>of(ThrownKnifeEntity::new, MobCategory.MISC)
                        .clientTrackingRange(4).updateInterval(10).sized(0.25F, 0.25F).build("thrown_knife"));
    }

}
package com.christofmeg.brutalharvest.common.init;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.entity.TomatoProjectileEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;

public class EntityTypeRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES;

    public static final RegistryObject<EntityType<TomatoProjectileEntity>> TOMATO_PROJECTILE;
//    public static final EntityType<Snowball> SNOWBALL;

    private EntityTypeRegistry() {
    }

    public static void init(@Nonnull IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);
    }

    static {
        ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CommonConstants.MOD_ID);
/*
        TOMATO = ENTITY_TYPES.register("tomato", EntityType.Builder.of(() -> new TomatoEntity(EntityTypeRegistry.TOMATO.get(), ), MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("tomato"));
        SNOWBALL = register("snowball", EntityType.Builder.of(Snowball::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10));

 */

        TOMATO_PROJECTILE =
                ENTITY_TYPES.register("tomato_projectile", () -> EntityType.Builder.<TomatoProjectileEntity>of(TomatoProjectileEntity::new, MobCategory.MISC)
                        .clientTrackingRange(4).updateInterval(10).sized(0.25F, 0.25F).build("tomato_projectile"));

    }


}
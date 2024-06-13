package com.christofmeg.brutalharvest.common.entity;

import com.christofmeg.brutalharvest.common.init.AdvancementRegistry;
import com.christofmeg.brutalharvest.common.init.EntityTypeRegistry;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class TomatoProjectileEntity extends ThrowableItemProjectile {

    public TomatoProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public TomatoProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(EntityTypeRegistry.TOMATO_PROJECTILE.get(), livingEntity, pLevel);
    }

    protected @NotNull Item getDefaultItem() {
        return this.getItemRaw().getItem();
    }

    private ParticleOptions getParticle() {
        ItemStack stack = this.getItemRaw();
        if (stack.isEmpty()) {
            stack = Items.AIR.getDefaultInstance();
        }
        return new ItemParticleOption(ParticleTypes.ITEM, stack);
    }

    public void handleEntityEvent(byte p_37402_) {
        if (p_37402_ == 3) {
            ParticleOptions particleOptions = this.getParticle();

            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(particleOptions, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }

    }

    protected void onHitEntity(@NotNull EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        if (!entity.level().isClientSide) {
            entity.hurt(this.damageSources().thrown(this, this.getOwner()), 0);
            if (entity instanceof Villager) {
                if (this.getOwner() instanceof ServerPlayer serverPlayer) {
                    AdvancementRegistry.ROTTEN_TOMATOES.trigger(serverPlayer);
                }
            }
        }
    }

    protected void onHit(@NotNull HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }

    }
}

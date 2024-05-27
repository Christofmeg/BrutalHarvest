package com.christofmeg.brutalharvest.common.entity;

import com.christofmeg.brutalharvest.common.init.EntityTypeRegistry;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class TomatoProjectileEntity extends ThrowableItemProjectile {

    public TomatoProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public TomatoProjectileEntity(Level pLevel) {
        super(EntityTypeRegistry.TOMATO_PROJECTILE.get(), pLevel);
    }

    public TomatoProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(EntityTypeRegistry.TOMATO_PROJECTILE.get(), livingEntity, pLevel);
    }

    protected @NotNull Item getDefaultItem() {
        return ItemRegistry.TOMATO.get();
    }

    private ParticleOptions getParticle() {
        ItemStack $$0 = this.getItemRaw();
        return $$0.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, $$0);
    }

    public void handleEntityEvent(byte p_37402_) {
        if (p_37402_ == 3) {
            ParticleOptions $$1 = this.getParticle();

            for(int $$2 = 0; $$2 < 8; ++$$2) {
                this.level().addParticle($$1, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }

    }

    protected void onHitEntity(@NotNull EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 0);
        if (entity instanceof Villager villager) {
            villager.setAggressive(true);
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

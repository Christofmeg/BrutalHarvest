package com.christofmeg.brutalharvest.common.entity;

import com.christofmeg.brutalharvest.common.init.AdvancementRegistry;
import com.christofmeg.brutalharvest.common.init.EntityTypeRegistry;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import com.christofmeg.brutalharvest.common.init.SoundRegistry;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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

import java.util.Random;

public class TomatoProjectileEntity extends ThrowableItemProjectile {

    Level level;

    public TomatoProjectileEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
        this.level = level;
    }

    public TomatoProjectileEntity(Level level, LivingEntity livingEntity) {
        super(EntityTypeRegistry.TOMATO_PROJECTILE.get(), livingEntity, level);
        this.level = level;
    }

    @Override
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

    @Override
    public void handleEntityEvent(byte p_37402_) {
        if (p_37402_ == 3) {
            ParticleOptions particleOptions = this.getParticle();
            for(int i = 0; i < 8; ++i) {
                level.addParticle(particleOptions, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        this.playSound(SoundRegistry.TOMATO_SPLAT.get(), 0.5F, 0.8F);
        if (!level.isClientSide) {
            entity.hurt(this.damageSources().thrown(this, this.getOwner()), 0);
            if (this.getOwner() instanceof ServerPlayer serverPlayer) {
                if (entity instanceof Villager) {
                    AdvancementRegistry.ROTTEN_TOMATOES.trigger(serverPlayer);
                }
            }
            Random random = new Random();
            int randomInt = random.nextInt(10);
           if (randomInt == 0) {
                if (this.getItem().getItem() == ItemRegistry.ROTTEN_TOMATO.get()) {
                    if (entity instanceof LivingEntity livingEntity) {
                        MobEffectInstance poison = new MobEffectInstance(MobEffects.POISON, 80);
                        MobEffectInstance blindness = new MobEffectInstance(MobEffects.BLINDNESS, 80);
                        livingEntity.addEffect(poison);
                        livingEntity.addEffect(blindness);
                    }
                }
           }
        }
    }

    @Override
    protected void onHit(@NotNull HitResult hitResult) {
        super.onHit(hitResult);
        this.playSound(SoundRegistry.TOMATO_SPLAT.get(), 0.5F, 0.8F);
        if (!level.isClientSide) {
            level.broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

}

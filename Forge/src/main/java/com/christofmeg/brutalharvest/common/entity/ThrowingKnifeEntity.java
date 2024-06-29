package com.christofmeg.brutalharvest.common.entity;

import com.christofmeg.brutalharvest.common.init.AdvancementRegistry;
import com.christofmeg.brutalharvest.common.init.EntityTypeRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ThrowingKnifeEntity extends AbstractArrow {
    
    private ItemStack knifeItem;
    private boolean dealtDamage;
    private float attackDamage;

    public ThrowingKnifeEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public ThrowingKnifeEntity(Level pLevel, LivingEntity livingEntity, ItemStack stack, float attackDamage) {
        super(EntityTypeRegistry.THROWING_KNIFE.get(), livingEntity, pLevel);
        this.knifeItem = stack.copy();
        this.attackDamage = attackDamage;
    }

    @Override
    protected void onHit(@NotNull HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            if (this.getOwner() instanceof ServerPlayer serverPlayer) {
                AdvancementRegistry.THROWING_KNIVES.trigger(serverPlayer);
            }
        }
    }

    @Override
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        Entity $$0 = this.getOwner();
        if ((this.dealtDamage || this.isNoPhysics()) && $$0 != null) {
            if (!this.isAcceptibleReturnOwner()) {
                if (!this.level().isClientSide && this.pickup == Pickup.ALLOWED) {
                    this.spawnAtLocation(this.getPickupItem(), 0.1F);
                }

                this.discard();
            }
        }

        super.tick();
    }

    private boolean isAcceptibleReturnOwner() {
        Entity $$0 = this.getOwner();
        if ($$0 != null && $$0.isAlive()) {
            return !($$0 instanceof ServerPlayer) || !$$0.isSpectator();
        } else {
            return false;
        }
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return this.knifeItem.copy();
    }

    @Override
    @Nullable
    protected EntityHitResult findHitEntity(@NotNull Vec3 pStartVec, @NotNull Vec3 pEndVec) {
        return this.dealtDamage ? null : super.findHitEntity(pStartVec, pEndVec);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        if (!entity.level().isClientSide) {
            entity.hurt(this.damageSources().thrown(this, this.getOwner()), attackDamage);
            if (this.getOwner() instanceof ServerPlayer serverPlayer) {
                AdvancementRegistry.THROWING_KNIVES.trigger(serverPlayer);
            }
        }

        if (entity instanceof LivingEntity $$3) {
            ItemStack stack = this.knifeItem;
            if (stack != null) {
                attackDamage += EnchantmentHelper.getDamageBonus(this.knifeItem, $$3.getMobType());
            }
        }

        Entity $$4 = this.getOwner();
        DamageSource $$5 = this.damageSources().thrown(this, $$4 == null ? this : $$4);
        this.dealtDamage = true;
        SoundEvent $$6 = SoundEvents.TRIDENT_HIT;
        if (entity.hurt($$5, attackDamage)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (entity instanceof LivingEntity livingEntity) {
                if ($$4 instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(livingEntity, $$4);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity)$$4, livingEntity);
                }

                this.doPostHurtEffects(livingEntity);
            }
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01, -0.1, -0.01));
        float $$8 = 1.0F;
        this.playSound($$6, $$8, 1.0F);
    }

    @Override
    protected boolean tryPickup(@NotNull Player pPlayer) {
        return super.tryPickup(pPlayer) || this.isNoPhysics() && this.ownedBy(pPlayer) && pPlayer.getInventory().add(this.getPickupItem());
    }

    @Override
    protected @NotNull SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND; //TODO custom hit sound
    }

    @Override
    public void playerTouch(@NotNull Player pEntity) {
        if (this.ownedBy(pEntity) || this.getOwner() == null) {
            super.playerTouch(pEntity);
        }
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("Knife", 10)) {
            this.knifeItem = ItemStack.of(pCompound.getCompound("Knife"));
        }
        this.dealtDamage = pCompound.getBoolean("DealtDamage");
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.put("Knife", this.knifeItem.save(new CompoundTag()));
        pCompound.putBoolean("DealtDamage", this.dealtDamage);
    }

    @Override
    public void tickDespawn() {
        if (this.pickup != Pickup.ALLOWED) {
            super.tickDespawn();
        }
    }

    @Override
    public boolean shouldRender(double pX, double pY, double pZ) {
        return true;
    }

}

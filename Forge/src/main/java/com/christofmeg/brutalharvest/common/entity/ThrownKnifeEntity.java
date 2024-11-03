package com.christofmeg.brutalharvest.common.entity;

import com.christofmeg.brutalharvest.common.init.EnchantmentRegistry;
import com.christofmeg.brutalharvest.common.init.EntityTypeRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ThrownKnifeEntity extends AbstractArrow {

    private static final EntityDataAccessor<ItemStack> DATA_ITEM = SynchedEntityData.defineId(ThrownKnifeEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<Byte> ID_LAUNCH = SynchedEntityData.defineId(ThrownKnifeEntity.class, EntityDataSerializers.BYTE);

    private boolean dealtDamage;

    public ThrownKnifeEntity(EntityType<? extends ThrownKnifeEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ThrownKnifeEntity(Level level, LivingEntity livingEntity, ItemStack stack) {
        super(EntityTypeRegistry.THROWN_KNIFE.get(), livingEntity, level);
        this.entityData.set(ID_LAUNCH, (byte) stack.getEnchantmentLevel(EnchantmentRegistry.LAUNCH.get()));
        this.setItem(stack);
    }

    @Override
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        Entity entity = this.getOwner();
        int $$1 = this.entityData.get(ID_LAUNCH);
        if ($$1 > 0 && (this.dealtDamage) && entity != null) {
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
        return this.getItem();
    }

    @Override
    @Nullable
    protected EntityHitResult findHitEntity(@NotNull Vec3 pStartVec, @NotNull Vec3 pEndVec) {
        return this.dealtDamage ? null : super.findHitEntity(pStartVec, pEndVec);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        Entity resultEntity = pResult.getEntity();
        float damage = 8.0F;
        if (resultEntity instanceof LivingEntity living) {
            ItemStack stack = this.getItem();
            if (stack != null && !stack.isEmpty()) {
                damage += EnchantmentHelper.getDamageBonus(stack, living.getMobType());
            }
        }
        Entity entity = this.getOwner();
        DamageSource damageSource = this.damageSources().trident(this, entity == null ? this : entity);
        this.dealtDamage = true;
        SoundEvent soundEvent = SoundEvents.TRIDENT_HIT;
        if (resultEntity.hurt(damageSource, damage)) {
            if (resultEntity.getType() == EntityType.ENDERMAN) {
                return;
            }
            if (resultEntity instanceof LivingEntity living) {
                if (entity instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(living, entity);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity)entity, living);
                }
                this.doPostHurtEffects(living);
            }
        }
        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01, -0.1, -0.01));
        float volume = 1.0F;
        this.playSound(soundEvent, volume, 1.0F);
    }

    @Override
    public void tickDespawn() {
        if (this.pickup != Pickup.ALLOWED) {
            super.tickDespawn();
        }
    }

    @Override
    protected boolean tryPickup(@NotNull Player pPlayer) {
        return super.tryPickup(pPlayer) || this.ownedBy(pPlayer) && pPlayer.getInventory().add(this.getPickupItem());
    }

    @Override
    protected @NotNull SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND;
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
        if (pCompound.contains("item", Tag.TAG_COMPOUND)) {
            setItem(ItemStack.of(pCompound.getCompound("item")));
        }
        this.dealtDamage = pCompound.getBoolean("DealtDamage");
        this.entityData.set(ID_LAUNCH, (byte)EnchantmentHelper.getLoyalty(this.getItem()));
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        if (!getItem().isEmpty()) {
            pCompound.put("item", getItem().save(new CompoundTag()));
        }
        pCompound.putBoolean("DealtDamage", this.dealtDamage);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_LAUNCH, (byte)0);
        this.entityData.define(DATA_ITEM, ItemStack.EMPTY);
    }

    public ItemStack getItem() {
        return entityData.get(DATA_ITEM);
    }

    public void setItem(ItemStack item) {
        entityData.set(DATA_ITEM, item.copy());
    }

}

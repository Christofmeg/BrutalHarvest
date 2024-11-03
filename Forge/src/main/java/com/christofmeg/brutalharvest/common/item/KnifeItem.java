package com.christofmeg.brutalharvest.common.item;

import com.christofmeg.brutalharvest.common.entity.ThrownKnifeEntity;
import com.christofmeg.brutalharvest.common.init.AdvancementRegistry;
import com.christofmeg.brutalharvest.common.init.EnchantmentRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class KnifeItem extends SwordItem {
    protected Tier tier;

    public KnifeItem(Tier tier, Properties properties) {
        super(tier, 1, -2, properties);
        this.tier = tier;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand interactionHand) {
        ItemStack $$3 = player.getItemInHand(interactionHand);
        player.startUsingItem(interactionHand);
        return InteractionResultHolder.consume($$3);
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return 72000;
    }

    @Override
    public void releaseUsing(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity, int pTimeLeft) {
        if (livingEntity instanceof ServerPlayer serverPlayer) {
            if (serverPlayer.isShiftKeyDown() && stack.isEnchanted()) {
                if (stack.getEnchantmentLevel(EnchantmentRegistry.LAUNCH.get()) >= 1) {
                    int $$5 = this.getUseDuration(stack) - pTimeLeft;
                    if ($$5 >= 0) {
                        if (!level.isClientSide) {
                            stack.hurtAndBreak(1, serverPlayer, (p_43388_) -> p_43388_.broadcastBreakEvent(livingEntity.getUsedItemHand()));
                            ThrownKnifeEntity thrownKnife = new ThrownKnifeEntity(level, serverPlayer, stack);
                            thrownKnife.shootFromRotation(serverPlayer, serverPlayer.getXRot(), serverPlayer.getYRot(), 0.0F, 2.5F, 1.0F);
                            if (serverPlayer.getAbilities().instabuild) {
                                thrownKnife.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                            }
                            level.addFreshEntity(thrownKnife);
                            level.playSound(null, thrownKnife, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);

                            AdvancementRegistry.THROWING_KNIVES.trigger(serverPlayer, stack);

                            if (!serverPlayer.getAbilities().instabuild) {
                                serverPlayer.getInventory().removeItem(stack);
                            }
                        }
                        serverPlayer.awardStat(Stats.ITEM_USED.get(this));
                    }
                }
            }
        }
    }

}

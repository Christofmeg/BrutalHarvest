package com.christofmeg.brutalharvest.common.item;

import com.christofmeg.brutalharvest.common.entity.TomatoProjectileEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class TomatoProjectileItem extends Item {

    public TomatoProjectileItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);
        if (player.isShiftKeyDown()) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!level.isClientSide) {
                TomatoProjectileEntity tomato = new TomatoProjectileEntity(level, player);
                tomato.setItem(stack);
                tomato.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                level.addFreshEntity(tomato);
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
        } else {
            if (stack.isEdible()) {
                FoodProperties foodProperties = stack.getFoodProperties(player);
                if (foodProperties != null) {
                    if (player.canEat(foodProperties.canAlwaysEat())) {
                        player.startUsingItem(interactionHand);
                        return InteractionResultHolder.consume(stack);
                    } else {
                        return InteractionResultHolder.fail(stack);
                    }
                }
            }
        }
        return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
    }

}

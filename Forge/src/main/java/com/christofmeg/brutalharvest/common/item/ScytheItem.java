package com.christofmeg.brutalharvest.common.item;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.entity.ThrownScytheEntity;
import com.christofmeg.brutalharvest.common.init.AdvancementRegistry;
import com.christofmeg.brutalharvest.common.init.TagRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = CommonConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ScytheItem extends DiggerItem {
    protected Tier tier;
    public int harvestRadius;

    public ScytheItem(Tier tier, int harvestRadius, Item.Properties properties) {
        super(1, -2, tier, TagRegistry.Blocks.MINEABLE_WITH_SCYTHE, properties);
        this.tier = tier;
        this.harvestRadius = harvestRadius;
    }

    @SubscribeEvent
    public static void blockBreakEvent(BlockEvent.BreakEvent event) {
        LevelAccessor level = event.getLevel();
        if (level instanceof ServerLevel serverLevel) {

            Player player = event.getPlayer();
            ItemStack stack = player.getItemInHand(player.getUsedItemHand());
            if (stack.getItem() instanceof ScytheItem scytheItem) {

                BlockState state = event.getState();
                boolean isCropBlock = state.getBlock() instanceof CropBlock || state.is(BlockTags.CROPS);
                boolean isGrassBlock = state.is(BlockTags.FLOWERS) || state.is(BlockTags.REPLACEABLE_BY_TREES);
                if (isCropBlock || isGrassBlock) {

                    BlockPos pos = event.getPos();
                    scytheItem.harvest(stack, player, serverLevel, pos, scytheItem.harvestRadius, event);
                    stack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                    event.setCanceled(true);
                }
            }
        }
    }

    public void harvest(ItemStack stack, LivingEntity livingEntity, Level level, BlockPos pos, int radius, BlockEvent.BreakEvent event) {
        int start = -radius;
        int end = hasCenteredRadius() ? radius + 1 : radius;

        for (int dx = start; dx < end; dx++) {
            for (int dy = start; dy < end; dy++) {
                for (int dz = start; dz < end; dz++) {
                    if (dx == 0 && dy == 0 && dz == 0) continue;

                    BlockPos targetPos = pos.offset(dx, dy, dz);
                    harvestAtPos(level, targetPos, livingEntity, stack, pos, event);
                }
            }
        }
        harvestAtPos(level, pos, livingEntity, stack, pos, event);
    }

    private boolean hasCenteredRadius() {
        return tier == BrutalTiers.COPPER || tier == Tiers.GOLD || tier == Tiers.NETHERITE;
    }

    private void harvestAtPos(Level level, BlockPos targetPos, LivingEntity livingEntity, ItemStack stack, BlockPos pos, BlockEvent.BreakEvent event) {
        BlockState targetState = level.getBlockState(targetPos);
        boolean shouldHarvest = targetState.getBlock() instanceof CropBlock cropBlock && cropBlock.isMaxAge(targetState) ||
                targetState.is(BlockTags.FLOWERS) || targetState.is(BlockTags.REPLACEABLE_BY_TREES);

        if (shouldHarvest) {
            Player player = (Player) livingEntity;
            player.awardStat(Stats.BLOCK_MINED.get(targetState.getBlock()));
            player.causeFoodExhaustion(0.005F);
            if (level instanceof ServerLevel serverLevel) {
                BlockEntity blockEntity = level.getBlockEntity(targetPos);
                Block.getDrops(targetState, serverLevel, targetPos, blockEntity, livingEntity, stack).forEach(itemStack -> Block.popResource(level, pos, itemStack));
                targetState.spawnAfterBreak(serverLevel, targetPos, stack, false);
                targetState.getBlock().popExperience(serverLevel, player.blockPosition(), event.getExpToDrop());

                if (targetState.getBlock() instanceof CropBlock && ((CropBlock) targetState.getBlock()).isMaxAge(targetState)) {
                    level.setBlock(targetPos, targetState.getBlock().defaultBlockState(), 2);
                } else {
                    level.removeBlock(targetPos, false);
                }
            }
        }
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
            if (serverPlayer.isShiftKeyDown()) {
                int $$5 = this.getUseDuration(stack) - pTimeLeft;
                if ($$5 >= 0) {
                    if (!level.isClientSide) {
                        stack.hurtAndBreak(1, serverPlayer, (p_43388_) -> p_43388_.broadcastBreakEvent(livingEntity.getUsedItemHand()));
                        ThrownScytheEntity thrownScythe = new ThrownScytheEntity(level, serverPlayer, stack);
                        thrownScythe.shootFromRotation(serverPlayer, serverPlayer.getXRot(), serverPlayer.getYRot(), 0.0F, 2.5F, 1.0F);
                        if (serverPlayer.getAbilities().instabuild) {
                            thrownScythe.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                        }
                        level.addFreshEntity(thrownScythe);
                        level.playSound(null, thrownScythe, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);

                        AdvancementRegistry.REPERANG.trigger(serverPlayer, stack);

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
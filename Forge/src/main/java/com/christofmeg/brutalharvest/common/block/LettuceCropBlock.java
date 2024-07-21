package com.christofmeg.brutalharvest.common.block;

import com.christofmeg.brutalharvest.common.block.base.BaseCropBlock;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class LettuceCropBlock extends BaseCropBlock {

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 5);
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(0.0, 0.0, 0.0, 16.0, 3.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 9.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0)
    };

    public LettuceCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 4;
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return ItemRegistry.LETTUCE_SEEDS.get();
    }

    @Override
    protected ItemStack getBaseItemStack() {
        return ItemRegistry.SLICED_LETTUCE.get().getDefaultInstance();
    }

    @Override
    protected int getAgeAfterKnife() {
        return 2;
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState blockState, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE_BY_AGE[this.getAge(blockState)];
    }

}
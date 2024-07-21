package com.christofmeg.brutalharvest.common.block;

import com.christofmeg.brutalharvest.common.block.base.BaseDoubleCropBlock;
import com.christofmeg.brutalharvest.common.init.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class CucumberCropBlock extends BaseDoubleCropBlock {

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 9);
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 9.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            Shapes.block(),
            Shapes.block(),
            Shapes.block(),
            Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0)
    };

    public CucumberCropBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        return SHAPE_BY_AGE[this.getAge(pState)];
    }

    @Override
    public int getMaxAge() {
        return 5; //TODO JADE/TOP/WTHIT override
    }

    @Override
    protected int getMaxAgeDifference() {
        return 3;
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return ItemRegistry.CUCUMBER_SEEDS.get();
    }

    @Override
    protected ItemStack getBaseItemStack() {
        return ItemRegistry.CUCUMBER.get().getDefaultInstance();
    }

    @Override
    protected int getAgeAfterKnife() {
        return 3;
    }

    @Override
    public @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

}
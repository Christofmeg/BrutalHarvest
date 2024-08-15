package com.christofmeg.brutalharvest.common.block;

import com.christofmeg.brutalharvest.common.init.BlockRegistry;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class RubberLogBlock extends Block {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    public static BooleanProperty OPEN = BooleanProperty.create("open");
    public static BooleanProperty DRAINED = BooleanProperty.create("drained");

    public RubberLogBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(AXIS, Direction.Axis.Y).setValue(OPEN, false).setValue(DRAINED, false));
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull BlockState rotate(@NotNull BlockState pState, @NotNull Rotation pRot) {
        return rotatePillar(pState, pRot);
    }

    public static BlockState rotatePillar(BlockState pState, Rotation pRotation) {
        switch (pRotation) {
            case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> {
                Direction.Axis axis = pState.getValue(AXIS);
                switch (axis) {
                    case X -> {
                        return pState.setValue(AXIS, Direction.Axis.Z);
                    }
                    case Z -> {
                        return pState.setValue(AXIS, Direction.Axis.X);
                    }
                    default -> {
                        return pState;
                    }
                }
            }
            default -> {
                return pState;
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AXIS).add(OPEN).add(DRAINED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(AXIS, pContext.getClickedFace().getAxis());
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof AxeItem) {
            if (state.is(BlockRegistry.RUBBER_LOG_GENERATED.get())) {
                return BlockRegistry.STRIPPED_RUBBER_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }

}

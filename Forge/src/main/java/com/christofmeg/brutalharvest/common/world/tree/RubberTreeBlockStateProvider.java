package com.christofmeg.brutalharvest.common.world.tree;

import com.christofmeg.brutalharvest.common.block.RubberLogGeneratedBlock;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;

public class RubberTreeBlockStateProvider extends SimpleStateProvider {

    public RubberTreeBlockStateProvider(BlockState state) {
        super(RandomSource.create().nextInt(2) == 0 ?
                state.setValue(RubberLogGeneratedBlock.OPEN, true).setValue(RubberLogGeneratedBlock.FACING, Direction.getRandom(RandomSource.create())) :
                state.setValue(RubberLogGeneratedBlock.CUT, true)
        );
    }

}
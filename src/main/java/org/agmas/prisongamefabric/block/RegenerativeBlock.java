package org.agmas.prisongamefabric.block;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.agmas.prisongamefabric.PrisonGameBlocks;

public class RegenerativeBlock extends Block implements PolymerBlock {

    Block regenerated = Blocks.COARSE_DIRT;
    public static final BooleanProperty REGENRATING = BooleanProperty.of("regenerating");
    public int timeToRegen = 20;

    public RegenerativeBlock(Block regenerated, int timeToRegenerate) {
        super(AbstractBlock.Settings.create().strength(0.5f));
        this.regenerated = regenerated;
        this.timeToRegen = timeToRegenerate;
        this.setDefaultState(getDefaultState().with(REGENRATING, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(new Property[]{REGENRATING}));
    }


    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, this.getDefaultState());
        super.scheduledTick(state, world, pos, random);
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        if (!state.get(REGENRATING)) {
            return regenerated.getDefaultState();
        } else {
            return Blocks.BEDROCK.getDefaultState();

        }
    }
}

package org.agmas.prisongamefabric.block;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class FoodCourtChest extends ChestBlock implements PolymerBlock {


    public FoodCourtChest() {
        super(Blocks.TRAPPED_CHEST.getSettings(), () -> {
            return BlockEntityType.TRAPPED_CHEST;
        });
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        player.giveItemStack(Items.COD.getDefaultStack());
        return super.onUse(state, world, pos, player, hit);
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        return Blocks.TRAPPED_CHEST.getStateWithProperties(state);
    }
}

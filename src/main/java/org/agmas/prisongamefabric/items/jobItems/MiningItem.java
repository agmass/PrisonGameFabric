package org.agmas.prisongamefabric.items.jobItems;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface MiningItem {
    default void onMine(PlayerEntity p, BlockState b, World w, BlockPos pos) {

    }
}

package org.agmas.prisongamefabric.block;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.agmas.prisongamefabric.items.Keycard;
import org.agmas.prisongamefabric.util.Profile;
import org.jetbrains.annotations.Nullable;

public class BlackMarketCauldron extends Block implements PolymerBlock {

    public BlackMarketCauldron() {
        super(AbstractBlock.Settings.create());
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {

        return ActionResult.PASS;
    }
    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        return Blocks.CAULDRON.getDefaultState();
    }

}

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
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.items.Keycard;
import org.agmas.prisongamefabric.prisons.PrisonLocation;
import org.agmas.prisongamefabric.util.Profile;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class BlackMarketCauldron extends Block implements PolymerBlock {

    public BlackMarketCauldron() {
        super(AbstractBlock.Settings.create());
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        Profile profile = Profile.getProfile(player);
        profile.blackMarketEnterancePoint = new PrisonLocation((float)player.getX(),(float)player.getY(),(float) player.getZ(), Optional.of(-player.getYaw()), Optional.of(-player.getPitch()));
        player.requestTeleport(PrisonGameFabric.active.blackMarketTeleport.x,PrisonGameFabric.active.blackMarketTeleport.y,PrisonGameFabric.active.blackMarketTeleport.z);
        player.playSound(SoundEvents.ENTITY_PLAYER_SWIM);
        return ActionResult.PASS;
    }
    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        return Blocks.CAULDRON.getDefaultState();
    }

}

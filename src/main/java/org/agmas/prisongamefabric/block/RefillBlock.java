package org.agmas.prisongamefabric.block;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.minecraft.block.BarrelBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.agmas.prisongamefabric.util.Profile;
import org.agmas.prisongamefabric.util.Roles.Role.PositionInPower;
import org.agmas.prisongamefabric.util.Roles.Role;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.UUID;

public class RefillBlock extends Block implements PolymerBlock {

    public static final BooleanProperty OPEN = Properties.OPEN;
    public static final DirectionProperty FACING = Properties.FACING;

    public RefillBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        return Blocks.BARREL.getDefaultState().with(OPEN, true).with(FACING, state.get(FACING));
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        Role role = Profile.getRole(player);
        Profile profile = Profile.getProfile(player);
        if (profile.barrelCooldown <= 0) {
            profile.barrelCooldown = 20*20;
            if (Profile.getRole(player).power != Role.PositionInPower.GUARD && Profile.getRole(player).power != Role.PositionInPower.WARDEN) {
                role = Role.GUARD;
            }
            profile.setRole(role, Profile.RoleChangeModifier.KITONLY);
        } else {
            player.sendMessage(Text.translatable("block.prisongamefabric.refillblock.cooldown", Math.round((float) profile.barrelCooldown /20)));
        }
        return super.onUse(state, world, pos, player, hit);
    }

    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, OPEN});
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite());
    }
}

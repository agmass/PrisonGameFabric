package org.agmas.prisongamefabric.block;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.agmas.prisongamefabric.items.Keycard;
import org.agmas.prisongamefabric.util.Profile;
import org.agmas.prisongamefabric.util.Schedule;
import org.jetbrains.annotations.Nullable;

public class LeveledDoor extends DoorBlock implements PolymerBlock {
    public Integer level =0;
    public Block topBlock;
    public LeveledDoor(Integer level, Block topBlock) {
        super(BlockSetType.IRON, Settings.create().mapColor(Blocks.ACACIA_PLANKS.getDefaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.0F).nonOpaque().burnable().pistonBehavior(PistonBehavior.DESTROY));
        this.level = level;
        this.topBlock = topBlock;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (level == 0 && !Schedule.getCurrentPeriod().specialProperties.contains("lockdown")) {
            state = (BlockState) state.cycle(OPEN);
            world.setBlockState(pos, state, 10);
            playOpenCloseSound(player, world, pos, (Boolean) state.get(OPEN));
            world.emitGameEvent(player, this.isOpen(state) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
            Profile.useFeedback(player, Profile.PlayerFeedbackEnum.ACCEPTED);
            return ActionResult.success(true);
        } else if (level != 0){

            Profile.useFeedback(player, Profile.PlayerFeedbackEnum.DENIED, Text.translatable("reason.prisongamefabric.lockdown", new Object[]{}));
        } else {

            Profile.useFeedback(player, Profile.PlayerFeedbackEnum.DENIED, Text.translatable("reason.prisongamefabric.insufficientLevel", new Object[]{"1"}));
        }
        return ActionResult.PASS;
    }

    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleBlockTick(pos, this, 1);
        super.onBlockAdded(state, world, pos, oldState, notify);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.scheduleBlockTick(pos, this, 1);
        if (Schedule.getCurrentPeriod().specialProperties.contains("lockdown") && level == 0) {
            world.setBlockState(pos, state.with(OPEN, false));
        }
        super.scheduledTick(state, world, pos, random);
    }

    private void playOpenCloseSound(@Nullable Entity entity, World world, BlockPos pos, boolean open) {
        world.playSound(entity, pos, open ? getBlockSetType().doorOpen() : getBlockSetType().doorClose(), SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.1F + 0.9F);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (stack.getItem() instanceof Keycard c) {
            if (c.level >= level) {
                state = (BlockState) state.cycle(OPEN);
                world.setBlockState(pos, state, 10);
                playOpenCloseSound(player, world, pos, (Boolean) state.get(OPEN));
                world.emitGameEvent(player, this.isOpen(state) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
                Profile.useFeedback(player, Profile.PlayerFeedbackEnum.ACCEPTED);
                return ItemActionResult.success(true);
            }
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        return state.getEntries().get(HALF).equals(DoubleBlockHalf.UPPER) ? topBlock.getStateWithProperties(state) : Blocks.IRON_DOOR.getStateWithProperties(state);
    }

}

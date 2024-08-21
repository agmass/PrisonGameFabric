package org.agmas.prisongamefabric.mixin.players;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.block.RegenerativeBlock;
import org.agmas.prisongamefabric.items.jobItems.MiningItem;
import org.agmas.prisongamefabric.prisons.shopSigns.ShopSign;
import org.agmas.prisongamefabric.util.Profile;
import org.agmas.prisongamefabric.util.StateSaverAndLoader;
import org.agmas.prisongamefabric.util.jobs.MiningJob;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerInteractionManager.class)
public abstract class ServerPlayerInteractionManagerMixin {

    @Shadow private GameMode gameMode;

    @Shadow @Final protected ServerPlayerEntity player;

    @Shadow protected ServerWorld world;

    @Inject(method = "tryBreakBlock", at = @At("HEAD"), cancellable = true)
    private void injected(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (!player.getAbilities().allowModifyWorld) {
            BlockState state = world.getBlockState(pos);

            if (state.getBlock() instanceof RegenerativeBlock regenerativeBlock) {
                if (!state.get(RegenerativeBlock.REGENRATING)) {
                    state = state.with(RegenerativeBlock.REGENRATING,true);
                    world.setBlockState(pos, state);
                    world.scheduleBlockTick(pos,state.getBlock(), regenerativeBlock.timeToRegen);
                    ItemStack itemStack = player.getMainHandStack();
                    if (itemStack.getItem() instanceof MiningItem mi) {
                        mi.onMine(player, world.getBlockState(pos), world, pos);
                    }
                }
            } else {
                ItemStack itemStack = player.getMainHandStack();
                if (itemStack.getItem() instanceof MiningItem mi) {
                    mi.onMine(player, world.getBlockState(pos), world, pos);
                }
            }
            cir.setReturnValue(false);
            cir.cancel();

        }
    }

    @Inject(method = "interactBlock", at = @At("HEAD"))
    private void injected(ServerPlayerEntity player, World world, ItemStack stack, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {

        BlockPos blockPos = hitResult.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
         if (gameMode != GameMode.SPECTATOR) {
             if (blockState.getBlock().equals(Blocks.SPRUCE_WALL_SIGN) || blockState.getBlock().equals(Blocks.BIRCH_WALL_SIGN)) {
                 BlockEntity be = world.getBlockEntity(blockPos);
                 if (be instanceof SignBlockEntity signBlockEntity) {
                     for (MiningJob j : MiningJob.values()) {
                         if (signBlockEntity.getFrontText().getMessage(2, false).equals(Text.of(j.name))) {
                             if (!player.getInventory().contains(j.jobItem.getDefaultStack())) {
                                 ItemStack lumberAxe = j.jobItem.getDefaultStack();
                                 player.sendMessage(j.hint);
                                 player.getInventory().insertStack(lumberAxe);
                                 break;
                             }
                         }
                     }
                     for (ShopSign ss : PrisonGameFabric.availableSigns.values()) {
                         if (signBlockEntity.getFrontText().getMessage(2, false).getLiteralString().equals(ss.name)) {
                             Profile profile = Profile.getProfile(player);
                             if (profile.getMoney() >= ss.price) {
                                 profile.setMoney(profile.getMoney()-ss.price);
                                 ss.buy(player);
                             } else {
                                 ss.attemptButFail(player);
                             }
                         }
                     }
                 }
             }
         }
    }

}

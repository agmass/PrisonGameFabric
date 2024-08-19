package org.agmas.prisongamefabric.mapgame.mixin;

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
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.agmas.prisongamefabric.block.RegenerativeBlock;
import org.agmas.prisongamefabric.items.jobItems.MiningItem;
import org.agmas.prisongamefabric.util.Profile;
import org.agmas.prisongamefabric.util.jobs.MiningJob;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerInteractionManager.class)
public abstract class InputMixin {

    @Shadow private GameMode gameMode;

    @Shadow @Final protected ServerPlayerEntity player;

    @Shadow protected ServerWorld world;

    @Inject(method = "tryBreakBlock", at = @At("HEAD"), cancellable = true)
    private void injected(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (Profile.getProfile(player).isInScene) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }

    @Inject(method = "interactBlock", at = @At("HEAD"), cancellable = true)
    private void injected(ServerPlayerEntity player, World world, ItemStack stack, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        if (Profile.getProfile(player).isInScene) {
            cir.setReturnValue(ActionResult.FAIL);
            cir.cancel();
        }
    }
    @Inject(method = "interactItem", at = @At("HEAD"), cancellable = true)
    private void injected(ServerPlayerEntity player, World world, ItemStack stack, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (Profile.getProfile(player).isInScene) {
            cir.setReturnValue(ActionResult.FAIL);
            cir.cancel();
        }
    }

}

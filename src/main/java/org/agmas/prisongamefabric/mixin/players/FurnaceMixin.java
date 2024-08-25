package org.agmas.prisongamefabric.mixin.players;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.agmas.prisongamefabric.util.Profile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlock.class)
public class FurnaceMixin {

    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    private void injected2(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {

        if (player.getMainHandStack().getItem().equals(Items.COD)) {
            player.getMainHandStack().setCount(player.getMainHandStack().getCount()-1);
            Profile.getProfile(player).addMoney(1.25, true);
            world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_BLASTFURNACE_FIRE_CRACKLE, SoundCategory.MASTER, 1f, 1f);
        }
        if (!player.getAbilities().allowModifyWorld) {
            cir.setReturnValue(ActionResult.FAIL);
            cir.cancel();
        }
    }

}

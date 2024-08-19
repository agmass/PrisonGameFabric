package org.agmas.prisongamefabric.mixin.players;

import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.GameMode;
import org.agmas.prisongamefabric.items.jobItems.MiningItem;
import org.agmas.prisongamefabric.util.StateSaverAndLoader;
import org.agmas.prisongamefabric.util.Tx;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public abstract class PlayerDrop {

    @Shadow @Final public ServerPlayerInteractionManager interactionManager;

    @Shadow public abstract boolean changeGameMode(GameMode gameMode);

    @Shadow public abstract void sendMessage(Text message, boolean overlay);



    @Inject(method = "dropItem", at = @At("HEAD"), cancellable = true)
    private void injected(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir) {
        if (stack.getItem() instanceof MiningItem mi) {
            cir.setReturnValue(null);
            cir.cancel();
        }
    }

}

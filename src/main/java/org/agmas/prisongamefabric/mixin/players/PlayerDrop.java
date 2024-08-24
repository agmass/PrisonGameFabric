package org.agmas.prisongamefabric.mixin.players;

import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.GameMode;
import org.agmas.prisongamefabric.items.jobItems.MiningItem;
import org.agmas.prisongamefabric.util.Profile;
import org.agmas.prisongamefabric.util.Roles.Role;
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


    @Shadow @Final public MinecraftServer server;

    @Inject(method = "changeGameMode", at = @At("HEAD"), cancellable = true)
    private void injected(GameMode gameMode, CallbackInfoReturnable<Boolean> cir) {
        if (gameMode.isCreative()) {
            if (server.getServerMotd().contains("prisonbutfab.minehut.com")) {
                sendMessage(Tx.tf(Formatting.RED, "WARNING!!!!!!!!!!!!\nYou are currently building on the Minehut Release server!!!!!!\nThis server is NOT what pbf uses to update, and will most likely be reset next update.\nIf you want to make permanent changes, please build on the DEV server; wherever that may be."), false);
            }
        }
    }

    @Inject(method = "dropItem", at = @At("HEAD"), cancellable = true)
    private void injected(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir) {
        if (stack.getItem() instanceof MiningItem mi || Profile.getRole((ServerPlayerEntity)(Object)this).power.equals(Role.PositionInPower.WARDEN)) {
            cir.setReturnValue(null);
            cir.cancel();
        }
    }

}

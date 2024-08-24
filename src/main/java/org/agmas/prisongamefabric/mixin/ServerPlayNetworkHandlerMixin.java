package org.agmas.prisongamefabric.mixin;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.network.message.FilterMask;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSignC2SPacket;
import net.minecraft.server.filter.FilteredMessage;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Formatting;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.util.Profile;
import org.agmas.prisongamefabric.util.Roles.Role;
import org.agmas.prisongamefabric.util.Tx;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {


    @Shadow public abstract ServerPlayerEntity getPlayer();

    @Shadow public ServerPlayerEntity player;

    @Inject(method = "handleDecoratedMessage", at = @At("HEAD"), cancellable = true)
    private void cursor(SignedMessage message, CallbackInfo ci) {
        Role role = Profile.getRole(player);
        PrisonGameFabric.serverInstance.getPlayerManager().getPlayerList().forEach((plr) -> {
            plr.sendMessage(role.prefix.copy().append(" ").append(Tx.ttf(role.secondaryColor, player.getName().copy()).append(Tx.tf(role.backgroundColor, ": ").append(Tx.tf(role.backgroundColor, message.getContent().getLiteralString())))));
        });
        ci.cancel();
    }

    @Inject(method = "onPlayerMove", at = @At("HEAD"), cancellable = true)
    private void cursor(PlayerMoveC2SPacket packet, CallbackInfo ci) {
        Scene scene = Profile.getScene(getPlayer());
        scene.playerMouseY -= (PrisonGameFabric.active.computerTeleport.pitch.orElse(0.0f)-packet.getPitch(getPlayer().getPitch()))*4;
        scene.playerMouseX -= (PrisonGameFabric.active.computerTeleport.yaw.orElse(0.0f)-packet.getYaw(getPlayer().getYaw()))*4;
    }

}

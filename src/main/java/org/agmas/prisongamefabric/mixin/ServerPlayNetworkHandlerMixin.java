package org.agmas.prisongamefabric.mixin;

import net.minecraft.network.message.SignedMessage;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.util.Profile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {


    @Shadow public abstract ServerPlayerEntity getPlayer();

    @Shadow public ServerPlayerEntity player;

    @Inject(method = "onPlayerMove", at = @At("HEAD"), cancellable = true)
    private void cursor(PlayerMoveC2SPacket packet, CallbackInfo ci) {
        Scene scene = Profile.getScene(getPlayer());
        scene.playerMouseY -= (PrisonGameFabric.active.computerTeleport.pitch.orElse(0.0f)-packet.getPitch(getPlayer().getPitch()))*4;
        scene.playerMouseX -= (PrisonGameFabric.active.computerTeleport.yaw.orElse(0.0f)-packet.getYaw(getPlayer().getYaw()))*4;
    }

}

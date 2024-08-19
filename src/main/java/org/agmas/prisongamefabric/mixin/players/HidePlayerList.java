package org.agmas.prisongamefabric.mixin.players;

import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerListS2CPacket.Entry.class)
public class HidePlayerList {

    @Mutable
    @Shadow @Final private boolean listed;

    @Inject(method = "<init>(Ljava/util/UUID;Lcom/mojang/authlib/GameProfile;ZILnet/minecraft/world/GameMode;Lnet/minecraft/text/Text;Lnet/minecraft/network/encryption/PublicPlayerSession$Serialized;)V", at = @At("TAIL"))
    private void injected2(CallbackInfo ci) {
        listed = false;
    }

}

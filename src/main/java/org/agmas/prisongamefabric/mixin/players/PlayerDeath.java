package org.agmas.prisongamefabric.mixin.players;

import net.minecraft.entity.LivingEntity;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.util.Profile;
import org.agmas.prisongamefabric.util.Roles.Role;
import org.agmas.prisongamefabric.util.Tx;
import org.agmas.prisongamefabric.util.WardenProgress;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class PlayerDeath {

    @Inject(method = "setHealth", at = @At("HEAD"), cancellable = true)
    private void injected(float health, CallbackInfo ci) {
        if (health <= 0) {
            if ((LivingEntity)(Object)this instanceof ServerPlayerEntity spe) {
                Profile profile = Profile.getProfile(spe);
                profile.respawnTime = 20*5;
                Profile.resetPlayer(spe);
                if (PrisonGameFabric.progress.gameEndingWarden != null) {
                    if (PrisonGameFabric.progress.gameEndingWarden.equals(spe.getUuid())) {
                        PrisonGameFabric.handleDeadWarden(spe);
                        profile.setRole(Role.PRISONER);
                    }
                }
                ci.cancel();
            }
        }
    }



}

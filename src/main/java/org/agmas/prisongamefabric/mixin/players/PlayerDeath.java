package org.agmas.prisongamefabric.mixin.players;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageTracker;
import net.minecraft.network.PacketCallbacks;
import net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.util.Profile;
import org.agmas.prisongamefabric.util.Roles.Role;
import org.agmas.prisongamefabric.util.Tx;
import org.agmas.prisongamefabric.util.WardenProgress;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class PlayerDeath {

    @Shadow public abstract DamageTracker getDamageTracker();

    @Inject(method = "setHealth", at = @At("HEAD"), cancellable = true)
    private void injected(float health, CallbackInfo ci) {
        if (health <= 0) {
            if ((LivingEntity)(Object)this instanceof ServerPlayerEntity spe) {
                Profile profile = Profile.getProfile(spe);
                profile.respawnTime = 20*5;
                Profile.resetPlayer(spe);


                Text text = getDamageTracker().getDeathMessage();
                spe.networkHandler.send(new DeathMessageS2CPacket(spe.getId(), text), PacketCallbacks.of(() -> {
                    String string = text.asTruncatedString(256);
                    Text text2 = Text.translatable("death.attack.message_too_long", new Object[]{Text.literal(string).formatted(Formatting.YELLOW)});
                    Text text3 = Text.translatable("death.attack.even_more_magic", new Object[]{spe.getDisplayName()}).styled((style) -> {
                        return style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, text2));
                    });
                    return new DeathMessageS2CPacket(spe.getId(), text3);
                }));

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

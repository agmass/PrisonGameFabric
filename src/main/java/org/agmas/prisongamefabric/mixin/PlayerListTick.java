package org.agmas.prisongamefabric.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.PlayerListHeaderS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.UserCache;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.util.Profile;
import org.agmas.prisongamefabric.util.Roles.Role;
import org.agmas.prisongamefabric.util.Tx;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Mixin(MinecraftServer.class)
public abstract class PlayerListTick {

    @Shadow public abstract PlayerManager getPlayerManager();

    @Shadow @Nullable public abstract UserCache getUserCache();

    @Unique
    boolean hasWardens = false;

    @Inject(method = "tick", at = @At("HEAD"))
    private void injected(CallbackInfo ci) {
        Text header = Tx.tf(Formatting.GOLD,"\nPrisonButBad -- Fabric\nOpen Beta v1.2.0\n").append(Tx.tf(Formatting.WHITE, "by agmas! [/credits]\n"));
        HashMap<Role, ArrayList<PlayerEntity>> roleCount = new HashMap<>();
        getPlayerManager().getPlayerList().forEach((spe)->{
            Role role = Profile.getRole(spe);
            if (!roleCount.containsKey(role)) {
                roleCount.put(role, new ArrayList<>());
            }
            roleCount.get(role).add(spe);

        });
        MutableText prisoners = Text.literal("");
        MutableText guards = Text.literal("");
        MutableText warden = Text.literal("");

        hasWardens = false;
        roleCount.forEach((role,count)->{
            MutableText toAppend = Tx.tf(role.color, "\n"+role.name + " (" + count.size() + ")").append("\n");
            AtomicInteger i = new AtomicInteger();
            count.forEach((spe)->{
                ServerPlayerEntity aspe = (ServerPlayerEntity) spe;
                toAppend.append(Text.literal("\n").append(role.prefix).append(Tx.tf(role.secondaryColor, " " + spe.getName().getString() + " ").append(Tx.wrapInBrackets(Formatting.GRAY, Tx.tf(getPingColor(aspe.networkHandler.getLatency()), aspe.networkHandler.getLatency()+"ms")))));
                if (role.power==Role.PositionInPower.WARDEN) {
                    toAppend.append(Tx.tf(Formatting.DARK_RED, "\n❤" + Math.round(spe.getHealth())));
                    if (i.get() == 0) {
                        PrisonGameFabric.humanReadableWardenList = aspe.getName().getLiteralString();
                    } else {

                        PrisonGameFabric.humanReadableWardenList += ", " + aspe.getName().getLiteralString();
                    }
                }
                i.getAndIncrement();
            });
            if (role.power == Role.PositionInPower.WARDEN) {
                warden.append(toAppend.append(Text.literal(("\n"))));
                PrisonGameFabric.wardens = count;
                hasWardens = true;
            }
            else if (role.power == Role.PositionInPower.GUARD) {
                guards.append(toAppend.append(Text.literal(("\n"))));
            }
            else {
                prisoners.append(toAppend.append(Text.literal(("\n"))));
            }
        });
        if (!hasWardens) {
            PrisonGameFabric.wardens.clear();
            if (PrisonGameFabric.progress.gameEndingWarden != null) {
                PrisonGameFabric.handleDisconnectedWarden();
            }
            PrisonGameFabric.humanReadableWardenList = "available";
        }
        PlayerListHeaderS2CPacket headerS2CPacket = new PlayerListHeaderS2CPacket(header.copy().append(warden).append(guards).append(prisoners), Text.of(""));
        getPlayerManager().getPlayerList().forEach((spe)->{
            spe.networkHandler.sendPacket(headerS2CPacket);
        });
    }

    @Unique
    public Formatting getPingColor(int ping) {
        if (ping > 600)
            return Formatting.DARK_RED;
        if (ping > 400)
            return Formatting.RED;
        if (ping > 200)
            return Formatting.YELLOW;
        return Formatting.GREEN;
    }

}

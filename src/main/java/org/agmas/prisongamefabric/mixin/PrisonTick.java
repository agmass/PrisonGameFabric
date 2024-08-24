package org.agmas.prisongamefabric.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.PlayerListHeaderS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.UserCache;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.mapgame.microUis.MapsMicroUI;
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
public abstract class PrisonTick {

    @Shadow public abstract PlayerManager getPlayerManager();

    @Shadow @Nullable public abstract UserCache getUserCache();

    @Inject(method = "tick", at = @At("HEAD"))
    private void injected(CallbackInfo ci) {
        MapsMicroUI.cooldown--;
        PrisonGameFabric.active.getOnTick().orElse(new ArrayList<>()).forEach((i)->{
            CommandFunction<ServerCommandSource> function = PrisonGameFabric.serverInstance.getCommandFunctionManager().getFunction(i).orElse(null);
            PrisonGameFabric.serverInstance.getCommandFunctionManager().execute(function, PrisonGameFabric.commandSource);
        });
    }

}

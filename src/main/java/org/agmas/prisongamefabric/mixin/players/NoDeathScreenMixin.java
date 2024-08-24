package org.agmas.prisongamefabric.mixin.players;

import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.BossBarS2CPacket;
import net.minecraft.network.packet.s2c.play.HealthUpdateS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameMode;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.util.Profile;
import org.agmas.prisongamefabric.util.Roles.Role;
import org.agmas.prisongamefabric.util.Schedule;
import org.agmas.prisongamefabric.util.Tx;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HealthUpdateS2CPacket.class)
public abstract class NoDeathScreenMixin {
    @Mutable
    @Shadow @Final private float health;

    @Inject(method = "<init>(FIF)V", at = @At("TAIL"))
    private void injected(CallbackInfo ci) {
        health = Math.clamp(health, 1, Integer.MAX_VALUE);
    }


}

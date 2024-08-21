package org.agmas.prisongamefabric.util;

import net.fabricmc.fabric.impl.resource.loader.ServerLanguageUtil;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.SubtitleS2CPacket;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.prisons.Prison;
import org.agmas.prisongamefabric.prisons.PrisonZone;
import org.agmas.prisongamefabric.prisons.upgrades.PrisonUpgrade;
import org.agmas.prisongamefabric.util.Roles.Role;

import java.util.ArrayList;
import java.util.UUID;

public class WardenProgress {
    public double funds = 0.0;
    public ArrayList<PrisonUpgrade> upgrades = new ArrayList<>();
    public UUID gameEndingWarden=null;
    public String wardenName="No-one";

    public WardenProgress() {}

    public WardenProgress(PlayerEntity p) {
        gameEndingWarden = p.getUuid();
        wardenName = p.getName().getLiteralString();
        Profile profile = Profile.getProfile(p);
        profile.setRole(Role.WARDEN);
        p.getServer().getPlayerManager().getPlayerList().forEach((serverPlayerEntity -> {
            serverPlayerEntity.networkHandler.sendPacket(new TitleS2CPacket(Tx.tf(Formatting.RED,p.getName().getLiteralString())));
            serverPlayerEntity.networkHandler.sendPacket(new SubtitleS2CPacket(Tx.ttf(Formatting.GREEN, Text.translatable("warden.new"))));
            serverPlayerEntity.playSoundToPlayer(SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.MASTER, 1, 1);
            if (p.getUuid().equals(serverPlayerEntity.getUuid())) return;
            Profile profile2 = Profile.getProfile(serverPlayerEntity);
            if (profile2.role.power.powerful) {
                profile2.setRole(Role.PRISONER);
            }
        }));
        PrisonGameFabric.active.upgrades.forEach((u)->{
            u.lock(true);
            if (u.safeZone.isPresent()) {
                PrisonZone zone = u.safeZone.get();
                PrisonGameFabric.serverInstance.getPlayerManager().getPlayerList().forEach((pl)->{
                    Profile profile2 = Profile.getProfile(pl);
                    if (zone.isInside(pl)) {
                        profile2.teleportToSpawn();
                    }
                });
            }
        });
    }
}

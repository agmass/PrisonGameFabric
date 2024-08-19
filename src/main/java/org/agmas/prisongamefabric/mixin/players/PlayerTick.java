package org.agmas.prisongamefabric.mixin.players;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.BossBarS2CPacket;
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
import org.agmas.prisongamefabric.util.Schedule;
import org.agmas.prisongamefabric.util.StateSaverAndLoader;
import org.agmas.prisongamefabric.util.Tx;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.text.Format;

@Mixin(ServerPlayerEntity.class)
public abstract class PlayerTick {

    @Shadow @Final public ServerPlayerInteractionManager interactionManager;

    @Shadow public abstract boolean changeGameMode(GameMode gameMode);

    @Shadow public abstract void sendMessage(Text message, boolean overlay);

    @Shadow public ServerPlayNetworkHandler networkHandler;

    @Shadow public abstract ServerWorld getServerWorld();

    @Unique
    public BossBar playerSpecificBar = getServerWorld().getServer().getBossBarManager().add(Identifier.of("prisongamefabric", "playerbar"), Text.of("Player Bar"));

    @Inject(method = "tick", at = @At("HEAD"))
    private void injected(CallbackInfo ci) {
        if (interactionManager.isSurvivalLike()) {
            changeGameMode(GameMode.ADVENTURE);
        }

        PlayerEntity thisplayer = getThisplayer(this); // So IntelliJ stops fucking complaining the code is ""unreachable""

        if (thisplayer != null) {
            Profile profile = Profile.getProfile(thisplayer);

            profile.isInScene = thisplayer.getInventory().contains((is)->{
                if (is.getItem().equals(Items.FILLED_MAP)) {
                    if (is.getName().equals(Text.of("Computer"))) {
                        if (!thisplayer.getInventory().getArmorStack(3).equals(is)) {
                            thisplayer.getInventory().armor.set(3, profile.helmetItem);
                            is.setCount(0);
                            return false;
                        }
                        if (!profile.isInScene) {
                            profile.scene.justEnteredScene = true;
                        }
                        return true;
                    }
                }
                return false;
            });
            if (!profile.isInScene && profile.previousMapState!=null) {

                for (int y = 0; y < 128; y++) {
                    for (int x = 0; x < 128; x++) {
                        profile.previousMapState.putColor(x, y, Blocks.BLACK_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.LOWEST));
                    }
                }
                profile.previousMapState = null;
            }
            if (profile.respawnTime > 0) {
                profile.respawnTime -= 1;
                changeGameMode(GameMode.SPECTATOR);
                playerSpecificBar.setName(Tx.tf(Formatting.RED, "Respawning in " + profile.respawnTime/20));
                playerSpecificBar.setColor(BossBar.Color.RED);
                playerSpecificBar.setPercent(Math.clamp((float) profile.respawnTime/(float)profile.maxRespawnTime, 0, 1));
                networkHandler.sendPacket(BossBarS2CPacket.remove(Schedule.scheduleBar.getUuid()));
                networkHandler.sendPacket(BossBarS2CPacket.add(playerSpecificBar));
                profile.reviveDirty = true;
            } else if (profile.reviveDirty) {
                profile.reviveDirty = false;
                profile.setRole(profile.role);
                networkHandler.sendPacket(BossBarS2CPacket.remove(playerSpecificBar.getUuid()));
                changeGameMode(GameMode.ADVENTURE);
            } else {
                networkHandler.sendPacket(BossBarS2CPacket.add(Schedule.scheduleBar));
            }

            StateSaverAndLoader.PlayerData pd = StateSaverAndLoader.getPlayerState((ServerPlayerEntity) thisplayer);
            if (profile.actionBarInvasion <= 0) {
                sendMessage(Tx.tf(Formatting.GREEN, "$" + pd.money).append(Tx.tf(Formatting.GRAY, " | ").append(Tx.tf(Formatting.DARK_GRAY, "Current Warden: ").append(Tx.tf(Formatting.RED, PrisonGameFabric.humanReadableWardenList)))), true);
            } else {
                profile.actionBarInvasion--;
            }
        }
    }

    @Unique
    public PlayerEntity getThisplayer(PlayerTick pt) {
        return ((PlayerEntity) (Object)pt);
    }

}

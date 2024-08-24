package org.agmas.prisongamefabric.util;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.client.sound.Sound;
import net.minecraft.component.type.MapIdComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.map.MapState;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.mapgame.scenes.Bootup;
import org.agmas.prisongamefabric.prisons.PrisonLocation;
import org.agmas.prisongamefabric.util.Roles.Role;

import java.util.Random;

public class Profile {
    public Scene scene = new Bootup();
    public MapState previousMapState=null;
    public boolean isInScene = false;
    public Role role = Role.PRISONER;
    public Role proposedRole = null;
    public PlayerEntity player;
    public ItemStack helmetItem=ItemStack.EMPTY;
    public PrisonLocation blackMarketEnterancePoint =null;
    public int respawnTime=0;
    public int barrelCooldown=0;
    public int actionBarInvasion=0;
    public int maxRespawnTime=20*5;
    public boolean reviveDirty=true;

    public Profile(PlayerEntity p) {
        player = p;
        scene.player = (ServerPlayerEntity) player;
        setRole(Role.PRISONER);
    }

    public static void resetPlayer(PlayerEntity player) {
        player.getInventory().clear();
        player.setHealth(20);
        player.getHungerManager().setFoodLevel(20);
        player.getHungerManager().setSaturationLevel(8);
        player.getHungerManager().setExhaustion(0);
        player.clearStatusEffects();
    }

    public void teleportToSpawn() {
        if (role.power== Role.PositionInPower.WARDEN) {
            PrisonLocation teleportLocation = PrisonGameFabric.active.wardenSpawn;
            player.requestTeleport(teleportLocation.x, teleportLocation.y, teleportLocation.z);
            player.setPitch(teleportLocation.pitch.orElse(0f));
            player.setYaw(teleportLocation.yaw.orElse(0f));
        } else if (role.power== Role.PositionInPower.GUARD) {
            PrisonLocation teleportLocation = PrisonGameFabric.active.guardSpawn;
            player.requestTeleport(teleportLocation.x, teleportLocation.y, teleportLocation.z);
            player.setPitch(teleportLocation.pitch.orElse(0f));
            player.setYaw(teleportLocation.yaw.orElse(0f));
        } else {
            PrisonLocation randomCell = PrisonGameFabric.active.cellLocations.getFirst();
            if (PrisonGameFabric.active.cellLocations.size() > 1) {
                randomCell = PrisonGameFabric.active.cellLocations.get(new Random().nextInt(0,PrisonGameFabric.active.cellLocations.size()-1));
            }
            player.requestTeleport((double)randomCell.x, (double)randomCell.y, (double)randomCell.z);
            player.setPitch(randomCell.pitch.orElse(0f));
            player.setYaw(randomCell.yaw.orElse(0f));
        }
    }

    public void setRole(Role role) {
        setRole(role, RoleChangeModifier.RESET);
        proposedRole = null;
    }



    public void setRole(Role role, RoleChangeModifier mod) {
        if (!mod.equals(RoleChangeModifier.KITONLY))
            this.role = role;
        if (role.equals(Role.OUTOFGAME)) {
            mod = RoleChangeModifier.NONE;
        }
        if (mod.equals(RoleChangeModifier.RESET))
            resetPlayer(player);

        if (!mod.equals(RoleChangeModifier.NONE)) {
            RoleChangeModifier finalMod = mod;
            role.kit.forEach((item) -> {
                if (duplicateChecker(finalMod,player.getInventory(), item.copy()))
                    player.getInventory().insertStack(item.copy());
            });

            final int[] i = {0};

            role.armor.forEach((item)-> {
                if (finalMod.equals(RoleChangeModifier.MORPH) || finalMod.equals(RoleChangeModifier.KITONLY)) {
                    if (duplicateChecker(finalMod,player.getInventory(), item.copy()))
                        player.getInventory().insertStack(item.copy());
                } else {
                    if (duplicateChecker(finalMod,player.getInventory(), item.copy()))
                        player.getInventory().armor.set(i[0], item.copy());
                }
                i[0]++;
            });
        }

        if (mod.equals(RoleChangeModifier.RESET))
            teleportToSpawn();

    }

    public boolean duplicateChecker(RoleChangeModifier mod, PlayerInventory pi, ItemStack i) {
        if (mod != RoleChangeModifier.KITONLY) return true;
        return !pi.armor.contains(i) && !pi.contains(i);
    }

    public enum RoleChangeModifier {
        RESET,
        MORPH,
        KITONLY,
        NONE
    }

    public void addMoney(double money, boolean giveToWarden) {
        if (role.power== Role.PositionInPower.WARDEN) {
            PrisonGameFabric.progress.funds += money;
        } else {
            StateSaverAndLoader.PlayerData pd = StateSaverAndLoader.getPlayerState(player);
            pd.money += money;
            if (giveToWarden && Profile.getRole(player).power!= Role.PositionInPower.PRISONLESS) {
                PrisonGameFabric.progress.funds += money/4;
            }
        }
    }

    public void setMoney(double money) {
        if (role.power== Role.PositionInPower.WARDEN) {
            PrisonGameFabric.progress.funds = money;
        } else {
            StateSaverAndLoader.PlayerData pd = StateSaverAndLoader.getPlayerState(player);
            pd.money = money;
        }
    }
    public Formatting getMoneyColor() {
        if (role.power== Role.PositionInPower.WARDEN) {
            return Formatting.RED;
        } else {
            return Formatting.GREEN;
        }
    }

    public double getMoney() {
        if (role.power== Role.PositionInPower.WARDEN) {
            return PrisonGameFabric.progress.funds;
        } else {
            StateSaverAndLoader.PlayerData pd = StateSaverAndLoader.getPlayerState(player);
            return pd.money;
        }
    }
    public static Profile getProfile(PlayerEntity p) {
        return PrisonGameFabric.PlayerProfiles.get(p.getUuid());
    }

    public static Scene getScene(PlayerEntity p) {
        return PrisonGameFabric.PlayerProfiles.get(p.getUuid()).scene;
    }

    public static Role getRole(PlayerEntity p) {
        return PrisonGameFabric.PlayerProfiles.get(p.getUuid()).role;
    }
    public static void useFeedback(PlayerEntity p, PlayerFeedbackEnum fe) {
        useFeedback(p,fe,Text.literal(""));
    }
    public static void useFeedback(PlayerEntity p, PlayerFeedbackEnum fe, Text reason) {
        p.sendMessage(Tx.wrapInBrackets(Formatting.GRAY, Tx.ttf(fe.textColor, Text.translatable("feedback.prisongamefabric." + fe.name))).append(Tx.ttf(Formatting.GRAY, Text.literal(" ").append(reason))),true);
        getProfile(p).actionBarInvasion = 20;
        p.playSoundToPlayer(fe.sound, SoundCategory.MASTER,1f,1f);
    }

    public enum PlayerFeedbackEnum {
        ACCEPTED(Formatting.GREEN, "granted", SoundEvents.BLOCK_NOTE_BLOCK_BIT.value()),
        DENIED(Formatting.RED, "denied", SoundEvents.ENTITY_VILLAGER_NO);

        Formatting textColor;
        SoundEvent sound;
        String name;

        PlayerFeedbackEnum(Formatting tx, String nm, SoundEvent sound) {
            textColor = tx;
            this.sound = sound;
            name = nm;
        }
    }
}

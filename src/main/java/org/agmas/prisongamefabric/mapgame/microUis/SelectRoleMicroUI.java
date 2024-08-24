package org.agmas.prisongamefabric.mapgame.microUis;

import eu.pb4.polymer.core.impl.ui.MicroUi;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.util.Profile;
import org.agmas.prisongamefabric.util.Roles.Role;
import org.agmas.prisongamefabric.util.Tx;

import java.util.concurrent.atomic.AtomicInteger;

public class SelectRoleMicroUI extends MicroUi {

    public ServerPlayerEntity target = null;

    public SelectRoleMicroUI(ServerPlayerEntity spe, ServerPlayerEntity target) {
        super((int) Math.ceil((double) spe.getServer().getCurrentPlayerCount() /9));
        this.title(Tx.tf(Formatting.GRAY, "Hire"));
        this.open(spe);
        this.target = target;
        AtomicInteger loop = new AtomicInteger();
        for (Role r : Role.values()) {
            if (r.equals(Role.SWAT) && !PrisonGameFabric.progress.upgrades.contains(PrisonGameFabric.availablePrisonUpgrades.get(Identifier.of("prisongamefabric", "swat")))) {
                return;
            }
            if (r.power.powerful == Profile.getProfile(spe).role.power.powerful) {
                return;
            }
            if (r.power!= Role.PositionInPower.PRISONLESS && r.power!= Role.PositionInPower.WARDEN) {
                ItemStack helmet = Items.LEATHER_CHESTPLATE.getDefaultStack();
                helmet.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(r.color.getColorValue(), false));
                helmet.set(DataComponentTypes.ITEM_NAME, Tx.tf(r.color, r.name));
                this.slot(loop.get(), helmet, ((player, slotIndex, button, actionType) -> {
                    try {
                        if (r.power.powerful) {
                            player.sendMessage(Tx.ttf(r.color, Text.translatable("promotion.sent", target.getName())));
                            target.sendMessage(Tx.ttf(r.color, Text.translatable("promotion.recieved", r.name)));
                            player.closeHandledScreen();
                            Profile targetProfile = Profile.getProfile(target);
                            targetProfile.proposedRole = r;

                        } else {
                            Profile targetProfile = Profile.getProfile(target);
                            if (targetProfile.role.power.powerful) {
                                targetProfile.setRole(Role.PRISONER);
                                spe.getServer().getPlayerManager().getPlayerList().forEach((pl)->{
                                    pl.sendMessage(Tx.ttf(Formatting.GOLD,Text.translatable("promotion.fired", pl.getName())));
                                });
                                player.closeHandledScreen();
                            }
                        }
                    } catch (Exception ignored) {}
                }));
                loop.getAndAdd(1);
            }
        }
    }
}

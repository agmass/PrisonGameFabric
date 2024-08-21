package org.agmas.prisongamefabric.mapgame.microUis;

import eu.pb4.polymer.core.impl.ui.MicroUi;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.prisons.upgrades.PrisonUpgrade;
import org.agmas.prisongamefabric.util.Profile;
import org.agmas.prisongamefabric.util.Tx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UpgradesMicroUI extends MicroUi {
    public UpgradesMicroUI(ServerPlayerEntity spe) {
        super(2);
        this.title(Tx.tf(Formatting.GRAY, "Upgrades"));
        this.open(spe);
        AtomicInteger loop = new AtomicInteger();
        PrisonGameFabric.active.upgrades.forEach((a)->{
            PrisonUpgrade upgrade = PrisonGameFabric.availablePrisonUpgrades.get(a.upgrade);
            ItemStack upgradeRepresenter = upgrade.itemIconAsItem.getDefaultStack();
            if (PrisonGameFabric.progress.upgrades.contains(upgrade)) {
                upgradeRepresenter = Items.BEDROCK.getDefaultStack();
            }
            upgradeRepresenter.set(DataComponentTypes.ITEM_NAME, Tx.ttf(Formatting.GOLD, upgrade.getTranslatedName().copy()));
            List<String> desc = List.of();
            ArrayList<Text> finalD = new ArrayList<>();
            finalD.add(Tx.ttf(Formatting.WHITE,upgrade.getDescription().copy()));
            if (PrisonGameFabric.progress.upgrades.contains(upgrade)) {
                finalD.add(Tx.ttf(Formatting.GRAY, Text.translatable("upgrade.bought")));
            } else {
                finalD.add(Tx.ttf(Formatting.GRAY, Text.translatable("upgrade.price").append(" ")).append(Tx.tf(Formatting.GREEN, upgrade.price + "$")));
            }
            upgradeRepresenter.set(DataComponentTypes.LORE, new LoreComponent(finalD,finalD));
            if (!PrisonGameFabric.progress.upgrades.contains(upgrade)) {
                this.slot(loop.get(), upgradeRepresenter, (player, slotIndex, button, actionType) -> {
                    Profile prof = Profile.getProfile(player);
                    if (prof.getMoney() >= upgrade.price) {
                        PrisonGameFabric.serverInstance.getPlayerManager().getPlayerList().forEach((p) -> {
                            p.sendMessage(Tx.ttf(Formatting.GREEN, Text.translatable("upgrade.success").append(" ").append(upgrade.getTranslatedName()).append("!")));
                        });
                        prof.setMoney(prof.getMoney()-upgrade.price);
                        player.closeHandledScreen();
                        a.unlock(true);
                    } else {
                        player.sendMessage(Tx.ttf(Formatting.RED, Text.translatable("purchase.fail").append(" ").append(upgrade.getTranslatedName()).append("!")));

                    }
                });
            } else {
                this.slot(loop.get(), upgradeRepresenter);
            }
            loop.getAndIncrement();
        });
    }

}

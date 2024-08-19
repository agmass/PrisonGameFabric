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
            upgradeRepresenter.set(DataComponentTypes.ITEM_NAME, Tx.tf(Formatting.GOLD, upgrade.name));
            List<String> desc = List.of(upgrade.description.split("\\n"));
            ArrayList<Text> finalD = new ArrayList<>();
            desc.forEach((s)->{
                finalD.add(Tx.tf(Formatting.WHITE,s));
            });
            if (PrisonGameFabric.progress.upgrades.contains(upgrade)) {
                finalD.add(Tx.tf(Formatting.GRAY, "Bought!"));
            } else {
                finalD.add(Tx.tf(Formatting.GRAY, "Price: ").append(Tx.tf(Formatting.GREEN, upgrade.price + "$")));
            }
            upgradeRepresenter.set(DataComponentTypes.LORE, new LoreComponent(finalD,finalD));
            if (!PrisonGameFabric.progress.upgrades.contains(upgrade)) {
                this.slot(loop.get(), upgradeRepresenter, (player, slotIndex, button, actionType) -> {
                    Profile prof = Profile.getProfile(player);
                    if (prof.getMoney() >= upgrade.price) {
                        PrisonGameFabric.serverInstance.getPlayerManager().getPlayerList().forEach((p) -> {
                            p.sendMessage(Tx.tf(Formatting.GREEN, "The warden has bought " + upgrade.name + "!"));
                        });
                        prof.setMoney(prof.getMoney()-upgrade.price);
                        player.closeHandledScreen();
                        a.unlock(true);
                    } else {
                        player.sendMessage(Tx.tf(Formatting.RED, "You don't have enough money to buy " + upgrade.name + "!"));

                    }
                });
            } else {
                this.slot(loop.get(), upgradeRepresenter);
            }
            loop.getAndIncrement();
        });
    }

}

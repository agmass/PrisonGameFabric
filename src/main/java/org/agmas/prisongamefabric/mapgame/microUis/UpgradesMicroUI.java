package org.agmas.prisongamefabric.mapgame.microUis;

import eu.pb4.polymer.core.impl.ui.MicroUi;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.prisons.upgrades.PrisonUpgrade;
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
            upgradeRepresenter.set(DataComponentTypes.ITEM_NAME, Tx.tf(Formatting.GOLD, upgrade.name));
            List<String> desc = List.of(upgrade.description.split("\\n"));
            ArrayList<Text> finalD = new ArrayList<>();
            desc.forEach((s)->{
                finalD.add(Tx.tf(Formatting.RESET,s));
            });
            upgradeRepresenter.set(DataComponentTypes.LORE, new LoreComponent(new ArrayList<>(),finalD));
            this.slot(loop.get(), upgradeRepresenter, (player, slotIndex, button, actionType)->{
                PrisonGameFabric.runUnlock(a, player.getServer());
            });
            loop.getAndIncrement();
        });
    }

}

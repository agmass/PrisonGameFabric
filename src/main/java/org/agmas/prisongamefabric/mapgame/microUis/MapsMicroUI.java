package org.agmas.prisongamefabric.mapgame.microUis;

import eu.pb4.polymer.core.impl.ui.MicroUi;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Formatting;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.util.Tx;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MapsMicroUI extends MicroUi {
    public MapsMicroUI(ServerPlayerEntity spe) {
        super(2);
        this.title(Tx.tf(Formatting.GRAY, "Maps"));
        this.open(spe);
        AtomicInteger loop = new AtomicInteger();
        PrisonGameFabric.availablePrisons.forEach((a)->{
            ItemStack mapRepresenter = a.itemIconAsItem.getDefaultStack();
            mapRepresenter.set(DataComponentTypes.ITEM_NAME, Tx.tf(Formatting.GOLD, a.name));
            this.slot(loop.get(), mapRepresenter);
            loop.getAndIncrement();
        });
    }
}

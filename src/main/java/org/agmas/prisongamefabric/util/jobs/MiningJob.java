package org.agmas.prisongamefabric.util.jobs;

import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.StringIdentifiable;
import org.agmas.prisongamefabric.PrisonGameItems;
import org.agmas.prisongamefabric.util.Tx;

public enum MiningJob implements StringIdentifiable {
    LUMBERJACK("Lumberjack", PrisonGameItems.LUMBERJACKAXE, Tx.ttf(Formatting.GOLD, Text.translatable("tip.lumberjack"))),
    SHOVELING("Shoveling", PrisonGameItems.SHOVELSHOVEL, Tx.ttf(Formatting.WHITE, Text.translatable("tip.shovel")));

    public String name;
    public Item jobItem;
    public Text hint;


    MiningJob(String name, Item jobItem, Text hint) {
        this.name = name;
        this.jobItem = jobItem;
        this.hint = hint;
    }

    @Override
    public String asString() {
        return this.name;
    }
}

package org.agmas.prisongamefabric.util.jobs;

import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.StringIdentifiable;
import org.agmas.prisongamefabric.PrisonGameItems;
import org.agmas.prisongamefabric.util.Tx;

public enum MiningJob implements StringIdentifiable {
    LUMBERJACK("Lumberjack", PrisonGameItems.LUMBERJACKAXE, Tx.tf(Formatting.GOLD, "Mine spruce logs to get money!")),
    SHOVELING("Shoveling", PrisonGameItems.SHOVELSHOVEL, Tx.tf(Formatting.WHITE, "Mine coarse dirt to get money!"));

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

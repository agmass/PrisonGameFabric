package org.agmas.prisongamefabric.util.Roles;

import net.minecraft.item.ItemStack;
import org.agmas.prisongamefabric.PrisonGameItems;

import java.util.ArrayList;

public class Items {
    public static ArrayList<ItemStack> empty() {
        return new ArrayList<>();
    }
    public static ArrayList<ItemStack> guard() {
        ArrayList<ItemStack> guardItems = new ArrayList<>();
        guardItems.add(net.minecraft.item.Items.IRON_SWORD.getDefaultStack());
        guardItems.add(PrisonGameItems.TWO_CARD.getDefaultStack());
        return guardItems;
    }
    public static ArrayList<ItemStack> nurse() {
        ArrayList<ItemStack> nurseItems = new ArrayList<>();
        nurseItems.add(net.minecraft.item.Items.STONE_SWORD.getDefaultStack());
        nurseItems.add(PrisonGameItems.ONE_CARD.getDefaultStack());
        return nurseItems;
    }
    public static ArrayList<ItemStack> warden() {
        ArrayList<ItemStack> wardenItems = new ArrayList<>();
        wardenItems.add(net.minecraft.item.Items.DIAMOND_SWORD.getDefaultStack());
        wardenItems.add(PrisonGameItems.THREE_CARD.getDefaultStack());
        return wardenItems;
    }
}


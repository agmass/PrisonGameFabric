package org.agmas.prisongamefabric.util.Roles;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.agmas.prisongamefabric.PrisonGameItems;

import java.util.ArrayList;

public class KitItems {
    public static ArrayList<ItemStack> empty() {
        return new ArrayList<>();
    }
    public static ArrayList<ItemStack> guard() {
        ArrayList<ItemStack> guardItems = new ArrayList<>();
        guardItems.add(net.minecraft.item.Items.IRON_SWORD.getDefaultStack());
        guardItems.add(Items.COOKED_BEEF.getDefaultStack().copyWithCount(24));
        guardItems.add(Items.GOLDEN_APPLE.getDefaultStack().copyWithCount(4));
        guardItems.add(PrisonGameItems.TWO_CARD.getDefaultStack());
        return guardItems;
    }
    public static ArrayList<ItemStack> nurse() {
        ArrayList<ItemStack> nurseItems = new ArrayList<>();
        nurseItems.add(net.minecraft.item.Items.STONE_SWORD.getDefaultStack());
        nurseItems.add(Items.COOKED_BEEF.getDefaultStack().copyWithCount(24));
        nurseItems.add(Items.GOLDEN_APPLE.getDefaultStack().copyWithCount(2));
        nurseItems.add(PrisonGameItems.ONE_CARD.getDefaultStack());
        return nurseItems;
    }
    public static ArrayList<ItemStack> swat() {
        ArrayList<ItemStack> swatItems = new ArrayList<>();
        swatItems.add(net.minecraft.item.Items.IRON_SWORD.getDefaultStack());
        swatItems.add(net.minecraft.item.Items.MACE.getDefaultStack());
        swatItems.add(Items.COOKED_BEEF.getDefaultStack().copyWithCount(24));
        swatItems.add(Items.GOLDEN_APPLE.getDefaultStack().copyWithCount(8));
        swatItems.add(PrisonGameItems.THREE_CARD.getDefaultStack());
        return swatItems;
    }
    public static ArrayList<ItemStack> warden() {
        ArrayList<ItemStack> wardenItems = new ArrayList<>();
        wardenItems.add(net.minecraft.item.Items.DIAMOND_SWORD.getDefaultStack());
        wardenItems.add(Items.COOKED_BEEF.getDefaultStack().copyWithCount(24));
        wardenItems.add(PrisonGameItems.THREE_CARD.getDefaultStack());
        return wardenItems;
    }
}


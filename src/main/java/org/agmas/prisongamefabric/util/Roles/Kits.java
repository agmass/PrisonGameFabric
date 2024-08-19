package org.agmas.prisongamefabric.util.Roles;

import net.minecraft.client.color.item.ItemColors;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.PrisonGameItems;

import java.util.ArrayList;

public class Kits {
    public static ArrayList<ItemStack> empty() {
        return new ArrayList<>();
    }
    public static ArrayList<ItemStack> guard() {
        ArrayList<ItemStack> guardKit = new ArrayList<>();
        guardKit.add(Items.IRON_SWORD.getDefaultStack());
        guardKit.add(PrisonGameItems.TWO_CARD.getDefaultStack());
        return guardKit;
    }
    public static ArrayList<ItemStack> nurse() {
        ArrayList<ItemStack> guardKit = new ArrayList<>();
        guardKit.add(Items.STONE_SWORD.getDefaultStack());
        guardKit.add(PrisonGameItems.ONE_CARD.getDefaultStack());
        return guardKit;
    }
    public static ArrayList<ItemStack> warden() {
        ArrayList<ItemStack> guardKit = new ArrayList<>();
        guardKit.add(Items.DIAMOND_SWORD.getDefaultStack());
        guardKit.add(PrisonGameItems.THREE_CARD.getDefaultStack());
        return guardKit;
    }
}


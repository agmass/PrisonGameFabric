package org.agmas.prisongamefabric.util.Roles;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;

public class Armor {

    public static ArrayList<ItemStack> empty() {
        return new ArrayList<>();
    }
    public static ArrayList<ItemStack> prisoner() {
        ArrayList<ItemStack> guardKit = new ArrayList<>();
        ItemStack leatherChestplate = Items.LEATHER_CHESTPLATE.getDefaultStack();
        leatherChestplate.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(0xC17E23 , false));
        guardKit.add(leatherChestplate);
        ItemStack leatherLeggings = Items.LEATHER_LEGGINGS.getDefaultStack();
        leatherLeggings.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(0xC17E23 , false));
        guardKit.add(leatherLeggings);
        ItemStack leatherBoots = Items.LEATHER_BOOTS.getDefaultStack();
        leatherBoots.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(0x180E00 , false));
        guardKit.add(leatherBoots);
        return guardKit;
    }
    public static ArrayList<ItemStack> guard() {
        ArrayList<ItemStack> guardKit = new ArrayList<>();
        guardKit.add(Items.IRON_HELMET.getDefaultStack());
        ItemStack leatherChestplate = Items.LEATHER_CHESTPLATE.getDefaultStack();
        leatherChestplate.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(0x4681ff , false));
        guardKit.add(leatherChestplate);
        ItemStack leatherLeggings = Items.LEATHER_LEGGINGS.getDefaultStack();
        leatherLeggings.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(0x4681ff , false));
        guardKit.add(leatherLeggings);
        guardKit.add(Items.IRON_BOOTS.getDefaultStack());
        return guardKit;
    }
    public static ArrayList<ItemStack> nurse() {
        ArrayList<ItemStack> guardKit = new ArrayList<>();
        guardKit.add(Items.CHAINMAIL_HELMET.getDefaultStack());
        ItemStack leatherChestplate = Items.LEATHER_CHESTPLATE.getDefaultStack();
        leatherChestplate.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(0xf246ff , false));
        guardKit.add(leatherChestplate);
        ItemStack leatherLeggings = Items.LEATHER_LEGGINGS.getDefaultStack();
        leatherLeggings.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(0xf246ff , false));
        guardKit.add(leatherLeggings);
        leatherLeggings = Items.LEATHER_BOOTS.getDefaultStack();
        leatherLeggings.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(0xf246ff , false));
        guardKit.add(leatherLeggings);
        return guardKit;
    }
    public static ArrayList<ItemStack> warden() {
        ArrayList<ItemStack> guardKit = new ArrayList<>();
        guardKit.add(Items.DIAMOND_HELMET.getDefaultStack());
        guardKit.add(Items.IRON_CHESTPLATE.getDefaultStack());
        guardKit.add(Items.IRON_LEGGINGS.getDefaultStack());
        guardKit.add(Items.NETHERITE_BOOTS.getDefaultStack());
        return guardKit;
    }
}

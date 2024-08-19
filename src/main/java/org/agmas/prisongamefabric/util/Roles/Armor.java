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
        ArrayList<ItemStack> prisonerArmor = new ArrayList<>();
        ItemStack leatherChestplate = Items.LEATHER_CHESTPLATE.getDefaultStack();
        leatherChestplate.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(0xC17E23 , false));
        prisonerArmor.add(leatherChestplate);
        ItemStack leatherLeggings = Items.LEATHER_LEGGINGS.getDefaultStack();
        leatherLeggings.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(0xC17E23 , false));
        prisonerArmor.add(leatherLeggings);
        ItemStack leatherBoots = Items.LEATHER_BOOTS.getDefaultStack();
        leatherBoots.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(0x180E00 , false));
        prisonerArmor.add(leatherBoots);
        return prisonerArmor;
    }
    public static ArrayList<ItemStack> guard() {
        ArrayList<ItemStack> guardArmor = new ArrayList<>();
        guardArmor.add(Items.IRON_HELMET.getDefaultStack());
        ItemStack leatherChestplate = Items.LEATHER_CHESTPLATE.getDefaultStack();
        leatherChestplate.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(0x4681ff , false));
        guardArmor.add(leatherChestplate);
        ItemStack leatherLeggings = Items.LEATHER_LEGGINGS.getDefaultStack();
        leatherLeggings.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(0x4681ff , false));
        guardArmor.add(leatherLeggings);
        guardArmor.add(Items.IRON_BOOTS.getDefaultStack());
        return guardArmor;
    }
    public static ArrayList<ItemStack> nurse() {
        ArrayList<ItemStack> nurseArmor = new ArrayList<>();
        nurseArmor.add(Items.CHAINMAIL_HELMET.getDefaultStack());
        ItemStack leatherChestplate = Items.LEATHER_CHESTPLATE.getDefaultStack();
        leatherChestplate.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(0xf246ff , false));
        nurseArmor.add(leatherChestplate);
        ItemStack leatherLeggings = Items.LEATHER_LEGGINGS.getDefaultStack();
        leatherLeggings.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(0xf246ff , false));
        nurseArmor.add(leatherLeggings);
        leatherLeggings = Items.LEATHER_BOOTS.getDefaultStack();
        leatherLeggings.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(0xf246ff , false));
        nurseArmor.add(leatherLeggings);
        return nurseArmor;
    }
    public static ArrayList<ItemStack> swat() {
        ArrayList<ItemStack> swatArmor = new ArrayList<>();
        swatArmor.add(Items.NETHERITE_HELMET.getDefaultStack());
        swatArmor.add(Items.NETHERITE_CHESTPLATE.getDefaultStack());
        swatArmor.add(Items.IRON_LEGGINGS.getDefaultStack());
        ItemStack leatherBoots = Items.LEATHER_BOOTS.getDefaultStack();
        leatherBoots.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(0x1D1C1C , false));
        swatArmor.add(leatherBoots);
        return swatArmor;
    }
    public static ArrayList<ItemStack> warden() {
        ArrayList<ItemStack> wardenArmor = new ArrayList<>();
        wardenArmor.add(Items.DIAMOND_HELMET.getDefaultStack());
        wardenArmor.add(Items.IRON_CHESTPLATE.getDefaultStack());
        wardenArmor.add(Items.IRON_LEGGINGS.getDefaultStack());
        wardenArmor.add(Items.NETHERITE_BOOTS.getDefaultStack());
        return wardenArmor;
    }
}

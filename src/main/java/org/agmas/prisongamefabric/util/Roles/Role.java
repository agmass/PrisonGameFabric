package org.agmas.prisongamefabric.util.Roles;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.agmas.prisongamefabric.util.Tx;

import java.util.ArrayList;

public enum Role {
    OUTOFGAME("OutOfGame", Formatting.GRAY, Formatting.BLUE, PositionInPower.PRISONLESS, KitItems.empty (), Armor.empty(), Formatting.AQUA),
    CRIMINAL("Criminal", Formatting.RED, Formatting.GRAY, PositionInPower.PRISONLESS, KitItems.empty (), Armor.empty(), Formatting.GRAY),
    PRISONER("Prisoner", Formatting.GOLD, Formatting.GRAY, PositionInPower.PRISONER, KitItems.empty(), Armor.prisoner(), Formatting.DARK_GRAY),
    GUARD("Guard", Formatting.BLUE, Formatting.WHITE, PositionInPower.GUARD, KitItems.guard(), Armor.guard(), Formatting.GRAY),
    NURSE("Nurse", Formatting.LIGHT_PURPLE, Formatting.WHITE, PositionInPower.GUARD, KitItems.nurse(), Armor.nurse(), Formatting.GRAY),
    SWAT("Swat", Formatting.DARK_GRAY, Formatting.WHITE, PositionInPower.GUARD, KitItems.swat(), Armor.swat(), Formatting.GRAY),
    WARDEN("Warden", Formatting.RED, Formatting.WHITE, PositionInPower.WARDEN, KitItems.warden(), Armor.warden(), Formatting.RED);

    public final String name;
    public final Formatting color;
    public final Formatting backgroundColor;
    public final Formatting secondaryColor;
    public final Text prefix;
    public final PositionInPower power;
    public final ArrayList<ItemStack> kit;
    public final ArrayList<ItemStack> armor = new ArrayList<>();

    Role(String name, Formatting color, Formatting background, PositionInPower guard, ArrayList<ItemStack> kit, ArrayList<ItemStack> armor, Formatting secondaryColor) {
        this.name = name;
        this.color = color;
        this.backgroundColor = background;
        power = guard;
        this.kit = kit;
        this.secondaryColor = secondaryColor;
        this.armor.addAll(armor.reversed());
        prefix = Tx.wrapInBrackets(backgroundColor, Tx.tf(color, name.toUpperCase()));
    }

    public enum PositionInPower {
        PRISONLESS(false),
        PRISONER(false),
        GUARD(true),
        WARDEN(true);

        public boolean powerful = false;

        PositionInPower(boolean powerful) {
            this.powerful = powerful;
        }
    }


}

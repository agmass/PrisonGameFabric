package org.agmas.prisongamefabric.util.Roles;

import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.agmas.prisongamefabric.util.Tx;

import java.util.ArrayList;

public enum Role {
    OUTOFGAME("OutOfGame", Formatting.GRAY, Formatting.BLUE, PositionInPower.PRISONLESS, Kits.empty (), Armor.empty()),
    PRISONER("Prisoner", Formatting.GOLD, Formatting.GRAY, PositionInPower.PRISONER, Kits.empty(), Armor.prisoner()),
    GUARD("Guard", Formatting.BLUE, Formatting.WHITE, PositionInPower.GUARD, Kits.guard(), Armor.guard()),
    NURSE("Nurse", Formatting.LIGHT_PURPLE, Formatting.WHITE, PositionInPower.GUARD, Kits.nurse(), Armor.nurse()),
    WARDEN("Warden", Formatting.RED, Formatting.WHITE, PositionInPower.WARDEN, Kits.warden(), Armor.warden());

    public final String name;
    public final Formatting color;
    public final Formatting backgroundColor;
    public final Text prefix;
    public final PositionInPower power;
    public final ArrayList<ItemStack> kit;
    public ArrayList<ItemStack> armor = new ArrayList<>();

    Role(String name, Formatting color, Formatting background, PositionInPower guard, ArrayList<ItemStack> kit, ArrayList<ItemStack> armor) {
        this.name = name;
        this.color = color;
        this.backgroundColor = background;
        power = guard;
        this.kit = kit;
        this.armor.addAll(armor.reversed());
        prefix = Tx.wrapInBrackets(backgroundColor, Tx.tf(color, name.toUpperCase()));
    }

    public enum PositionInPower {
        PRISONLESS(false),
        PRISONER(false),
        GUARD(true),
        WARDEN(true);

        public boolean powerful=false;

        PositionInPower(boolean powerful) {
            this.powerful = powerful;
        }
    }


}

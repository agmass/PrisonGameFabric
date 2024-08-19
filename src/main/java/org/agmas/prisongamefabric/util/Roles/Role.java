package org.agmas.prisongamefabric.util.Roles;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.agmas.prisongamefabric.util.Tx;

import java.util.ArrayList;

public enum Role {
    OUTOFGAME("OutOfGame", Formatting.GRAY, Formatting.BLUE, PositionInPower.PRISONLESS, Items.empty (), Armor.empty()),
    PRISONER("Prisoner", Formatting.GOLD, Formatting.GRAY, PositionInPower.PRISONER, Items.empty(), Armor.prisoner()),
    GUARD("Guard", Formatting.BLUE, Formatting.WHITE, PositionInPower.GUARD, Items.guard(), Armor.guard()),
    NURSE("Nurse", Formatting.LIGHT_PURPLE, Formatting.WHITE, PositionInPower.GUARD, Items.nurse(), Armor.nurse()),
    WARDEN("Warden", Formatting.RED, Formatting.WHITE, PositionInPower.WARDEN, Items.warden(), Armor.warden());

    public final String name;
    public final Formatting color;
    public final Formatting backgroundColor;
    public final Text prefix;
    public final PositionInPower power;
    public final ArrayList<ItemStack> kit;
    public final ArrayList<ItemStack> armor = new ArrayList<>();

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

        public boolean powerful = false;

        PositionInPower(boolean powerful) {
            this.powerful = powerful;
        }
    }


}

package org.agmas.prisongamefabric.util;

import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Tx {
    public static MutableText tf(Formatting f, String t) {
        return Text.literal(t).setStyle(Style.EMPTY.withFormatting(Formatting.RESET).withFormatting(f)).copy();
    }

    public static MutableText wrapInBrackets(Formatting background, Text text) {
        return tf(background, "[").append(text.copy().append(tf(background, "]")));
    }
}

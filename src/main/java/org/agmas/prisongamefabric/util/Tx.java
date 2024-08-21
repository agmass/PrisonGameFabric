package org.agmas.prisongamefabric.util;

import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;
import java.util.Random;

public class Tx {
    public static MutableText tf(Formatting f, String t) {
        return Text.literal(t).setStyle(Style.EMPTY.withFormatting(Formatting.RESET).withFormatting(f)).copy();
    }

    public static MutableText ttf(Formatting f, MutableText t) {
        return t.setStyle(Style.EMPTY.withFormatting(Formatting.RESET).withFormatting(f)).copy();
    }



    public static MutableText wrapInBrackets(Formatting background, Text text) {
        return tf(background, "[").append(text.copy().append(tf(background, "]")));
    }

    public static String randomYour() {
        return List.of("you're", "youre", "you'r", "your", "yoru", "you're'r", "youre'r", "your'e'r", "you'r", "youer", "your'e").get(new Random().nextInt(0,8));
    }
}

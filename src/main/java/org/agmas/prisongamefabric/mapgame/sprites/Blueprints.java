package org.agmas.prisongamefabric.mapgame.sprites;

import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.mapgame.Sprite;
import org.agmas.prisongamefabric.mapgame.image.Image;
import org.agmas.prisongamefabric.mapgame.image.Template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Blueprints extends Sprite {


    ArrayList<Byte[][]> prints = new ArrayList<>();

    public Blueprints(Scene parent) {
        super(parent);
        byte w = Blocks.WHITE_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.HIGH);
        byte b = Blocks.BLUE_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.HIGH);

        List<String> templates = new ArrayList<>();

        templates.add("""
                bbbwbbbbbbwbbbbb
                bbwbbbbbbbbwbbbb
                bbwbbbbbbbbwbbbb
                bbbwbbbbbbwbbbbb
                bbbwbbbbbbwbbbbb
                bbbbwbbbbwbbbbbb
                bbbbwbbbbwbbbbbb
                bbbwbbbbbwbbbbbb
                """);

        templates.add("""
                bwwwwwwwwwwwwwb
                bwbbbbbbbbbbbwb
                bwbbbbbbbbbbbwb
                bwwbbbbbbbbbwwb
                bwwwwwwwwwwwwwb
                bwbbbbbbbbbbbwb
                bwbbbwwwwwbbbwb
                bwwwwwbbbwwwwwb
                """);

        templates.add("""
                wwwwbbbwwwwwwww
                bbbwbbbbbbwbbww
                bbbwbbbbbbwbbww
                bbbbbbbbbbwwwww
                bbbbbbbbbbbbbbw
                bbbwbbbbbbwbbbw
                bbbwwwwwwwwbbbw
                bbbwbbbbbwwwbbw
                """);

        templates.add("""
                bbbbwwbbwwbbbbb
                bbbwbbbbbbwbbbb
                wwwwbbbbbbwwwww
                bbbbbbbbbbbbbbb
                bbbbbbbbbbbbbbb
                wwwwbbbbbbwwwww
                bbbwbbbbbbwbbbb
                bbbbwwbbwwbbbbb
                """);

        templates.add("""
                wbwbbbwbbwbbbbb
                wbwbbbwbbwbbbbb
                wbwwbwwbwwbbbbb
                bbbbbbbbbbbbbbb
                bbbbbbbbbbbbbbb
                wbwwbwwwbwbbbbb
                wbwbbbwbbwbbbbb
                wbwbbbwbbwbbbbb
                """);

        templates.add("""
                bbwbbbwbbbbbbbb
                wwwbbbwwwwwwwww
                bbbbbbbbbbbbbbb
                bbbbbbbbbbbbbbb
                bbbbbbbbbbbbbbb
                wwwwwwwwwwwwwww
                bbbbbbbbbbbbbbb
                bbbbbbbbbbbbbbb
                """);

        templates.add("""
                bbbbbbbbbwbbbwb
                wwwwwwwwwwbbbww
                bbbbbbbbbbbbbbw
                bbbbbbbbbbbbbbw
                bbbbbbbbbbbbbbw
                wwwwbbbwwwwwwww
                bbbwbbbwbbbbbbb
                bbbwbbbwbbbbbbb
                """);

        for (String template : templates) {
            String[] lines = template.split(" ");

            prints.add(new Image(lines[0].length(), lines.length)
                    .applyTemplate(new Template(template)
                            .put('b', b)
                            .put('w', w))
                    .bitmap);
        }

        scaleToFit(prints.getFirst());
    }

    public void scaleToFit(Byte[][] a) {
        bitmap = new Byte[8*16][16*8];
        int yLevel = 0;
        for (Byte[] bytes : bitmap) {
            Arrays.fill(bytes, parent.backgroundColor);
        }
        for (Byte[] bytes : a) {
            int xLevel = 0;
            for (Byte aByte : bytes) {
                for (int i = 0; i < 8; i++) {
                    if (bitmap.length > (yLevel*16)) {
                        if (bitmap[yLevel * 16].length-1 > (xLevel*8) + i) {

                            bitmap[yLevel * 16][(xLevel*8) + i] = aByte;
                        }
                    }
                }
                xLevel++;
            }
            for (int i = 0; i < 16; i++) {
                if (bitmap.length-1 > (yLevel*16)+i) {
                    bitmap[(yLevel * 16) + i] = bitmap[yLevel * 16];
                }
            }
            yLevel++;

        }
    }

    int aaa = 0;

    @Override
    public void update() {
        if (parent.justAttacked) {
            aaa++;
            if (prints.size()-1 < aaa) {
                aaa = 0;
            }

            scaleToFit(prints.get(aaa));
        }
        super.update();
    }
}

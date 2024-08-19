package org.agmas.prisongamefabric.mapgame.sprites;

import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.mapgame.Sprite;

import java.util.ArrayList;
import java.util.Arrays;

public class Blueprints extends Sprite {


    ArrayList<Byte[][]> prints = new ArrayList<>();

    public Blueprints(Scene parent) {
        super(parent);
        byte w = Blocks.WHITE_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.HIGH);
        byte b = Blocks.BLUE_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.HIGH);
        prints.add(new Byte[][]{
                {b,b,b,w,b,b,b,b,b,b,w,b,b,b,b,b},
                {b,b,w,b,b,b,b,b,b,b,b,w,b,b,b,b},
                {b,b,w,b,b,b,b,b,b,b,b,w,b,b,b,b},
                {b,b,b,w,b,b,b,b,b,b,w,b,b,b,b,b},
                {b,b,b,w,b,b,b,b,b,b,w,b,b,b,b,b},
                {b,b,b,b,w,b,b,b,b,w,b,b,b,b,b,b},
                {b,b,b,b,w,b,b,b,b,w,b,b,b,b,b,b},
                {b,b,b,w,b,b,b,b,b,w,b,b,b,b,b,b},
        });
        prints.add(new Byte[][]{
                {b,w,w,w,w,w,w,w,w,w,w,w,w,w,w,b},
                {b,w,b,b,b,b,b,b,b,b,b,b,b,b,w,b},
                {b,w,b,b,b,b,b,b,b,b,b,b,b,b,w,b},
                {b,w,w,b,b,b,b,b,b,b,b,b,b,w,w,b},
                {b,w,w,w,w,w,w,w,w,w,w,w,w,w,w,b},
                {b,w,b,b,b,b,b,b,b,b,b,b,b,b,w,b},
                {b,w,b,b,b,w,w,w,w,w,b,b,b,b,w,b},
                {b,w,w,w,w,w,b,b,b,w,w,w,w,w,w,b},
        });
        prints.add(new Byte[][]{
                {w,w,w,w,w,b,b,b,w,w,w,w,w,w,w,w},
                {b,b,b,b,w,b,b,b,b,b,b,w,b,b,w,w},
                {b,b,b,b,w,b,b,b,b,b,b,w,b,b,w,w},
                {b,b,b,b,b,b,b,b,b,b,b,w,w,w,w,w},
                {b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,w},
                {b,b,b,b,w,b,b,b,b,b,b,w,b,b,b,w},
                {b,b,b,b,w,w,w,w,w,w,w,w,b,b,b,w},
                {b,b,b,b,w,b,b,b,b,b,w,w,w,b,b,w},
        });
        prints.add(new Byte[][]{
                {b,b,b,b,b,w,w,b,b,w,w,b,b,b,b,b},
                {b,b,b,b,w,b,b,b,b,b,b,w,b,b,b,b},
                {w,w,w,w,w,b,b,b,b,b,b,w,w,w,w,w},
                {b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b},
                {b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b},
                {w,w,w,w,w,b,b,b,b,b,b,w,w,w,w,w},
                {b,b,b,b,w,b,b,b,b,b,b,w,b,b,b,b},
                {b,b,b,b,b,w,w,b,b,w,w,b,b,b,b,b},
        });
        prints.add(new Byte[][]{
                {w,w,b,w,b,b,b,w,b,b,w,b,b,b,b,b},
                {w,b,b,w,b,b,b,w,b,b,w,b,b,b,b,b},
                {w,w,b,w,w,b,w,w,b,w,w,b,b,b,b,b},
                {b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b},
                {b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b},
                {w,w,b,w,w,b,w,w,w,b,w,b,b,b,b,b},
                {w,b,b,w,b,b,b,w,b,b,w,b,b,b,b,b},
                {w,b,b,w,b,b,b,w,b,b,w,b,b,b,b,b},
        });
        prints.add(new Byte[][]{
                {b,b,b,w,b,b,b,w,b,b,b,b,b,b,b,b},
                {w,w,w,w,b,b,b,w,w,w,w,w,w,w,w,w},
                {b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b},
                {b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b},
                {b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b},
                {w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w},
                {b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b},
                {b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b},
        });
        prints.add(new Byte[][]{
                {b,b,b,b,b,b,b,b,b,b,w,b,b,b,w,b},
                {w,w,w,w,w,w,w,w,w,w,w,b,b,b,w,w},
                {b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,w},
                {b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,w},
                {b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,w},
                {w,w,w,w,w,b,b,b,w,w,w,w,w,w,w,w},
                {b,b,b,b,w,b,b,b,w,b,b,b,b,b,b,b},
                {b,b,b,b,w,b,b,b,w,b,b,b,b,b,b,b},
        });
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

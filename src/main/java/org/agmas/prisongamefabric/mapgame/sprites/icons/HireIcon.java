package org.agmas.prisongamefabric.mapgame.sprites.icons;

import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.mapgame.microUis.HireMicroUI;
import org.agmas.prisongamefabric.mapgame.microUis.MapsMicroUI;

public class HireIcon extends Icon {
    public HireIcon(Scene parent) {
        super(parent);
        width = 32;
        height = 32;

        byte n = -1;
        byte b = Blocks.BLACK_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.NORMAL);
        byte w = Blocks.WHITE_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.NORMAL);
        byte s = Blocks.BLUE_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.NORMAL);
        bitmap = new Byte[][]{
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,n,n,n},
                {n,n,n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n,n,n},
                {n,n,n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n,n,n},
                {n,n,n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n,n,n},
                {n,n,n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n,n,n},
                {n,n,n,b,s,s,s,s,s,s,s,b,b,b,b,b,b,b,b,b,b,s,s,s,s,s,s,s,b,n,n,n},
                {n,n,n,b,s,s,s,s,s,s,b,w,w,w,w,w,w,w,w,w,w,b,s,s,s,s,s,s,b,n,n,n},
                {n,n,n,b,s,s,s,s,s,b,w,w,w,w,w,w,w,w,w,w,w,w,b,s,s,s,s,s,b,n,n,n},
                {n,n,n,b,s,s,s,s,s,b,w,w,w,w,w,w,w,w,w,w,w,w,b,s,s,s,s,s,b,n,n,n},
                {n,n,n,b,s,s,s,s,s,b,w,w,w,w,w,w,w,w,w,w,w,w,b,s,s,s,s,s,b,n,n,n},
                {n,n,n,b,s,s,s,s,s,b,w,w,w,w,w,w,w,w,w,w,w,w,b,s,s,s,s,s,b,n,n,n},
                {n,n,n,b,s,s,s,s,s,b,w,w,w,b,b,b,b,b,b,w,w,w,b,s,s,s,s,s,b,n,n,n},
                {n,n,n,b,s,s,s,s,s,b,w,w,b,s,s,s,s,s,s,b,w,w,b,s,s,s,s,s,b,n,n,n},
                {n,n,n,b,s,s,s,s,s,b,w,w,b,s,s,s,s,s,s,b,w,w,b,s,s,s,s,s,b,n,n,n},
                {n,n,n,b,s,s,s,s,s,b,b,b,b,s,s,s,s,s,s,b,b,b,b,s,s,s,s,s,b,n,n,n},
                {n,n,n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n,n,n},
                {n,n,n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n,n,n},
                {n,n,n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n,n,n},
                {n,n,n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n,n,n},
                {n,n,n,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,n,n,n},
                {n,n,n,n,n,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,n,n,n,n,n},
                {n,n,n,n,n,w,b,b,w,b,b,w,b,b,w,b,b,b,b,w,w,b,b,b,b,b,w,n,n,n,n,n},
                {n,n,n,n,n,w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,w,w,w,n,n,n,n,n},
                {n,n,n,n,n,w,b,b,b,b,b,w,b,b,w,b,b,w,b,b,w,b,b,b,b,w,w,n,n,n,n,n},
                {n,n,n,n,n,w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,w,w,n,n,n,n,n,n},
                {n,n,n,n,n,w,b,b,w,b,b,w,b,b,w,b,b,b,b,w,w,b,b,w,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,w,b,b,w,b,b,w,b,b,w,b,b,w,b,w,w,b,b,w,w,w,w,n,n,n,n,n},
                {n,n,n,n,n,w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,b,b,b,w,n,n,n,n,n},
                {n,n,n,n,n,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,n,n,n,n,n}
        };
    }

    @Override
    public void update() {
        if (selected) {
            if (parent.justAttacked) {
                new HireMicroUI(parent.player);
            }
        }
        super.update();
    }
}

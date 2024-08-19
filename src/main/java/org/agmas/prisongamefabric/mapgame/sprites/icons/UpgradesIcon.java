package org.agmas.prisongamefabric.mapgame.sprites.icons;

import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.mapgame.microUis.MapsMicroUI;
import org.agmas.prisongamefabric.mapgame.microUis.UpgradesMicroUI;

public class UpgradesIcon extends Icon {
    public UpgradesIcon(Scene parent) {
        super(parent);
        width = 48;
        height = 32;

        byte n = -1;
        byte b = Blocks.BLACK_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.NORMAL);
        byte w = Blocks.WHITE_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.NORMAL);
        byte s = Blocks.LIGHT_BLUE_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.NORMAL);
        byte d = Blocks.BROWN_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.NORMAL);
        byte g = Blocks.GRASS_BLOCK.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.HIGH);
        bitmap = new Byte[][]{
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,n},
                {n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n},
                {n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n},
                {n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n},
                {n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n},
                {n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n},
                {n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n},
                {n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n},
                {n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n},
                {n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n},
                {n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n},
                {n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n},
                {n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n},
                {n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n},
                {n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n},
                {n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n},
                {n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n},
                {n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n},
                {n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n},
                {n,b,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,s,b,n},
                {n,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {w,w,w,w,w,w,w,w,w,w,w,w,n,w,w,w,w,w,w,w,w,w,w,w,n,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w},
                {w,b,b,w,b,b,w,b,b,b,b,w,w,w,b,b,b,w,w,b,b,b,b,w,w,w,b,b,b,w,w,b,b,b,b,w,w,b,b,b,b,b,w,w,b,b,b,w},
                {w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,w,w,w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,w,w,w,b,b,w,w,w},
                {w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,w,w,w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,b,b,w,w,b,b,w,w,n},
                {w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,b,w,w,w,w,w,w,b,b,w,w},
                {w,b,b,w,b,b,w,b,b,b,b,b,w,b,b,w,b,b,w,b,b,b,b,w,w,b,b,b,b,b,w,b,b,w,b,b,w,b,w,n,n,n,w,w,w,b,b,w},
                {w,b,b,w,b,b,w,b,b,w,w,w,w,b,b,w,b,b,w,b,b,w,b,w,w,b,b,w,b,b,w,b,b,w,b,b,w,b,w,w,w,w,w,w,w,b,b,w},
                {w,w,b,b,b,w,w,b,b,w,w,w,w,w,b,b,b,b,w,b,b,w,b,b,w,b,b,w,b,b,w,b,b,b,b,w,w,b,b,b,b,b,w,b,b,b,w,w},
                {n,w,w,w,w,w,w,w,w,w,n,n,n,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,n},
        };
    }

    @Override
    public void update() {
        if (selected) {
            if (parent.justAttacked) {
                new UpgradesMicroUI(parent.player);
            }
        }
        super.update();
    }
}

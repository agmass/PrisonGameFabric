package org.agmas.prisongamefabric.mapgame.sprites.icons;

import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.mapgame.image.Image;
import org.agmas.prisongamefabric.mapgame.microUis.HireMicroUI;
import org.agmas.prisongamefabric.mapgame.microUis.MapsMicroUI;

import java.util.Arrays;

public class HireIcon extends Icon {
    public HireIcon(Scene parent) {
        super(parent);
        width = 32;
        height = 32;

        byte n = -1;
        byte b = Blocks.BLACK_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.NORMAL);
        byte w = Blocks.WHITE_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.NORMAL);
        byte s = Blocks.BLUE_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.NORMAL);

        bitmap = new Image(width, height)
                .fillRectangle(3, 1, width - 3, height - 12, b)
                .fillRectangle(4, 2, width - 4, height - 13, s)
                .drawText(5, height - 10, "HIRE")
                .bitmap;
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

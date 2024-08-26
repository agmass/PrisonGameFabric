package org.agmas.prisongamefabric.mapgame.sprites.icons;

import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.mapgame.image.Image;
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

        bitmap = new Image(width, height)
                .fillRectangle(1, 1, width - 1, height - 11, b)
                .fillRectangle(2, 2, width - 2, height - 12, s)
                .drawText(0, height - 9, "UPGRADES")
                .bitmap;
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

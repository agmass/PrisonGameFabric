package org.agmas.prisongamefabric.mapgame.sprites.icons;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.network.packet.s2c.play.InventoryS2CPacket;
import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.mapgame.Sprite;
import org.agmas.prisongamefabric.mapgame.image.Image;
import org.agmas.prisongamefabric.mapgame.microUis.MapsMicroUI;

public class MapsIcon extends Icon {
    public MapsIcon(Scene parent) {
        super(parent);
        width = 32;
        height = 32;

        byte n = -1;
        byte b = Blocks.BLACK_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.NORMAL);
        byte w = Blocks.WHITE_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.NORMAL);
        byte s = Blocks.BLUE_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.NORMAL);
        byte d = Blocks.BROWN_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.NORMAL);
        byte g = Blocks.GRASS_BLOCK.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.HIGH);

        bitmap = new Image(width, height)
                .fillRectangle(3, 1, width - 3, height - 12, b)
                .fillRectangle(4, 2, width - 4, height - 13, s)
                .fillRectangle(4, 13, width - 4, height - 13, g)
                .fillRectangle(4, 14, width - 4, height - 13, d)
                .drawText(3, width - 10, "MAPS")
                .bitmap;
    }

    @Override
    public void update() {
        if (selected) {
            if (parent.justAttacked) {
                new MapsMicroUI(parent.player);
            }
        }
        super.update();
    }
}

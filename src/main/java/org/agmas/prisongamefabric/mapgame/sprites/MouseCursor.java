package org.agmas.prisongamefabric.mapgame.sprites;

import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.mapgame.Sprite;

public class MouseCursor extends Sprite {
    public MouseCursor(Scene parent) {
        super(parent);
        width = 5;
        height = 6;
        Byte colorBlack = Blocks.BLACK_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.HIGH);
        bitmap = new Byte[][]{{colorBlack, colorBlack},{colorBlack,14,colorBlack},{colorBlack,14,14,colorBlack},{colorBlack,14,14,14,colorBlack},{colorBlack,14,14,colorBlack},{colorBlack,colorBlack,colorBlack}};
    }

    @Override
    public void update() {
        x = parent.playerMouseX;
        y = parent.playerMouseY;
        super.update();
    }
}

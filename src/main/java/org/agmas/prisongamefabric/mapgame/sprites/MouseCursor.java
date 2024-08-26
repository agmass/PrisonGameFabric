package org.agmas.prisongamefabric.mapgame.sprites;

import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.mapgame.Sprite;
import org.agmas.prisongamefabric.mapgame.image.Image;
import org.agmas.prisongamefabric.mapgame.image.Template;

public class MouseCursor extends Sprite {
    public MouseCursor(Scene parent) {
        super(parent);
        width = 5;
        height = 6;
        Byte colorBlack = Blocks.BLACK_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.HIGH);

        String template = """
                bb000
                bwb00
                bwwb0
                bwwwb
                bwwb0
                bbb00
                """;

        bitmap = new Image(width, height)
                .applyTemplate(new Template(template)
                                .put('b', colorBlack)
                                .put('w', (byte) 0xE))
                .bitmap;
    }

    @Override
    public void update() {
        x = parent.playerMouseX;
        y = parent.playerMouseY;
        super.update();
    }
}

package org.agmas.prisongamefabric.mapgame.sprites;

import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.mapgame.Sprite;
import org.agmas.prisongamefabric.mapgame.image.Image;
import org.agmas.prisongamefabric.mapgame.image.Template;

public class WardenBootup extends Sprite {

    public WardenBootup(Scene parent) {
        super(parent);
        width = 24;
        height = 24;
        byte n = -1;
        byte border = Blocks.NETHERRACK.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.NORMAL);
        byte shadow2 = Blocks.RED_WOOL.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.HIGH);
        byte background = Blocks.CRIMSON_NYLIUM.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.HIGH);

        byte foreground = Blocks.SNOW.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.HIGH);
        byte shadow = Blocks.WHITE_TERRACOTTA.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.HIGH);

        String template = """
                sfff00000fff
                sfff00000fff
                sfff00f00fff
                sfff00f00fff
                sfff00f00fff
                000sffffff00
                000sffffff00
                000sffsfff00
                0000ss0sss00
                """;

        bitmap = new Image(width, height)
                .drawBorderRadius(0, 0, width - 1, height - 1, 3, border)
                .fillInsideBorder(0, 0, width - 1, height - 1, 3, background)
                .fillInsideBorder(0, 17, width - 1, height - 1, 3, shadow2, 1)
                .applyTemplate(6, 7, new Template(template)
                        .put('s', shadow)
                        .put('f', foreground))
                .bitmap;
    }

    @Override
    public void update() {
        x = 64-12;
        y = 64-24;
        super.update();
    }
}

package org.agmas.prisongamefabric.mapgame.sprites;

import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.mapgame.Sprite;

public class Hotbar extends Sprite {
    public Hotbar(Scene parent) {
        super(parent);
        width = 128;
        height = 8;
        sampleColor = Blocks.GRAY_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.HIGH);
    }

    @Override
    public void update() {
        y = 120;
        x = 0;
        super.update();
    }
}

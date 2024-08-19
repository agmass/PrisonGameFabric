package org.agmas.prisongamefabric.mapgame.sprites;

import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.mapgame.Sprite;

public class WardenBootup extends Sprite {

    public WardenBootup(Scene parent) {
        super(parent);
        width = 24;
        height = 24;
        byte n = -1;
        byte border = Blocks.NETHERRACK.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.NORMAL);
        byte s = Blocks.RED_WOOL.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.HIGH);
        byte B = Blocks.CRIMSON_NYLIUM.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.HIGH);

        byte F = Blocks.SNOW.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.HIGH);
        byte S = Blocks.WHITE_TERRACOTTA.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.HIGH);
        bitmap = new Byte[][]{
                {n,n,n,border,border,border,border,border,border,border,border,border,border,border,border,border,border,border,border,border,border,n,n,n},
                {n,n,border, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B,border,n,n},
                {n,border, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B,border,n},
                {border, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B,border},
                {border, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B,border},
                {border, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B,border},
                {border, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B,border},
                {border, B, B, B, B, B,S,F,F,F, B, B, B, B, B,F,F,F, B, B, B, B, B,border},
                {border, B, B, B, B, B,S,F,F,F, B, B, B, B, B,F,F,F, B, B, B, B, B,border},
                {border, B, B, B, B, B,S,F,F,F, B, B,F, B, B,F,F,F, B, B, B, B, B,border},
                {border, B, B, B, B, B,S,F,F,F, B, B,F, B, B,F,F,F, B, B, B, B, B,border},
                {border, B, B, B, B, B,S,F,F,F, B, B,F, B, B,F,F,F, B, B, B, B, B,border},
                {border, B, B, B, B, B,B, B, B, S, F, F,F, F, F,F,B,B, B, B, B, B, B,border},
                {border, B, B, B, B, B,B, B, B, S, F, F,F, F, F,F,B,B, B, B, B, B, B,border},
                {border, B, B, B, B, B,B, B, B, S, F, F,S, F, F,F,B,B, B, B, B, B, B,border},
                {border, B, B, B, B, B,B, B, B, B, S, S,B, S, S,S,B,B, B, B, B, B, B,border},
                {border, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B,border},
                {border, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B, B,border},
                {border, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s,border},
                {border, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s,border},
                {border, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s,border},
                {n,border, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s,border,n},
                {n,n, border, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, s, border,n,n},
                {n,n, n, border, border, border, border, border, border, border, border, border, border, border, border, border, border, border, border, border, border, n,n,n},
        };
    }

    @Override
    public void update() {
        x = 64-12;
        y = 64-24;
        super.update();
    }
}

package org.agmas.prisongamefabric.mapgame.scenes;

import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.mapgame.sprites.Hotbar;
import org.agmas.prisongamefabric.mapgame.sprites.icons.Icon;
import org.agmas.prisongamefabric.mapgame.sprites.icons.MapsIcon;
import org.agmas.prisongamefabric.mapgame.sprites.icons.UpgradesIcon;

import java.util.ArrayList;

public class Wallpaper extends Scene {
    Hotbar hotbar;
    MapsIcon mapsIcon;
    UpgradesIcon upgradesIcon;
    ArrayList<Icon> selectableIcons = new ArrayList<>();
    public Wallpaper() {
        super();
        backgroundColor = Blocks.BLUE_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.LOW);
        hotbar = new Hotbar(this);
        sprites.add(hotbar);
        mapsIcon = new MapsIcon(this);
        sprites.add(mapsIcon);
        selectableIcons.add(mapsIcon);
        mapsIcon.x = 3;
        mapsIcon.y = 3;
        upgradesIcon = new UpgradesIcon(this);
        sprites.add(upgradesIcon);
        selectableIcons.add(upgradesIcon);
        upgradesIcon.x = 3;
        upgradesIcon.y = 3+mapsIcon.height;
        mouseCursor.x = 0;
        mouseCursor.y = 0;
        sprites.add(mouseCursor);
    }

    @Override
    public void update() {
        super.update();
    }
}

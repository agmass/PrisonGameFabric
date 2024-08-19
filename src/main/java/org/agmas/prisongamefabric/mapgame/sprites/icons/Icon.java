package org.agmas.prisongamefabric.mapgame.sprites.icons;

import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.mapgame.Sprite;

public class Icon extends Sprite {
    public Icon(Scene parent) {
        super(parent);
    }
    public boolean selected = false;

    @Override
    public void update() {

        if (parent.mouseCursor.x > x && parent.mouseCursor.y > y) {
            if (parent.mouseCursor.x < x+width && parent.mouseCursor.y < y+height) {
                selected = true;
            } else {
                selected = false;
            }
        } else {
            selected = false;
        }
        super.update();
    }
}

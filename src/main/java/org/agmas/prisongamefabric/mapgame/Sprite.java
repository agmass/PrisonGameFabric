package org.agmas.prisongamefabric.mapgame;

public class Sprite {
    public double x = 0;
    public double y = 0;
    public Integer width = 8;
    public Integer height = 8;
    public Byte sampleColor = null;
    public Byte[][] bitmap = null;
    public boolean dither = false;
    public Scene parent;

    public Sprite(Scene parent) {
        this.parent = parent;
    }

    public void update() {}
}

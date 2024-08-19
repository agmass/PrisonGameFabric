package org.agmas.prisongamefabric.mapgame.scenes;

import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.mapgame.sprites.Blueprints;
import org.agmas.prisongamefabric.mapgame.sprites.WardenBootup;
import org.agmas.prisongamefabric.util.Profile;

public class BluePrintsScene extends Scene {

    public Blueprints logoSprite;

    public BluePrintsScene() {
        super();
        logoSprite = new Blueprints(this);
        sprites.add(logoSprite);
    }

    @Override
    public void update() {
        super.update();
    }
}

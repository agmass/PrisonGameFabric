package org.agmas.prisongamefabric.mapgame.scenes;

import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.server.network.ServerPlayerEntity;
import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.mapgame.sprites.WardenBootup;
import org.agmas.prisongamefabric.util.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bootup extends Scene {

    private static final Logger log = LoggerFactory.getLogger(Bootup.class);
    public int timeElapsed = 0;
    public WardenBootup logoSprite;

    public Bootup() {
        super();
        logoSprite = new WardenBootup(this);
        backgroundColor = Blocks.BLACK_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.LOW);
    }

    @Override
    public void update() {
        timeElapsed++;
        if (timeElapsed == 20) {
            sprites.add(logoSprite);
            logoSprite.dither = true;
        }
        if (timeElapsed == 25) {
            logoSprite.dither = false;
        }
        if (timeElapsed == 45) {
            logoSprite.dither = true;
        }
        if (timeElapsed == 50) {
            sprites.remove(logoSprite);
        }
        if (timeElapsed >= 55) {
            Profile p = Profile.getProfile(player);
            p.scene = new Wallpaper();
            p.scene.player = player;
        }
        super.update();
    }
}

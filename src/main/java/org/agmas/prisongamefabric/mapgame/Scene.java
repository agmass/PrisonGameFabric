package org.agmas.prisongamefabric.mapgame;

import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.agmas.prisongamefabric.mapgame.scenes.Bootup;
import org.agmas.prisongamefabric.mapgame.sprites.Hotbar;
import org.agmas.prisongamefabric.mapgame.sprites.MouseCursor;
import org.agmas.prisongamefabric.util.Profile;

import java.util.ArrayList;

public class Scene {
    public ArrayList<Sprite> sprites = new ArrayList<>();
    public double playerMouseX = 0.0;
    public double playerMouseY = 0.0;
    public ServerPlayerEntity player;
    public boolean justEnteredScene = false;
    public boolean justAttacked = false;
    public boolean justRightClicked = false;
    public byte backgroundColor = Blocks.BLACK_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.HIGH);
    public MouseCursor mouseCursor;

    public Scene() {
        mouseCursor = new MouseCursor(this);
    }


    public void update() {
        sprites.forEach(Sprite::update);
        if (justEnteredScene) {
            justEnteredScene = false;
            Profile p = Profile.getProfile(player);
            p.scene = new Bootup();
            p.scene.player = player;
        }
        justAttacked = false;
    }
}

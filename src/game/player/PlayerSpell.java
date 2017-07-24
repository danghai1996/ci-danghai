package game.player;

import game.Utils;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by NHEM on 11/07/2017.
 */
public class PlayerSpell extends GameObject {

    public PlayerSpell() {
        renderer = new ImageRenderer(Utils.loadAssetImage("player-spells/a/1.png"));
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.addUp(0, -20);
    }

}

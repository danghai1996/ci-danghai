package game.player;

import game.Utils;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by NHEM on 11/07/2017.
 */
public class Player {

    //Properties: Thuoc tinh
    public Vector2D position;
    public ImageRenderer imageRenderer;

    public Player(){
        this.position = new Vector2D();
        this.imageRenderer = new ImageRenderer(Utils.loadAssetImage("players/straight/0.png"));
    }

    //Methods: Phuong thuc
    public void move(float dx, float dy) {
        this.position.addUp(dx, dy);
    }

    public void render(Graphics2D g2d) {
        imageRenderer.render(g2d, this.position);
    }
}

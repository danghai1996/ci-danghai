package game.enemies;

import game.Utils;
import game.bases.FrameCounter;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

public class EnemyBullet extends GameObject {

    public Vector2D velocity;

    public EnemyBullet() {
        super();
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("enemies/bullets/white.png"));
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.addUp(velocity);
    }
}

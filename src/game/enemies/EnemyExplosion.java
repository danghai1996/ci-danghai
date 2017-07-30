package game.enemies;

import game.Utils;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.renderers.Animation;

public class EnemyExplosion extends GameObject {
    private Animation animation;

    public EnemyExplosion() {
        super();
        animation = new Animation(
                3,
                false,
                Utils.loadAssetImage("enemies/explosion/0.png"),
                Utils.loadAssetImage("enemies/explosion/2.png"),
                Utils.loadAssetImage("enemies/explosion/4.png"),
                Utils.loadAssetImage("enemies/explosion/6.png")
        );
        this.renderer = animation;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (this.animation.isFinished()) {
            this.isActive = false;
        }
    }

    @Override
    public void refresh() {
        super.refresh();
        animation.reset();
    }
}

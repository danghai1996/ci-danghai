package game.enemies;

import game.Utils;
import game.bases.GameObject;
import game.bases.actions.MoveByAction;
import game.bases.actions.SequenceAction;
import game.bases.actions.WaitAction;
import game.bases.renderers.ImageRenderer;
import game.bases.Vector2D;
import game.scenes.Settings;

public class EnemyBullet extends GameObject {

    public EnemyBullet() {
        super();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("enemies/bullets/white.png"));
    }

    public void config(Vector2D velocity) {
        this.addAction(
                new SequenceAction(
                        new MoveByAction(velocity.multiply(3), 2),
                        new WaitAction(1),
                        new MoveByAction(velocity, 9999)
                )
        );
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (this.position.y > Settings.GAMEPLAY_HEIGHT || this.position.y < 0 || this.position.x > Settings.GAMEPLAY_WIDTH || this.position.x < 0) {
            this.isActive = false;
        }
    }
}

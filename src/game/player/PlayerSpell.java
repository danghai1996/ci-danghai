package game.player;

import game.Utils;
import game.bases.physics.BoxCollider;
import game.bases.GameObject;
import game.bases.renderers.Animation;
import game.bases.renderers.ImageRenderer;
import game.bases.Vector2D;
import game.bases.physics.Physics;
import game.bases.physics.PhysicsBody;
import game.enemies.Enemy;

/**
 * Created by NHEM on 11/07/2017.
 */
public class PlayerSpell extends GameObject implements PhysicsBody {

    private BoxCollider boxCollider;

    public PlayerSpell() {
        super();
        renderer = new Animation(
                Utils.loadAssetImage("player-spells/a/0.png"),
                Utils.loadAssetImage("player-spells/a/1.png"),
                Utils.loadAssetImage("player-spells/a/2.png"),
                Utils.loadAssetImage("player-spells/a/3.png")
        );
        boxCollider = new BoxCollider(20,20);
        children.add(boxCollider);
    }


    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.addUp(0, -10);
        hitEnemy();
        if (this.position.y < 0) {
            this.isActive = false;
        }
    }

    private void hitEnemy() {
        Enemy hitEnemy = Physics.bodyInRect(this.boxCollider, Enemy.class);
        if (hitEnemy != null) {
            hitEnemy.getHit(1);
            this.isActive = false;
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

}

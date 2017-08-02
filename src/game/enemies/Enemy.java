package game.enemies;

import game.Utils;
import game.bases.*;
import game.bases.actions.Action;
import game.bases.actions.RepeatForeverAction;
import game.bases.actions.SequenceAction;
import game.bases.actions.WaitAction;
import game.bases.physics.BoxCollider;
import game.bases.physics.PhysicsBody;
import game.bases.renderers.Animation;
import game.bases.renderers.ImageRenderer;
import game.enemies.shoot.EnemyShootAction;
import game.player.Player;

public class Enemy extends GameObject implements PhysicsBody{

    public Vector2D velocity;
    private BoxCollider boxCollider;

    public Enemy() {
        super();
        this.renderer = new Animation(
                Utils.loadAssetImage("enemies/level0/blue/0.png"),
                Utils.loadAssetImage("enemies/level0/blue/1.png"),
                Utils.loadAssetImage("enemies/level0/blue/2.png"),
                Utils.loadAssetImage("enemies/level0/blue/3.png")
        );
        velocity = new Vector2D();
        this.boxCollider = new BoxCollider(20,20);
        this.children.add(boxCollider);
    }

    public void config() {
        Action shootsequence = new SequenceAction(
                new WaitAction(10),
                new EnemyShootAction()
        );
        this.addAction(new SequenceAction(new RepeatForeverAction(shootsequence)));
    }


    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.velocity.y = 2;
        this.position.addUp(velocity);

    }

    public void getHit(int damage) {
        this.isActive = false;
        EnemyExplosion enemyExplosion = GameObjectPool.recycle(EnemyExplosion.class);
        enemyExplosion.position.set(this.position);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}

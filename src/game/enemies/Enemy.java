package game.enemies;

import game.Utils;
import game.bases.*;
import game.bases.physics.BoxCollider;
import game.bases.physics.PhysicsBody;
import game.bases.renderers.Animation;
import game.bases.renderers.ImageRenderer;
import game.player.Player;

public class Enemy extends GameObject implements PhysicsBody{

    public Vector2D velocity;
    FrameCounter shootCounter;
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
        this.shootCounter = new FrameCounter(5);
        this.boxCollider = new BoxCollider(20,20);

        this.children.add(boxCollider);
    }


    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.velocity.y = 2;
        this.position.addUp(velocity);

        if (shootCounter.run()) {
            this.shootCounter.reset();
            shoot();
        }
    }

    private void shoot() {
        Vector2D target = Player.instance.position;

        Vector2D bulletVelocity = target.subtract(position)
                .normdlize()
                .multiply(4);

        EnemyBullet enemyBullet = GameObjectPool.recycle(EnemyBullet.class);
        enemyBullet.velocity.set(bulletVelocity);
        enemyBullet.position.set(this.position);
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

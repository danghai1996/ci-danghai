package game.enemies;

import game.Utils;
import game.bases.*;
import game.player.Player;

public class Enemy extends GameObject{

    public Vector2D velocity;
    FrameCounter shootCounter;
    BoxCollider boxCollider;

    public Enemy() {
        super();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("enemies/level0/blue/0.png"));
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

        System.out.println(this.boxCollider);
    }

    private void shoot() {
        Vector2D target = Player.instance.position;

        Vector2D bulletVelocity = target.subtract(position)
                .normdlize()
                .multiply(4);

        EnemyBullet enemyBullet = new EnemyBullet();
        enemyBullet.velocity.set(bulletVelocity);
        enemyBullet.position.set(this.position);
        GameObject.add(enemyBullet);
    }
}

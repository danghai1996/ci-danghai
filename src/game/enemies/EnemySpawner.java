package game.enemies;

import game.bases.FrameCounter;
import game.bases.GameObject;
import game.bases.GameObjectPool;
import game.bases.Vector2D;

/**
 * Created by NHEM on 18/07/2017.
 */
public class EnemySpawner extends GameObject {

    private FrameCounter frameCounter = new FrameCounter(10);

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        if (frameCounter.run()) {
            frameCounter.reset();
            Enemy enemy = GameObjectPool.recycle(Enemy.class);
            enemy.position.set(192, 10);
        }
    }
}

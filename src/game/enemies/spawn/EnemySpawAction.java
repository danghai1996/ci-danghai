package game.enemies.spawn;

import game.bases.GameObject;
import game.bases.GameObjectPool;
import game.bases.Vector2D;
import game.bases.actions.Action;
import game.enemies.Enemy;

public class EnemySpawAction implements Action {

    private Vector2D positon;

    public EnemySpawAction(Vector2D positon) {
        this.positon = positon;
    }

    @Override
    public boolean run(GameObject gameObject) {
        Enemy enemy = GameObjectPool.recycle(Enemy.class);
        enemy.position.set(positon);
        enemy.config();
        return true;
    }

    @Override
    public void reset() {

    }
}

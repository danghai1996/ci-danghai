package game.enemies;

import game.bases.FrameCounter;
import game.bases.GameObject;
import game.bases.GameObjectPool;
import game.bases.Vector2D;
import game.bases.actions.SequenceAction;
import game.bases.actions.WaitAction;
import game.enemies.spawn.EnemySpawAction;

/**
 * Created by NHEM on 18/07/2017.
 */
public class EnemySpawner extends GameObject {

    public EnemySpawner() {
        super();
        this.addAction(
                new SequenceAction(
                    new EnemySpawAction(new Vector2D(20, 20)),
                    new EnemySpawAction(new Vector2D(364, 20)),
                    new WaitAction(1000 / 7),
                    new EnemySpawAction(new Vector2D(20, 20)),
                    new EnemySpawAction(new Vector2D(364, 20))
                )
        );
    }
}

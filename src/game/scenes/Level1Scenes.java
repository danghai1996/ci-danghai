package game.scenes;

import game.bases.Contraints;
import game.bases.GameObject;
import game.enemies.EnemySpawner;
import game.inputs.InputManager;
import game.player.Player;

import java.util.Set;

public class Level1Scenes extends Scenes{
    Background background;

    @Override
    public void init() {
        addBackground();
        addPlayer();
        addEnemySpawner();
    }

    private void addBackground() {
        background = new Background();
        background.position.y = Settings.WINDOW_HEIGHT;
        GameObject.add(background);
    }

    private void addEnemySpawner() {
        GameObject.add(new EnemySpawner());
    }

    private void addPlayer() {
        Player player = new Player();
        player.setContraints(new Contraints(20, Settings.WINDOW_HEIGHT, 0, Settings.GAMEPLAY_WIDTH));
        player.setInputManager(InputManager.instance);
        player.position.set(background.getWidth() / 2, Settings.WINDOW_HEIGHT - 50);

        GameObject.add(player);
    }
}

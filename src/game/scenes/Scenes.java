package game.scenes;

import game.bases.GameObject;

public abstract class Scenes {
    public abstract void init();

    public void deInit() {
        GameObject.clear();
    }
}

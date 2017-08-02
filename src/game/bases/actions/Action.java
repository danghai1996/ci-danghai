package game.bases.actions;

import game.bases.GameObject;

public interface Action {
    boolean run(GameObject gameObject);
    void reset();
}

package game.bases;

import java.awt.*;
import java.util.Vector;

/**
 * Created by NHEM on 18/07/2017.
 */
public class GameObject {

    public Vector2D position;           //relative
    public Vector2D screenPosition;     //Screen

    public ImageRenderer renderer;
    public Vector<GameObject> children;

    private static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObjects = new Vector<>();

    public static void add(GameObject gameObject) {
        newGameObjects.add(gameObject);
    }
    public static void renderAll(Graphics2D g2d) {
        for(GameObject gameObject : gameObjects) {
            gameObject.render(g2d);
        }
    }

    public static void runAll() {
        for (GameObject gameObject : gameObjects) {
            gameObject.run(Vector2D.ZERO);
        }
        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();

        for (int i = 0; i < gameObjects.size() - 1; i++) {
            for (int j = i + 1; j < gameObjects.size(); j++) {

            }
        }
    }

    public GameObject() {
        this.position = new Vector2D();
        this.screenPosition = new Vector2D();
        this.children = new Vector<>();
    }

    public void render(Graphics2D g2d) {
        if (renderer != null) {
            renderer.render(g2d, this.position);
        }
    }

    public void run (Vector2D parentPosition) {
        this.screenPosition = parentPosition.add(position);
        for (GameObject child : children) {
            child.run(this.screenPosition);
        }
    }
}

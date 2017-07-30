package game.bases;

import game.bases.physics.Physics;
import game.bases.physics.PhysicsBody;
import game.bases.renderers.Renderer;

import java.awt.*;
import java.util.Vector;

/**
 * Created by NHEM on 18/07/2017.
 */
public class GameObject {

    public Vector2D position;           //relative
    public Vector2D screenPosition;     //Screen

    public boolean isActive;

    public Renderer renderer;
    public Vector<GameObject> children;

    private static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObjects = new Vector<>();

    public static void add(GameObject gameObject) {
        newGameObjects.add(gameObject);
        if (gameObject instanceof PhysicsBody) {
            Physics.add((PhysicsBody) gameObject);
        }
    }



    public static void renderAll(Graphics2D g2d) {
        for(GameObject gameObject : gameObjects) {
            if (gameObject.isActive)
                gameObject.render(g2d);
        }
    }

    public static void runAll() {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.isActive)
                gameObject.run(Vector2D.ZERO);
        }
        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
        //System.out.println(gameObjects.size());
    }

    public GameObject() {
        this.position = new Vector2D();
        this.screenPosition = new Vector2D();
        this.children = new Vector<>();
        this.isActive = true;
    }

    public boolean isActive() {
        return isActive;
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

    public void refresh() {
        isActive = true;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }
}

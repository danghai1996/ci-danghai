package game.bases;

import java.util.Vector;

/**
 * Created by NHEM on 16/07/2017.
 */
public class Vector2D {
    public float x;
    public float y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(){
        this(0, 0);
    }
    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void addUp(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public void addUp(Vector2D other) {
        addUp(other.x, other.y);
    }

    public Vector2D add(float x, float y) {
        return new Vector2D(this.x + x, this.y + y);
    }

    public Vector2D add(Vector2D other) {
        return add(other.x, other.y);
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2D other) {
        set(other.x, other.y);
    }

    public float magnitude() {
        return (float) Math.sqrt(x*x + y*y);
    }

    public void multiplyBy(float n){
        x = this.x * n;
        y = this.y * n;
    }

    public Vector2D normdlize() {
        Vector2D c = new Vector2D();
        c.x = (float) (this.x / (Math.sqrt(x * x + y*y)));
        c.y = (float) (this.y / (Math.sqrt(x * x + y*y)));
        return c;
    }

    public Vector2D copy() {
        Vector2D c = new Vector2D();
        c.x = this.x;
        c.y = this.y;
        return c;
    }

    public Vector2D invert() {
        Vector2D c = new Vector2D();
        c.x = - this.x;
        c.y = - this.y;
        return c;
    }
}


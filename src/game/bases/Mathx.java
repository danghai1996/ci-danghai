package game.bases;

/**
 * Created by NHEM on 16/07/2017.
 */
public class Mathx {
    public static float clamp (float x, float min, float max) {
        if (x > max) {
            return max;
        }else if (x < min){
            return min;
        }else {
            return x;
        }
    }
}

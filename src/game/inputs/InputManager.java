package game.inputs;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Created by NHEM on 18/07/2017.
 */
public class InputManager {
    public boolean rightPressed;
    public boolean leftPressed;
    public boolean upPressed;
    public boolean downPressed;
    public boolean xPressed;

    public static final InputManager instance = new InputManager();

    private List<InputListener>  inputListeners;

    public void register(InputListener inputListener) {
        this.inputListeners.add(inputListener);
    }

    public void unregister(InputListener inputListener) {
        inputListeners.remove(inputListener);
    }

    private InputManager(){
        inputListeners = new ArrayList<>();
    }

    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                break;
            case KeyEvent.VK_UP:
                upPressed = true;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = true;
                break;
            case KeyEvent.VK_X:
                xPressed = true;
                break;
        }

        for (InputListener inputListener : inputListeners) {
            inputListener.onKeyPressed(keyEvent.getKeyCode());
        }
    }

    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                leftPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                break;
            case KeyEvent.VK_UP:
                upPressed = false;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = false;
                break;
            case KeyEvent.VK_X:
                xPressed = false;
                break;
        }

        Iterator<InputListener> iterator = inputListeners.iterator();
        while (iterator.hasNext()) {
            InputListener inputListener = iterator.next();
            if (inputListener.onKeyReleased(keyEvent.getKeyCode())) {
                iterator.remove();
            }
        }
    }

}

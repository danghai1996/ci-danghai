package game.player;

import game.Utils;
import game.bases.*;
import game.inputs.InputManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by NHEM on 11/07/2017.
 */
public class Player extends GameObject {

    Contraints contraints;
    InputManager inputManager;

    FrameCounter coolDownCounter;
    boolean spellDisabled;
    Vector2D velocity;

    public Player(){
        this.velocity = new Vector2D();
        this.coolDownCounter = new FrameCounter(17); // 17 = 300 miliseconds to cooldown
        this.renderer = new ImageRenderer(Utils.loadAssetImage("players/straight/0.png"));
    }

    @Override
    public void run() {
        move();
        castSpell();
    }

    private void castSpell() {
        if (inputManager.xPressed) {
            PlayerSpell playerSpell = new PlayerSpell();
            playerSpell.position.set(this.position.add(0, -20));
            GameObject.add(playerSpell);
        }
    }

    private void move() {
        this.velocity.set(0, 0);

        if (inputManager.leftPressed)
            this.velocity.x -= 10;
        if (inputManager.rightPressed)
            this.velocity.x += 10;
        if (inputManager.upPressed)
            this.velocity.y -= 10;
        if (inputManager.downPressed)
            this.velocity.y += 10;

        this.position.addUp(velocity);
        this.contraints.make(this.position);
    }

    public void castSpell(ArrayList<PlayerSpell> playerSpells) {
        //cast spell
        if (!spellDisabled) {
            PlayerSpell playerSpell = new PlayerSpell();
            playerSpell.position.set(this.position.add(0, -20));
            playerSpells.add(playerSpell);

            spellDisabled = true;
        }
    }

    public void coolDown() {
        //cooldown
        if (spellDisabled){
            boolean status = coolDownCounter.run();
            if (status) {
                spellDisabled = false;
                coolDownCounter.reset();
            }
        }
    }

    //setter
    public void setContraints(Contraints contraints) {
        this.contraints = contraints;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }
}

package game;

import game.bases.Contraints;
import game.bases.GameObject;
import game.enemies.Enemy;
import game.enemies.EnemySpawner;
import game.inputs.InputManager;
import game.player.Player;
import game.player.PlayerSpell;
import game.scenes.Background;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by NHEM on 09/07/2017.
 */
public class GameWindow extends JFrame {

    Background background;
    BufferedImage backBufferImage;
    Graphics2D backBufferGraphics2D;


    InputManager inputManager = new InputManager();

    public GameWindow() {
        setupWindow();

        addBackground();
        addPlayer();
        addEnemySpawner();


        backBufferImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphics2D = (Graphics2D) backBufferImage.getGraphics();

        setupInput();
        this.setVisible(true);
    }

    private void addBackground() {
        background = new Background();
        background.position.y = this.getHeight();
        GameObject.add(background);
    }

    private void addEnemySpawner() {
        GameObject.add(new EnemySpawner());
    }

    private void addPlayer() {
        Player player = new Player();
        player.setContraints(new Contraints(20, this.getHeight(), 0, background.getWidth()));
        player.setInputManager(inputManager);
        player.position.set(background.getWidth() / 2, this.getHeight() - 50);

        GameObject.add(player);
    }

    private void setupInput() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyReleased(e);
            }
        });
    }

    long lastUpdateTime;
    public void loop() {
        while(true) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastUpdateTime > 17) {
                lastUpdateTime = currentTime;
                run();
                render();
            }
        }
    }

    public void run() {
        GameObject.runAll();
    }

    public void render() {
        backBufferGraphics2D.setColor(Color.BLACK);
        backBufferGraphics2D.fillRect(0, 0 ,this.getWidth(), this.getHeight());

        GameObject.renderAll(backBufferGraphics2D);

        Graphics2D g2d = (Graphics2D)this.getGraphics();

        g2d.drawImage(backBufferImage, 0, 0, null);
    }

    private void setupWindow() {
        this.setSize(600, 600);
        this.setResizable(false);
        this.setTitle("Tohou - Remade by Nhem");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //System.out.println("Closing");
                System.exit(0);
                //super.windowClosing(e);
            }
        });
    }

}

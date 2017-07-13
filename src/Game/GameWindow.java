package game;

import game.player.Player;
import game.player.PlayerSpell;

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

    private BufferedImage background;

    private BufferedImage spells;
    private int spellsX;
    private int spellsY;

    private int backgroundY ;

    boolean rightPressed;
    boolean leftPressed;
    boolean upPressed;
    boolean downPressed;
    boolean xPressed;

    BufferedImage backBufferImage;
    Graphics2D backBufferGraphics2D;

    Player player = new Player();
    ArrayList <PlayerSpell> playerSpells = new ArrayList<>();

    public GameWindow() {
        setupWindow();
        loadImage();

        backgroundY = this.getHeight() - background.getHeight();
        player.x = background.getWidth() / 2;
        player.y = this.getHeight() - 100 ;
        backBufferImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphics2D = (Graphics2D) backBufferImage.getGraphics();

        setupInput();
        this.setVisible(true);
    }

    private void setupInput() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        rightPressed = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        leftPressed = true;
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
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        rightPressed = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        leftPressed = false;
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
                    default:
                        break;
                }
            }
        });
    }

    public void loop() {
        while(true) {
            try {
                Thread.sleep(17);
                run();
                render();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        if (backgroundY < 0) {
            backgroundY++;
        }

        int dx = 0;
        int dy = 0;

        if (rightPressed) {
            dx += 5;
        }
        if (leftPressed) {
            dx += -5;
        }
        if (upPressed) {
            dy += -5;
        }
        if (downPressed) {
            dy += 5;
        }
        if (xPressed) {
            //Creat new
            PlayerSpell playerSpell = new PlayerSpell();

            //Config
            playerSpell.x = player.x;
            playerSpell.y = player.y;
            try {
                playerSpell.image = Utils.loadAssetImage("players/straight/0.png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            playerSpells.add(playerSpell);
        }
        player.move(dx, dy);
        for (PlayerSpell playerSpell : playerSpells){
            playerSpell.move();
        }
    }

    public void render() {
        backBufferGraphics2D.setColor(Color.BLACK);
        backBufferGraphics2D.fillRect(0, 0 ,this.getWidth(), this.getHeight());

        backBufferGraphics2D.drawImage(background, 0, backgroundY, null);
        player.render(backBufferGraphics2D);
        for (PlayerSpell playerSpell : playerSpells){
            playerSpell.render(backBufferGraphics2D);
        }
        backBufferGraphics2D.drawImage(spells, spellsX, spellsY, null );

        Graphics2D g2d = (Graphics2D)this.getGraphics();

        g2d.drawImage(backBufferImage, 0, 0, null);
    }

    private void loadImage() {
        try {
            background = Utils.loadAssetImage( "background/0.png");
            player.image = Utils.loadAssetImage("players/straight/0.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
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

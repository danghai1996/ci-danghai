package game.bases.renderers;

import game.Utils;
import game.bases.FrameCounter;
import game.bases.Vector2D;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Arrays;

public class Animation implements Renderer {

    private List<BufferedImage> images;
    private int imageIndex;
    private FrameCounter frameCounter;
    private boolean finished;
    private boolean repeat;

    public Animation(int delayFrame, boolean repeat, BufferedImage... imagesArr) {
        this.images = Arrays.asList(imagesArr);
        frameCounter = new FrameCounter(delayFrame);
        this.repeat = repeat;
    }

    public Animation(BufferedImage... imagesArr) {
        this(3, true, imagesArr);
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public void render(Graphics g, Vector2D postion) {
        if (frameCounter.run()) {
            changeIndex();
            frameCounter.reset();
        }
        BufferedImage image = images.get(imageIndex);
        g.drawImage(image,
                (int) (postion.x - image.getWidth() / 2),
                (int) (postion.y - image.getHeight() / 2),
                null);
    }

    private void changeIndex() {
        if (imageIndex >= images.size() - 1) {
            if (repeat) {
                imageIndex = 0;
            }
            finished = true;
        } else {
            imageIndex++;
        }
    }

    public void reset() {
        imageIndex = 0;
        finished = false;
    }
}

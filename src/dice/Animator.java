package dice;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animator {
    public BufferedImage sprite;
    ArrayList<BufferedImage> frames;
    private volatile boolean running = false;
    private long previousTime, speed;
    private int frameAtPause, currentFrame;

    public Animator(ArrayList<BufferedImage> frames) {
        this.frames = frames;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void update(long time) {
        if (running) {
            if (time - previousTime >= speed) {
                currentFrame++;
                try {
                    sprite = frames.get(currentFrame);
                } catch (IndexOutOfBoundsException e) {
                    currentFrame = 0;
                    sprite = frames.get(currentFrame);
                }
                previousTime = time;
            }
        }
    }

    public void start() {
        running = true;
        previousTime = 0;
        frameAtPause = 0;
        currentFrame = 0;
    }

    public void stop() {
        running = false;
        previousTime = 0;
        frameAtPause = 0;
        currentFrame = 0;
    }

    public void pause() {
        frameAtPause = currentFrame;
        running = false;
    }

    public void resume() {
        currentFrame = frameAtPause;
        running = true;
    }
}

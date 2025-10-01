package entities;

import static utils.Constants.*;

public class Paddle extends Entity implements Movable {
    private int speed;
    private int angle; // not use
    private int width;
    private int height;
    public void move() {
        if (x >= 0 && x <= WINDOW_WIDTH - width) {
            this.x += speed;
        }
    }
    public void setAngle(int a) {
        // not use
    }
    public void setSpeed(int s) {
        this.speed = s;
    }
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public int getAngle() {
        return this.angle;
    }

    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }

}
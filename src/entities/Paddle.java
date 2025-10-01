package entities;
public class Paddle extends Entity implements Movable {
    private int velocityX;
    private int velocityY;
    private int width;
    private int height;
    public void move() {
        if (x>=0 && x <= WINDOW_WIDTH - width) {
            this.x += velocityX;
        }
    }

    public void setVelocity(int velocityX, int velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }
    public int getVelocityX() {
        return this.velocityX;
    }
    public int getVelocityY() {
        return this.velocityY;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }

}
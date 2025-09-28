package entities;
public class Paddle extends Entity implements Movable {
    private int velocityX;
    private int velocityY;
    private int x;
    private int y;
    public void move() {
        this.x += velocityX;
        this.y += velocityY;
    }

    public void setVelocity(int velocityX, int velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

}
package entities;
public class PowerUp extends Entity implements Movable {
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
        return this.velocityX;
    }
    public int getVelocityY() {
        return this.velocityY;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
}
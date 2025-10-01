package entities;
public class Ball extends Entity implements Movable {
    private int velocityX;
    private int velocityY;
    private int r; // radius
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

    public boolean Die() {
        return this.y + 2 * r > WINDOW_HEIGHT;
    }

    public boolean collisionWithWall() {
        if (x <= WINDOW_WIDTH || x >= WINDOW_WIDTH - 2 * r) {
            this.velocityX = - this.velocityX;
        } else if (y <= 0) {
            this.velocityY = - this.velocityY;
        }
    }

}
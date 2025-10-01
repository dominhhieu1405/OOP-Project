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
    public int getR() {
        return this.r;
    }

    public boolean Die() {
        return this.y + 2 * r > WINDOW_HEIGHT;
    }

    public void collisionWithWall() {
        if (x <= WINDOW_WIDTH || x >= WINDOW_WIDTH - 2 * r) {
            this.velocityX = - this.velocityX;
        } else if (y <= 0) {
            this.velocityY = - this.velocityY;
        }
    }

    public void collisionWithPaddle(Paddle p) {
        if (y + 2 * r == p.getY()) && (x >= p.getX() && x <= p.getX() - 2 * r) {
            this.velocityY = - this.velocityY;
        }
    }

}
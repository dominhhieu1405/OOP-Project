package entity.powerUp;

import entity.Paddle;

public class PowerUpPaddleExpand extends PowerUp {
    // Constructor
    public PowerUpPaddleExpand(int x, int y) {
        super(x, y, 36, 36, "assets/images/PowerUp/PaddleExpand.png");
        this.duration = 10000;
    }

    // Khi paddle hung duoc PowerUp
    @Override
    public void activate() {
        // Lay paddle hien tai
        Paddle paddle = Paddle.getInstance();
        // Set chieu dai paddle moi
        paddle.setWidth(paddle.getWidth() + 50);
        // Danh dau la Power kich hoat
        isActive = true;
        // Thoi gian bat dau
        startTime = System.currentTimeMillis();
    }

    @Override
    public void deactivate() {
        // Lay paddle hien tai
        Paddle paddle = Paddle.getInstance();
        // Tra ve chieu dai ban dau
        paddle.setWidth(paddle.getWidth() - 50);
        // Danh dau PowerUp het hieu luc
        isActive = false;
    }
}

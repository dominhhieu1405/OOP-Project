package entity;

public class PowerUpPaddleSlow extends PowerUp {

    // Constructor
    public PowerUpPaddleSlow(int x, int y) {
        super(x, y, 36, 36, "assets/images/PowerUp/PaddleSlow.png");
        this.duration = 10000;
    }

    // Khi paddle hung duoc PowerUp
    @Override
    public void activate() {
        Paddle paddle = Paddle.getInstance();
        paddle.setSpeed(paddle.getSpeed() * 0.8);
        isActive = true;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void deactivate() {
        Paddle paddle = Paddle.getInstance();
        paddle.setSpeed(paddle.getSpeed() / 0.8);
        isActive = false;
    }
}

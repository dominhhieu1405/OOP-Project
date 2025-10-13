package entity;

public class PowerUpPaddleFast extends PowerUp {

    // Constructor
    public PowerUpPaddleFast(int x, int y) {
        super(x, y, 36, 36, "assets/images/PowerUp/PaddleFast.png");
        this.duration = 10000;
    }

    // Khi paddle hung duoc PowerUp
    @Override
    public void activate() {
        Paddle paddle = Paddle.getInstance();
        paddle.setSpeed(paddle.getSpeed() * 1.5);
        isActive = true;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void deactivate() {
        Paddle paddle = Paddle.getInstance();
        paddle.setSpeed(paddle.getSpeed() / 1.5);
        isActive = false;
    }
}

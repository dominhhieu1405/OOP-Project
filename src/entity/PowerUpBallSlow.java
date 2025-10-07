package entity;

public class PowerUpBallSlow extends PowerUp {
    public PowerUpBallSlow(int x, int y) {
        super(x, y, 36, 36, "assets/images/PowerUp/BallSlow.png");
        this.duration = 10000; // hiệu lực 10 giây
    }

    @Override
    public void activate() {
        Ball ball = Ball.getInstance();
        ball.setSpeed(ball.getSpeed() * 0.8); // Giảm tốc độ bóng
        isActive = true;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void deactivate() {
        Ball ball = Ball.getInstance();
        ball.setSpeed(ball.getSpeed() / 0.8); // Phục hồi tốc độ bóng
        isActive = false;
    }
}

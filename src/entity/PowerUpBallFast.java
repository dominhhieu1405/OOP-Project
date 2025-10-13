package entity;

public class PowerUpBallFast extends PowerUp {
    public PowerUpBallFast(int x, int y) {
        super(x, y, 36, 36, "assets/images/PowerUp/BallFast.png");
        this.duration = 10000; // hiệu lực 10 giây
    }

    @Override
    public void activate() {
        Ball ball = Ball.getInstance();
        ball.setSpeed(ball.getSpeed() * 1.5); // Tăng tốc độ bóng lên 1.5 lần
        isActive = true;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void deactivate() {
        Ball ball = Ball.getInstance();
        ball.setSpeed(ball.getSpeed() / 1.5); // Trả về tốc độ ban đầu
        isActive = false;
    }
}

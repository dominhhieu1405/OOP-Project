package entity.powerUp;

import entity.Ball;

public class PowerUpBallFire extends PowerUp {
    public PowerUpBallFire(int x, int y) {
        super(x, y, 36, 36, "assets/images/PowerUp/BallFire.png");
        this.duration = 10000; // hiệu lực 10 giây
    }

    @Override
    public void activate() {
        Ball ball = Ball.getInstance();
        ball.setFire(true);
        isActive = true;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void deactivate() {
        Ball ball = Ball.getInstance();
        ball.setFire(false);
        isActive = false;
    }
}

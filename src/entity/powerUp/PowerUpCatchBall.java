package entity.powerUp;

import entity.Ball;

public class PowerUpCatchBall extends PowerUp {

    // Constructor
    public PowerUpCatchBall(int x, int y) {
        super(x, y, 36, 36, "assets/images/PowerUp/CatchBall.png");
        this.duration = 0;
    }

    // Khi paddle hung duoc PowerUp
    @Override
    public void activate() {
        Ball ball = Ball.getInstance();
        ball.reset();
    }

    @Override
    public void deactivate() {
        return;
    }
}

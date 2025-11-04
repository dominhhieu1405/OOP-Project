package entity.powerUp;

import Constant.Constant;
import entity.Ball;

public class PowerUpBallExtraLife extends PowerUp {
    public PowerUpBallExtraLife(int x, int y) {
        super(x, y, 36, 36, "assets/images/PowerUp/BallExtraLife.png");
        this.duration = 0; // hiệu lực 0 giây
    }

    @Override
    public void activate() {
        Ball ball = Ball.getInstance();
        if (ball.getIsAlive() && ball.health < Constant.TOTAL_BALL_HEART) {
            ball.health += 1;
            isActive = true;
        }
    }

    @Override
    public void deactivate() {
        isActive = false;
    }

}

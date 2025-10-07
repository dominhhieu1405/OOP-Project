package entity;

import Constant.Constant;

public class PowerUpExtraLife extends PowerUp {
    public PowerUpExtraLife(int x, int y) {
        super(x, y, 30, 30, "assets/images/PowerUpHealth.png");
        this.duration = 0; // hiệu lực 10 giây
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

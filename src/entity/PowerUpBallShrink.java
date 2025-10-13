package entity;

public class PowerUpBallShrink extends PowerUp {
    public PowerUpBallShrink(int x, int y) {
        super(x, y, 36, 36, "assets/images/PowerUp/BallShrink.png");
        this.duration = 10000; // hiệu lực 10 giây
    }

    @Override
    public void activate() {
        Ball ball = Ball.getInstance();

        ball.setRADIUS(Math.max(ball.getRADIUS() - 2, 1));

        isActive = true;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void deactivate() {
        Ball ball = Ball.getInstance();

        ball.setRADIUS(ball.getRADIUS() + 2);

        isActive = false;
    }
}

package entity;

public class PowerUpBallExpand extends PowerUp {
    public PowerUpBallExpand(int x, int y) {
        super(x, y, 36, 36, "assets/images/PowerUp/BallExpand.png");
        this.duration = 10000; // hiệu lực 10 giây
    }

    @Override
    public void activate() {
        Ball ball = Ball.getInstance();

        ball.setRADIUS(ball.getRADIUS() + 5);

        isActive = true;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void deactivate() {
        Ball ball = Ball.getInstance();

        ball.setRADIUS(ball.getRADIUS() - 5);

        isActive = false;
    }
}

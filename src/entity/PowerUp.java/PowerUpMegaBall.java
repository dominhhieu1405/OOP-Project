package entity;

public class PowerUpMegaBall extends PowerUp {
    public PowerUpMegaBall(int x, int y) {
        super(x, y, 30, 30, "assets/images/powerup_shrinkball.png");
        this.duration = 10000; // hiệu lực 10 giây
    }

    @Override
    public void activate() {
        Ball ball = Ball.getInstance();

        // Tang 5 pixel
        ball.setWidth(Math.max(5, ball.getWidth() + 5));
        ball.setHeight(Math.max(5, ball.getHeight() + 5));

        isActive = true;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void deactivate() {
        Ball ball = Ball.getInstance();

        // Khôi phục lại kích thước ban đầu
        ball.setWidth(ball.getWidth() - 5);
        ball.setHeight(ball.getHeight() - 5);

        isActive = false;
    }
}

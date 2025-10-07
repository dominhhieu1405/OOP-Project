package entity;

public class PowerUpShrinkBall extends PowerUp {
    public PowerUpShrinkBall(int x, int y) {
        super(x, y, 30, 30, "assets/images/SmallBall.png");
        this.duration = 10000; // hiệu lực 10 giây
    }

    @Override
    public void activate() {
        Ball ball = Ball.getInstance();

        // Tru co dinh 5 pixel
        ball.setWidth(Math.max(5, ball.getWidth() - 5));
        ball.setHeight(Math.max(5, ball.getHeight() - 5));
        isActive = true;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void deactivate() {
        Ball ball = Ball.getInstance();

        // Khôi phục lại kích thước ban đầu
        ball.setWidth(ball.getWidth() + 5);
        ball.setHeight(ball.getHeight() + 5);

        isActive = false;
    }
}

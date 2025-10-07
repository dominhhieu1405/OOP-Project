package entity;

public class PowerUpExpand extends PowerUp {
    // Constructor
    public PowerUpExpand(int x, int y) {
        super(x, y, 30, 30, "assets/images/PowerUpExpand.png");
        this.duration = 10000;
    }

    // Khi paddle hung duoc PowerUp
    @Override
    public void activate() {
        // Lay paddle hien tai
        Paddle paddle = Paddle.getInstance();
        // Set chieu dai paddle moi
        paddle.setWidth(paddle.getWidth() + 50);
        // Danh dau la Power kich hoat
        isActive = true;
        // Thoi gian bat dau
        startTime = System.currentTimeMillis();
    }

    @Override
    public void deactivate() {
        // Lay paddle hien tai
        Paddle paddle = Paddle.getInstance();
        // Tra ve chieu dai ban dau
        paddle.setWidth(paddle.getWidth() - 50);
        // Danh dau PowerUp het hieu luc
        isActive = false;
    }
}

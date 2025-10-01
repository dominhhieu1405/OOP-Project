package entities;

public class Brick extends Entity implements Breakable {
    private int hitPoint;
    private boolean destroyed;
    private double powerUpDropChance;
    private int scoreValue;

    @Override
    public boolean hit();

    @Override
    public boolean isDestroyed();

    /**
     * Xử lí khi bị hit.
     */
    public void onHit();

    /**
     * Xử lí khi bị phá hủy (Bao nhiêu điểm , có rơi ra vật phẩm gì không , âm thanh
     * khi bị phá hủy)
     */
    public void onDestroyed();

    /**
     * Xử lí va chạm với bóng
     */
    public boolean checkCollisionWithBall(Ball ball);
}
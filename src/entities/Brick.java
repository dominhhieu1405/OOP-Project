package entities;

import javax.swing.*;
import java.awt.*;
import assets.Sprite;

public class Brick extends Block implements Breakable {
    private static final int width = 75;
    private static final int height = 30;
    private int hitPoint;
    private boolean destroyed;
    private double powerUpDropChance;
    private int scoreValue;
    private Image brickImage;

    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        this.HP = 2; // Brick cần 2 hit để phá hủy
    }
    public void destroy() {
        
    }

    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
        this.hitPoint = DEFAULT_HIT_POINTS;
        this.powerUpDropChance = DEFAULT_POWERUP_CHANCE;
        this.scoreValue = DEFAULT_SCORE_VALUE;
        this.destroyed = false;
    }

    public Brick() {
        brickImage = new ImageIcon("src/resources/brick.png").getImage();
    }

    @Override
    public boolean hit() {
        hitPoint--;
        if (hitPoint <= 0) {
            destroyed = true;
            onDestroyed();
            return true;
        }
        return false;
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    /**
     * Vẽ gạch lên màn hình
     */
    public void render(Graphics g) {
        if (!destroyed) {
            g.drawImage(brickImage, x, y, width, height, null);
        }
    }

    /**
     * Xử lí khi bị hit.
     */
    public void onHit() {
        // Cập nhật trạng thái gạch (Màu sắc , hình ảnh)
        // Phát âm thanh
    }

    /**
     * Xử lí khi bị phá hủy (Bao nhiêu điểm , có rơi ra vật phẩm gì không , âm thanh
     * khi bị phá hủy)
     */
    public void onDestroyed() {
        // Tăng điểm
        // Kiểm tra rơi vật phẩm
    }
}
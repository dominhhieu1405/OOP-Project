package entity;

import Constant.Constant;
import manager.BlockManager;

import java.awt.*;

public class BlockBomb extends Block {

    private int damage = 1; // Sát thương khi nổ
    private int radius = 100; // Bán kính nổ, tính từ tâm block
    private long explodeTick; // Thời gian nổ sau khi block vỡ.
    private boolean isExploded = false; // Đã nổ chưa
    private boolean animation = false; // Hiệu ứng nổ đang diễn ra

    /**
     * Constructor.
     * @param x x
     * @param y y
     * @param width chiều rộng
     * @param height chiều cao
     * @param HP số máu
     * @param damage sát thương
     * @param radius bán kính nổ
     */
    public BlockBomb(int x, int y, int width, int height, int HP, int damage, int radius) {
        super(x, y, width, height, HP);
        this.img = Constant.BLOCK_BOMB_IMG;
        this.radius = radius;
        this.damage = damage;
    }

    /**
     * Constructor.
     * @param x x
     * @param y y
     * @param width chiều rộng
     * @param height chiều cao
     * @param HP số máu
     * @param damage sát thương
     */
    public BlockBomb(int x, int y, int width, int height, int HP, int damage) {
        super(x, y, width, height, HP);
        this.img = Constant.BLOCK_BOMB_IMG;
        this.damage = damage;
    }

    /**
     * Constructor.
     * @param x x
     * @param y y
     * @param HP số máu
     * @param damage sát thương
     */
    public BlockBomb(int x, int y, int HP, int damage) {
        super(x, y, HP);
        this.img = Constant.BLOCK_BOMB_IMG;
        this.damage = damage;
    }

    /**
     * Constructor.
     * @param x x
     * @param y y
     */
    public BlockBomb(int x, int y) {
        super(x, y);
        this.img = Constant.BLOCK_BOMB_IMG;
    }

    /**
     * Lấy sát thương khi nổ.
     * @return sát thương
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Đặt sát thương khi nổ.
     * @param damage sát thương
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Lấy bán kính nổ.
     * @return bán kính nổ
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Đặt bán kính nổ.
     * @param radius bán kính nổ
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * Ghi đè hàm die để thêm trạng thái nổ.
     */
    @Override
    public void die() {
        super.die();
        explodeTick = System.currentTimeMillis();
        animation = true;
        // System.out.println("Bom no: " + explodeTick);
    }

    /**
     * Nổ.
     */
    public void exploded() {
        BlockManager bm = BlockManager.getInstance();
        for (Block b : bm.getBlocks()) {
            if (b != this && b.isAlive() && checkBombEffect(b)) {
                b.decreaseHP(damage);
            }
        }
        this.isExploded = true;
    }

    /**
     * Ghi đè hàm render để thêm hiệu ứng động nổ.
     * @param g Graphics
     */
    @Override
    public void render(Graphics g) {
        if (!isAlive() && animation) {
            super.render(g);
            long currentTick = System.currentTimeMillis();
            if (currentTick - explodeTick < 500) {
                // Draw explosion effect
                int alpha = (int) (255 * (1 - (double)(currentTick - explodeTick) / 500));
                int eRadius = (int) (this.radius * ((double)(currentTick - explodeTick) / 500));
                g.setColor(new Color(255, 255, 255, alpha));
                g.fillOval(this.getX() + this.getWidth()/2 - eRadius, this.getY() + this.getHeight()/2 - eRadius, eRadius*2, eRadius*2);
            } else {
                if (!isExploded) {
                    exploded();
                }
                animation = false;
            }
        } else if (isAlive()) {
            super.render(g);
            // System.out.println("Bomb at " + this.getX() + " " + this.getY() + " is alive.");
        }
    }

    /**
     * Giới hạn giá trị trong khoảng min và max.
     * @param value giá trị cần giới hạn
     * @param min giá trị nhỏ nhất
     * @param max giá trị lớn nhất
     * @return giá trị đã được giới hạn
     */
    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * Kiểm tra xem block có nằm trong phạm vi nổ hay không.
     * @param b block cần kiểm tra
     * @return true nếu block nằm trong phạm vi nổ, false nếu không
     */
    public boolean checkBombEffect(Block b) {
        double closestX = clamp(this.getX(), b.getX(), b.getX() + b.getWidth());
        double closestY = clamp(this.getY(), b.getY(), b.getY() + b.getHeight());

        double pX = this.getX() + (double) this.getWidth() / 2;
        double pY = this.getY() + (double) this.getHeight() / 2;

        double distanceX = pX - closestX;
        double distanceY = pY - closestY;
        double distanceSquared = (distanceX * distanceX) + (distanceY * distanceY);

        return distanceSquared < (radius * radius);
    }

}

package entity;

import Constant.Constant;
import manager.PowerUpManager;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public abstract class PowerUp extends Entity {
    protected boolean isActive = false;
    protected long startTime;
    protected long duration = 10000;
    protected int fallSpeed = 100; // Tốc độ rơi (pixel/giây)
    private long lastTime; // Thời gian lần cuối cập nhật vị trí
    private double posY; // Vị trí Y thực tế (có thể là số thập phân)

    // Constructor co tham so ke thua tu Entity
    public PowerUp(int x, int y, int width, int height, String imgPath) {
        super(x, y, width, height);
        this.posY = y;
        lastTime = System.currentTimeMillis();
        this.img = new ImageIcon(imgPath).getImage();
    }

    // PowerUp roi xuong
    public void update() {
        double dt = (System.currentTimeMillis() - lastTime) / 1000.0;
        lastTime = System.currentTimeMillis();
        posY += fallSpeed * dt;
        y = (int) Math.round(posY);


        if (this.isCollision(Paddle.getInstance())) {
            this.activate();
//            PowerUpManager.getInstance().removePowerUp(this);
        }

    }

    // Vẽ PowerUp
    public void render(Graphics g) {
        g.drawImage(img, x, y, width, height, null);
    }

    // Paddle hung duoc PowerUp
    public abstract void activate();

    // Khi hết thời gian hiệu lực
    public abstract void deactivate();

    public boolean isExpired() {
        return isActive && (System.currentTimeMillis() - startTime > duration);
    }

    public boolean isActive() {
        return isActive;
    }

}

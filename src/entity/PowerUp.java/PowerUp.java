package entity;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public abstract class PowerUp extends Entity {
    protected boolean isActive = false;
    protected long startTime;
    protected long duration = 10000;
    protected double fallSpeed = 150.0;

    // Constructor co tham so ke thua tu Entity
    public PowerUp(int x, int y, int width, int height, String imgPath) {
        super(x, y, width, height);
        this.img = new ImageIcon(imgPath).getImage();
    }

    // PowerUp roi xuong
    public void update(double dt) {
        y += fallSpeed;
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

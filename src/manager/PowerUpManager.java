package manager;

import Constant.Constant;
import entity.*;

import java.awt.*;
import java.util.ArrayList;

public class PowerUpManager {
    private static PowerUpManager instance; // Singleton instance
    public ArrayList<PowerUp> list; // Danh sách powerup
    public ArrayList<PowerUp> toRemove; // Danh sách powerup cần xoá

    /**
     * Constructor.
     */
    public PowerUpManager() {
        list = new ArrayList<>();
        toRemove = new ArrayList<>();
    }

    /**
     * Lấy instance của PowerUpManager (Singleton pattern).
     * @return Instance của PowerUpManager.
     */
    public static PowerUpManager getInstance() {
        if (instance == null) {
            instance = new PowerUpManager();
        }
        return instance;
    }

    /**
     * Reset danh sách powerup.
     */
    public void reset() {
        for (PowerUp powerUp : list) {
            powerUp.deactivate();
        }

        list.clear();
        toRemove.clear();
    }

    /**
     * Thêm một powerup vào danh sách.
     * @param p PowerUp cần thêm.
     */
    public void addPowerUp(PowerUp p) {
        list.add(p);
    }

    /**
     * Lấy danh sách powerup.
     * @return Danh sách powerup.
     */
    public ArrayList<PowerUp> getPowerUps() {
        return list;
    }

    /**
     * Xoá một powerup khỏi danh sách.
     * @param p PowerUp cần xoá.
     */
    public void removePowerUp(PowerUp p) {
        list.remove(p);
    }

    public void update() {
        for (PowerUp p : list) {
            if (!p.isActive()) {
                p.update();
                if (p.getY() > Constant.FRAME_HEIGHT) {
                    toRemove.add(p);
                }
            } else if (p.isExpired()) {
                System.out.println("Power-up expired: " + p.toString());
                p.deactivate();
                toRemove.add(p);
            }
        }
        list.removeAll(toRemove);
        toRemove.clear();
    }

    /**
     * Vẽ tất cả các powerup.
     * @param g Graphics
     */
    public void render(Graphics g) {
        for (PowerUp p : list) {
            if (!p.isActive()) {
                p.render(g);
            }
        }
    }

}

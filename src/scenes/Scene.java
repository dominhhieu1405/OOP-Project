// abstract class đại diện cho một cảnh trong trò chơi (menu, chơi, tạm dừng, v.v.)
package scenes;

import java.awt.Graphics2D;

public abstract class Scene {
    
    // Cập nhật logic của scene
    public abstract void update();
    
    // Vẽ scene lên màn hình
    public abstract void render(Graphics2D g2d);
    
    // Được gọi khi scene được kích hoạt
    public void onEnter() {
        // Override nếu cần
    }
    
    // Được gọi khi scene bị thoát
    public void onExit() {
        // Override nếu cần
    }
}

// abstract class đại diện cho một cảnh trong trò chơi (menu, chơi, tạm dừng, v.v.)
package scenes;

import java.awt.Graphics2D;
import core.InputHandler;

public abstract class Scene {
    
    // Cập nhật logic của scene
    public abstract void update(InputHandler inputHandlers);
    
    // Vẽ scene lên màn hình
    public abstract void render(Graphics2D g2d);
    
}

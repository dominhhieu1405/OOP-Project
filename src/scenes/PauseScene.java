package scenes;
import core.InputHandler;

// màn hình tạm dừng của trò chơi

public class PauseScene extends Scene {
    public void update(InputHandler inputHandler) {
        // cập nhật logic tạm dừng (nếu cần)
    }
    public void render(java.awt.Graphics2D g2d) {
        // vẽ overlay tạm dừng
        g2d.setColor(new java.awt.Color(0, 0, 0, 150)); // màu đen bán trong suốt
        g2d.fillRect(0, 0, 800, 600); //
        g2d.setColor(java.awt.Color.WHITE);
        g2d.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 48));
        g2d.drawString("PAUSED", 300, 300);
    }
}

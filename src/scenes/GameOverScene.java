package scenes;

// màn hình kết thúc trò chơi

public class GameOverScene extends Scene {
    public void update() {
        // cập nhật logic kết thúc trò chơi (nếu cần)
    }
    public void render(java.awt.Graphics2D g2d) {
        //
        g2d.setColor(java.awt.Color.BLACK);
        g2d.fillRect(0, 0, 800, 600); //
        g2d.setColor(java.awt.Color.RED);
        g2d.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 48));
        g2d.drawString("GAME OVER", 280, 300);
    }
}
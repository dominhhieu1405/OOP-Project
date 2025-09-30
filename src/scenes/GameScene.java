package scenes;
import java.awt.*;
import utils.Constants;

// màn hình chơi chính của trò chơi

public class GameScene extends Scene {
    
    @Override
    public void update() {
        // Logic update cho gameplay
        // Update entities (ball, paddle, bricks)
        // Check collisions
        // Update score, lives, etc.
    }
    
    @Override
    public void render(Graphics2D g2d) {
        // Vẽ background
        g2d.setColor(new Color(20, 20, 40));
        g2d.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        
        // Vẽ game elements
        // renderBricks(g2d);
        // renderPaddle(g2d);
        // renderBall(g2d);
        // renderUI(g2d);
        
        // Placeholder cho development
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.drawString("GAME SCENE", 
                      Constants.WINDOW_WIDTH/2 - 80, 
                      Constants.WINDOW_HEIGHT/2);
    }
}

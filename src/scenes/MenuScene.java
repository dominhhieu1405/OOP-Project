package scenes;
import java.awt.*;
import utils.Constants;
import utils.Logger;
import core.GameEngine;
import core.InputHandler;

// menu chính của trò chơi

public class MenuScene extends Scene {
    
    @Override
    public void update(InputHandler inputHandler) {
        // Logic update cho menu (xử lý input, animation, etc.)
        if (inputHandler.isSpacePressed()) {
            // Chuyển sang GameScene khi nhấn SPACE
            GameEngine.getGamePanel().setCurrentScene(new GameScene());
            Logger.log("Switching to GameScene");

        } else if (inputHandler.isEscapePressed()) {
            // Thoát trò chơi khi nhấn ESCAPE
            GameEngine.getGamePanel().stopGame();
            Logger.log("Game stopped from MenuScene");
            System.exit(0);
        }
    }
    
    @Override
    public void render(Graphics2D g2d) {
        // Vẽ background
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        
        // Vẽ tiêu đề game
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 48));
        g2d.drawString("ARKANOID", 
                      Constants.WINDOW_WIDTH/2 - 120, 
                      Constants.WINDOW_HEIGHT/2 - 100);
        
        // Vẽ menu options
        g2d.setFont(new Font("Arial", Font.PLAIN, 24));
        g2d.drawString("Press SPACE to Start", 
                      Constants.WINDOW_WIDTH/2 - 100, 
                      Constants.WINDOW_HEIGHT/2 + 50);
        
        g2d.drawString("Press ESC to Exit", 
                      Constants.WINDOW_WIDTH/2 - 80, 
                      Constants.WINDOW_HEIGHT/2 + 100);
    }
}

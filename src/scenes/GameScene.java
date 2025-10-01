package scenes;
import java.awt.*;
import core.InputHandler;
import utils.Constants;
import assets.Assets;
import assets.Sprite;

// màn hình chơi chính của trò chơi

public class GameScene extends Scene {
    
    // Assets và sprites
    private Assets assets;
    private Sprite paddleSprite;
    private Sprite ballSprite;
    private Sprite brickSprite;
    private Sprite backgroundSprite;
    
    // Game objects positions (temporary)
    private int paddleX = Constants.WINDOW_WIDTH / 2 - 50;
    private int paddleY = Constants.WINDOW_HEIGHT - 50;
    private int ballX = Constants.WINDOW_WIDTH / 2;
    private int ballY = Constants.WINDOW_HEIGHT / 2;
    
    public GameScene() {
        initializeAssets();
    }
    
    private void initializeAssets() {
        assets = Assets.getInstance();
        
        // Tạo sprites từ loaded images
        paddleSprite = new Sprite("paddle.png");
        ballSprite = new Sprite("ball.png");
        brickSprite = new Sprite("brick_red.png");
        backgroundSprite = new Sprite("background.png");
        
        System.out.println("GameScene assets initialized.");
    }
    
    @Override
    public void update(InputHandler inputHandlers) {
        // Logic update cho gameplay
        // Update entities (ball, paddle, bricks)
        // Check collisions
        // Update score, lives, etc.
        
        // Simple paddle movement for demo
        // paddleX += velocity;
    }
    
    @Override
    public void render(Graphics2D g2d) {
        // Vẽ background
        if (backgroundSprite.isLoaded()) {
            backgroundSprite.render(g2d, 0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        } else {
            // Fallback background
            g2d.setColor(new Color(20, 20, 40));
            g2d.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        }
        
        // Vẽ game elements
        renderBricks(g2d);
        renderPaddle(g2d);
        renderBall(g2d);
        renderUI(g2d);
    }
    
    private void renderBricks(Graphics2D g2d) {
        // Vẽ grid bricks đơn giản
        int brickWidth = 60;
        int brickHeight = 20;
        int rows = 5;
        int cols = 10;
        int startX = 50;
        int startY = 50;
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = startX + col * (brickWidth + 5);
                int y = startY + row * (brickHeight + 5);
                
                if (brickSprite.isLoaded()) {
                    brickSprite.render(g2d, x, y, brickWidth, brickHeight);
                } else {
                    // Fallback brick rendering
                    g2d.setColor(Color.RED);
                    g2d.fillRect(x, y, brickWidth, brickHeight);
                    g2d.setColor(Color.WHITE);
                    g2d.drawRect(x, y, brickWidth, brickHeight);
                }
            }
        }
    }
    
    private void renderPaddle(Graphics2D g2d) {
        if (paddleSprite.isLoaded()) {
            paddleSprite.render(g2d, paddleX, paddleY, 100, 20);
        } else {
            // Fallback paddle
            g2d.setColor(Color.CYAN);
            g2d.fillRect(paddleX, paddleY, 100, 20);
        }
    }
    
    private void renderBall(Graphics2D g2d) {
        if (ballSprite.isLoaded()) {
            ballSprite.render(g2d, ballX - 10, ballY - 10, 20, 20);
        } else {
            // Fallback ball
            g2d.setColor(Color.WHITE);
            g2d.fillOval(ballX - 10, ballY - 10, 20, 20);
        }
    }
    
    private void renderUI(Graphics2D g2d) {
        // Vẽ UI elements
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Score: 0", 10, 30);
        g2d.drawString("Lives: 3", Constants.WINDOW_WIDTH - 100, 30);
        
        // Demo text
        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        g2d.drawString("Assets Loading Demo", 10, Constants.WINDOW_HEIGHT - 20);
    }
}


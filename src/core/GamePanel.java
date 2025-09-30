package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import scenes.*;
import utils.*;

// Game Panel chứa game loop và render logic
public class GamePanel extends JPanel implements ActionListener {
    // ===== PHẦN TỪ GameLoop (merged) =====
    private Scene currentScene;
    private InputHandler inputHandler;
    private Timer gameTimer;
    private boolean isRunning;
    
    public GamePanel() {
        // Thiết lập panel
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setBackground(Color.BLACK);
        setVisible(true);
        setFocusable(true);
        
        // Khởi tạo input handler
        inputHandler = new InputHandler();
        addKeyListener(inputHandler);
        
        // Khởi tạo game timer (thay thế GameLoop) - 60 FPS
        gameTimer = new Timer(1000/60, this);
        isRunning = false;
        
        Logger.log("Game panel initialized.");
    }
    
    // ===== GAME LOOP LOGIC (từ GameLoop.update()) =====
    private void update() {

    }
    
    // ===== RENDER LOGIC (mới) =====
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Thiết lập chất lượng render
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, 
                            RenderingHints.VALUE_RENDER_QUALITY);
        
        // Vẽ scene hiện tại
        if (currentScene != null) {
            currentScene.render(g2d);
        } else {
            // Vẽ màn hình mặc định nếu chưa có scene
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 24));
            g2d.drawString("Loading...", 
                          Constants.WINDOW_WIDTH/2 - 50, 
                          Constants.WINDOW_HEIGHT/2);
        }
    }
    
    // ===== GAME LOOP CHÍNH =====
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRunning) {
            update();   // Cập nhật logic game
            repaint();  // Vẽ lại màn hình
        }
    }
    
    // ===== CONTROL METHODS (từ GameLoop) =====
    public void startGame() {
        if (!isRunning) {
            isRunning = true;
            gameTimer.start();
            Logger.log("Game loop started.");
        }
    }
    
    public void stopGame() {
        if (isRunning) {
            isRunning = false;
            gameTimer.stop();
            Logger.log("Game loop stopped.");
        }
    }
    
    public void pauseGame() {
        if (isRunning) {
            gameTimer.stop();
            Logger.log("Game loop paused.");
        }
    }
    
    public void resumeGame() {
        if (isRunning) {
            gameTimer.start();
            Logger.log("Game loop resumed.");
        }
    }
    
    // ===== SCENE MANAGEMENT =====
    public void setCurrentScene(Scene scene) {
        this.currentScene = scene;
        Logger.log("Scene changed to: " + scene.getClass().getSimpleName());
    }
    
    public Scene getCurrentScene() {
        return currentScene;
    }
    
    // ===== GETTERS =====
    public boolean isRunning() {
        return isRunning;
    }
    
    public InputHandler getInputHandler() {
        return inputHandler;
    }
}
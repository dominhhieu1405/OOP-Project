package core;
import javax.swing.*;
import utils.Constants;
import utils.Logger;
import scenes.*;

// quản lý trò chơi và khởi tạo graphics

public class GameEngine {
    private JFrame window;
    private GamePanel gamePanel;
    private ResourceManager resourceManager;

    public GameEngine() {
        initializeWindow();
        initializeGamePanel();
    }
    
    private void initializeWindow() {
        if(window == null) {
            window = new JFrame(Constants.GAME_TITLE);
            window.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setLocationRelativeTo(null); // Center on screen
            Logger.log("Game window initialized.");
        } else {
            Logger.log("Game window already initialized.");
        }
    }
    
    private void initializeGamePanel() {
        gamePanel = new GamePanel();
        window.add(gamePanel);
        window.setVisible(true);
        
        // Thiết lập scene mặc định
        gamePanel.setCurrentScene(new MenuScene());
        Logger.log("Game panel added to window.");
    }

    public JFrame getWindow() {
        return window;
    }
    
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void startGame() {
        if (gamePanel != null) {
            gamePanel.startGame();
            Logger.log("Game started.");
        }
    }
    
    public void stopGame() {
        if (gamePanel != null) {
            gamePanel.stopGame();
            Logger.log("Game stopped.");
        }
    }
    
    public void pauseGame() {
        if (gamePanel != null) {
            gamePanel.pauseGame();
            Logger.log("Game paused.");
        }
    }
    
    public void resumeGame() {
        if (gamePanel != null) {
            gamePanel.resumeGame();
            Logger.log("Game resumed.");
        }
    }
    
    public void changeScene(Scene newScene) {
        if (gamePanel != null) {
            gamePanel.setCurrentScene(newScene);
        }
    }

    public void close() {
        if (gamePanel != null) {
            gamePanel.stopGame();
        }
        if(window != null) {
            window.dispose();
            window = null;
            Logger.log("Game window closed.");
        } else {
            Logger.log("No game window to close.");
        }
    }

    public void loadResources() {
        resourceManager = new ResourceManager();
        // load tài nguyên trò chơi
        resourceManager.loadImages();
        resourceManager.loadSounds();
        resourceManager.loadFonts();
    }

}
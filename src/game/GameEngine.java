package game;
import Constant.Constant;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import game.scenes.*;

import java.awt.*;
import entity.*;
import manager.SoundManager;
public class GameEngine {
    private JFrame window;
    private GamePanel gamePanel;
    public GameEngine() {
        window = new JFrame("Arkanoid");
        gamePanel = GamePanel.getInstance();
        window.setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add the main game panel to the window so scenes can be displayed
        window.setContentPane(gamePanel);
        window.add(new MenuScene());
        System.out.println("Created Default Scene and added to window");
        window.setVisible(true);
    }
    public void start() {
        Ball.getInstance().reset();
        while (true) {
        // đảm bảo repaint trên EDT
            SwingUtilities.invokeLater(() -> gamePanel.repaint());
            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
    
}

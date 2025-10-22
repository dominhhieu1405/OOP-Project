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
        SoundManager.init();
        SoundManager.loop("bgm");
        window = new JFrame("Arkanoid");
        gamePanel = GamePanel.getInstance();

        window.setIconImage(Constant.FAVICON);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel.setPreferredSize(new Dimension(800, 600));
        // Add the main game panel to the window so scenes can be displayed
        window.setContentPane(gamePanel);
        window.revalidate();

        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
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

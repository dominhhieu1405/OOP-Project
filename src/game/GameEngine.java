package game;
import Constant.Constant;
import javax.swing.JFrame;
import java.awt.*;
import entity.*;
import manager.SoundManager;
public class GameEngine {
    private JFrame frame;
    private GamePanel gamePanel;
    public GameEngine() {
        SoundManager.init();
        frame = new JFrame("Arkanoid");
        frame.setIconImage(Constant.FAVICON);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new Dimension(800, 600));
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public void start() {
        Ball.getInstance().reset();
        while (true) {
            gamePanel.repaint();
            try {
                Thread.sleep(16); // Approximately 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}

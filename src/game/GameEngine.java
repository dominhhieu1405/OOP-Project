package game;
import Constant.Constant;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import entity.*;
public class GameEngine {
    private JFrame frame;
    private GamePanel gamePanel;
    public GameEngine() {
        frame = new JFrame("Arkanoid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        gamePanel = new GamePanel();
        frame.add(gamePanel);
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

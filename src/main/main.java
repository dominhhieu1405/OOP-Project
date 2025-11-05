package main;
import game.GameEngine;
import game.GamePanel;
import Constant.Constant;
import manager.SoundManager;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.*;
public class main {
    public static void main(String[] args) {
        JFrame window;
        GamePanel gamePanel;
        
        SoundManager.init();
        System.out.println("Done initializing SoundManager.");
        // SoundManager.loop("bgm");
        window = new JFrame("Arkanoid");
        gamePanel = GamePanel.getInstance();
        
        gamePanel.setPreferredSize(new Dimension(800, 600));
        window.setIconImage(Constant.FAVICON);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add the main game panel to the window so scenes can be displayed
        window.setContentPane(gamePanel);
        window.revalidate();

        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);


        SwingUtilities.invokeLater(() -> {
// Not use 2 threads to avoid concurrency issues

            // Update Logic Thread
            GameEngine game = new GameEngine();
            Thread logicThread = new Thread(game);
            logicThread.start();

            // Render Timer
            Timer renderTimer = new Timer(16, e -> {
                gamePanel.repaint();
            });
            renderTimer.start();
        });
    }
}
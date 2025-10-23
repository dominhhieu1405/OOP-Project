package game;
import Constant.Constant;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import game.scenes.*;

import java.awt.*;
import entity.*;
import manager.SoundManager;

// GameEngine update game logic and manage the main window
public class GameEngine implements Runnable {
    private volatile boolean running = true;

    @Override 
    public void run() {
        while(running) {
            long start = System.currentTimeMillis();

            Scene current = GamePanel.getInstance().getCurrentScene();
            if (current != null) {
                synchronized (current) {
                    current.update();
                }
            }

            long elapsed = System.currentTimeMillis() - start;
            long sleep = 16 - elapsed;
            if (sleep > 0) {
                try { Thread.sleep(sleep); } catch (InterruptedException ignored) {}
            }
        }
    }

    public void stop() {
        running = false;
    }
    
}

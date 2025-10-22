package game;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
// import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import entity.*;
import game.scenes.MenuScene;
import manager.BlockManager;
import manager.PowerUpManager;

public class GamePanel extends JPanel {
    private Scene currentScene;
    private static GamePanel instance;
    private GamePanel() {
        setScene(new MenuScene()); // Default scene
        setFocusable(true);
        requestFocusInWindow();
        BlockManager.getInstance().test();
    }

    public static GamePanel getInstance() {
        if (instance == null) {
            instance = new GamePanel();
        }
        return instance;
    }
    
    /**
     * Override phương thức paintComponent để vẽ các thành phần game
     */
    @Override
    protected void paintComponent(Graphics g) {
        if (currentScene != null) {
            currentScene.paintComponent(g);
        }   
    }


    public void setScene(Scene scene) {
        System.out.println("Switching to new scene: " + scene.getClass().getSimpleName());
        for (KeyListener kl : this.getKeyListeners()) {
            this.removeKeyListener(kl);
        }
        for (MouseListener ml : this.getMouseListeners()) {
            this.removeMouseListener(ml);
        }
        for (MouseMotionListener mml : this.getMouseMotionListeners()) {
            this.removeMouseMotionListener(mml);
        }
        currentScene = scene;
        this.removeAll();
        this.add(currentScene);
        this.revalidate();
        this.repaint();
        System.out.println("Current scene set to: " + currentScene.getClass().getSimpleName());

        if (scene.useKeyboard()) {
            // addKeyListener(scene.getKeyListener());
            scene.setupKeyBindings();
        }

        if (scene.useMouse()) {
            addMouseListener(scene.getMouseListener());
            addMouseMotionListener(scene.getMouseMotionListener());
        }
    }

    public Scene getCurrentScene() {
        return currentScene;
    }
}

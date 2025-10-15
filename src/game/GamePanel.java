package game;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
// import java.awt.*;
import java.awt.*;
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

        // remove old stuff & listeners
        this.removeAll();

        // set new scene
        currentScene = scene;

        // ensure scene is sized to fill panel
        this.add(currentScene, BorderLayout.CENTER);

        // ensure scene is focusable if it needs keyboard
        currentScene.setFocusable(true);

        // revalidate + repaint để layout và vẽ lại ngay
        this.revalidate();
        this.repaint();

        System.out.println("Current scene set to: " + currentScene.getClass().getSimpleName());

        // setup input
        if (scene.useKeyboard()) {
            scene.setupKeyBindings();
            currentScene.requestFocusInWindow();
        }

        if (scene.useMouse()) {
            currentScene.addMouseListener(scene.getMouseListener());
            currentScene.addMouseMotionListener(scene.getMouseMotionListener());
        }
    }


    public Scene getCurrentScene() {
        return currentScene;
    }
}

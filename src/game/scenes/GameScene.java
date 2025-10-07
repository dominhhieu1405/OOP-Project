package game.scenes;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

import entity.Ball;
import entity.Paddle;

public class GameScene extends game.Scene {

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        // Draw background
        g.drawImage(Constant.Constant.BACKGROUND_IMG, 0, 0, Constant.Constant.FRAME_WIDTH, Constant.Constant.FRAME_HEIGHT, null);
        // Draw paddle
        Paddle.getInstance().render(g);
        // render ball
        Ball.getInstance().render(g);
        // render blocks
        manager.BlockManager.getInstance().render(g);
        // render power-ups
        manager.PowerUpManager.getInstance().render(g);
        // Update game state
        manager.PowerUpManager.getInstance().update();
        Paddle.getInstance().update();
        Ball.getInstance().update();
        manager.PowerUpManager.getInstance().update();
        
    }

    public boolean useMouse() {
        return false;
    }
    public boolean useKeyboard() {
        return true;
    }
    
    public void setupKeyBindings() {
        // Lấy input map và action map
        javax.swing.InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        javax.swing.ActionMap actionMap = getActionMap();


        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "pressLeft");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "pressLeft");
        actionMap.put("pressLeft", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                System.out.println("press left");
                Paddle.getInstance().setMovingLeft(true);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "releaseLeft");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "releaseLeft");
        actionMap.put("releaseLeft", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                System.out.println("release left");
                Paddle.getInstance().setMovingLeft(false);
            }
        });


        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "pressRight");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "pressRight");
        actionMap.put("pressRight", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                System.out.println("press right");
                Paddle.getInstance().setMovingRight(true);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "releaseRight");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "releaseRight");
        actionMap.put("releaseRight", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                System.out.println("release right");
                Paddle.getInstance().setMovingRight(false);
            }
        });



        // Bind phím SPACE hoặc ENTER để bắt đầu hoặc tạm dừng game
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "toggleStartPause");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "toggleStartPause");
        actionMap.put("toggleStartPause", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball.getInstance().runBall();
                if (!Ball.getInstance().getIsAlive()) {
                    Ball.getInstance().respawn();
                }
                repaint();
            }
        });
    }

    public MouseMotionListener getMouseMotionListener() {
        return null;
    }
    public MouseListener getMouseListener() {
        return null;
    }
}

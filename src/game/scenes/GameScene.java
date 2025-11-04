package game.scenes;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Color;
import Constant.Constant;

import entity.Ball;
import entity.Paddle;
import manager.BlockManager;
import manager.MapManager;
import manager.PowerUpManager;

public class GameScene extends game.Scene {
    public static final String STATUS_PLAYING = "PLAYING";
    public static final String STATUS_PAUSE = "PAUSE";
    public static final String STATUS_GAMEOVER = "GAMEOVER";
    public static final String STATUS_WIN = "WIN";
    public String status = STATUS_PLAYING;
    
    public static GameScene instance;
    
    private GameScene(){
        super();
        PowerUpManager.getInstance().reset();
        BlockManager.getInstance().reset();
        Ball.getInstance().reset();
        Paddle.getInstance().reset();
        Paddle.getInstance().setWorking(true);
        Ball.getInstance().setIsRunning(false);
        status = STATUS_PLAYING;
        setupKeyBindings();
    }
    
    public static GameScene getInstance() {
        if (instance == null) {
            instance = new GameScene();
        }
        return instance;
    }
    
    public GameScene resetScene() {
        System.out.println("Rmoving pause buttons");

        RemoveAllButton();

        PowerUpManager.getInstance().reset();
        BlockManager.getInstance().reset();
        Ball.getInstance().reset();
        Paddle.getInstance().reset();
        Paddle.getInstance().setWorking(true);
        Ball.getInstance().setIsRunning(false);
        status = STATUS_PLAYING;
        setupKeyBindings();
        return this;
    }

    public GameScene continueGame() {
        Paddle.getInstance().setWorking(true);
        Ball.getInstance().setIsRunning(true);
        status = STATUS_PLAYING;
        RemoveAllButton();
        return this;
    }

    private void RemoveAllButton() {
        remove(Pause.getInstance().getResumeButton());
        remove(Pause.getInstance().getMenuButton());
        remove(Pause.getInstance().getPlayAgainButton());
        remove(GameOver.getInstance().getMenuButton());
        remove(GameOver.getInstance().getPlayAgainButton());
        remove(Win.getInstance().getNextLevelButton());
        remove(Win.getInstance().getMenuButton());
        remove(Win.getInstance().getPlayAgainButton());
        revalidate();
        repaint();
    }

    @Override
    public void update() {
        if (Paddle.getInstance().isWorking()) {
            Paddle.getInstance().update();
            Ball.getInstance().update();
            manager.PowerUpManager.getInstance().update();
        }
        // check win
        if (!this.status.equals(STATUS_WIN) && BlockManager.getInstance().checkWin()) {
            status = STATUS_WIN;
            System.out.println("Win detected in update()");
            Paddle.getInstance().setWorking(false);
            Ball.getInstance().setIsRunning(false);
        
            javax.swing.JButton btn1 = Win.getInstance().getNextLevelButton();
            javax.swing.JButton btn2 = Win.getInstance().getMenuButton();
            javax.swing.JButton btn3 = Win.getInstance().getPlayAgainButton();
            javax.swing.SwingUtilities.invokeLater(()->{
                if (btn1.getParent()!= this) this.add(btn1);
                if (btn2.getParent()!=this) this.add(btn2);
                if (btn3.getParent()!=this) this.add(btn3);
                btn1.setBounds(220, 300, 160, 40);
                btn2.setBounds(220, 350, 160, 40);
                btn3.setBounds(220, 400, 160, 40);
                btn1.setVisible(true);
                btn2.setVisible(true);
                btn3.setVisible(true);
                this.revalidate();
                this.repaint();
            });
            //TODO: Update map unlock


            System.out.println("Unlock next map if any");
            Win.getInstance().getNextLevelButton().setEnabled(MapManager.getInstance().hasNextMap());
            MapManager.getInstance().unlockNextMap();
        }
        // check game over
        if (!Ball.getInstance().getIsAlive()) {
            status = STATUS_GAMEOVER;
            Paddle.getInstance().setWorking(false);
            Ball.getInstance().setIsRunning(false);
            javax.swing.JButton btn1 = GameOver.getInstance().getMenuButton();
            javax.swing.JButton btn2 = GameOver.getInstance().getPlayAgainButton();
            javax.swing.SwingUtilities.invokeLater(()->{
                if (btn1.getParent()!= this) this.add(btn1);
                if (btn2.getParent()!=this) this.add(btn2);
                btn1.setBounds(220, 320, 360, 60);
                btn2.setBounds(220, 400, 360, 60);
                
        // menuButton.setBounds(220, 320, 360, 60);
        // playAgainButton.setBounds(220, 400, 360, 60);
                btn1.setVisible(true);
                btn2.setVisible(true);
                this.revalidate();
                this.repaint();
            });

        }

        if (status.equals(STATUS_PAUSE)) {
            System.out.println("Game paused, showing pause buttons");
            javax.swing.JButton btn1 = Pause.getInstance().getResumeButton();
            javax.swing.JButton btn2 = Pause.getInstance().getMenuButton();
            javax.swing.JButton btn3 = Pause.getInstance().getPlayAgainButton();
            javax.swing.SwingUtilities.invokeLater(()->{
                if (btn1.getParent() != GameScene.this) this.add(btn1);
                if (btn2.getParent() != GameScene.this) this.add(btn2);
                if (btn3.getParent() != GameScene.this) this.add(btn3);
                btn1.setBounds(220, 240, 360, 60);
                btn2.setBounds(220, 320, 360, 60);
                btn3.setBounds(220, 400, 360, 60);
                btn1.setVisible(true);
                btn2.setVisible(true);
                btn3.setVisible(true);
                this.revalidate();
                this.repaint();
            });
        }
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

// ================ RENDER GAME ENTITIES ================

/*
 *     Keep render but update only when game is playing
 * 
 *     Game is playing when Paddle is working
 *     Game is paused / over / win when Paddle is not working
 * 
 */
        // Draw background
        g.drawImage(Constant.BACKGROUND_IMG, 0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT, null);
        
        // render blocks
        manager.BlockManager.getInstance().render(g);
        // Draw paddle
        Paddle.getInstance().render(g);
        // render ball
        Ball.getInstance().render(g);
        // render power-ups
        manager.PowerUpManager.getInstance().render(g);
// ================== check game status ===================
        // // check win
        // if (BlockManager.getInstance().checkWin()) {
        //     status = STATUS_WIN;
        //     Paddle.getInstance().setWorking(false);
        //     Ball.getInstance().setIsRunning(false);
        // }
        // // check game over
        // if (!Ball.getInstance().getIsAlive()) {
        //     status = STATUS_GAMEOVER;
        //     Paddle.getInstance().setWorking(false);
        //     Ball.getInstance().setIsRunning(false);
        // }

// =======================================================================
        // update entities only when game is playing
        // if (Paddle.getInstance().isWorking()) {

        //     Paddle.getInstance().update();
        //     Ball.getInstance().update();
        //     manager.PowerUpManager.getInstance().update();
        // }

// ================ render overlay scenes ================
        if (!Paddle.getInstance().isWorking()) {
            // Freeze the game screen with a translucent overlay
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);

            if (status.equals(STATUS_WIN)) {
                Win.getInstance().render(g);

            } else if (status.equals(STATUS_GAMEOVER)) {
                GameOver.getInstance().render(g);

            } else if (status.equals(STATUS_PAUSE)) {} {
                Pause.getInstance().render(g);
            }
        }
        


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

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "pauseGame");
        actionMap.put("pauseGame", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                status = STATUS_PAUSE;
                Paddle.getInstance().setWorking(false);
                Ball.getInstance().setIsRunning(false);
            }
        });


        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "pressLeft");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "pressLeft");
        actionMap.put("pressLeft", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                // System.out.println("press left");
                Paddle.getInstance().setMovingLeft(true);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "releaseLeft");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "releaseLeft");
        actionMap.put("releaseLeft", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                // System.out.println("release left");
                Paddle.getInstance().setMovingLeft(false);
            }
        });


        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "pressRight");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "pressRight");
        actionMap.put("pressRight", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                // System.out.println("press right");
                Paddle.getInstance().setMovingRight(true);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "releaseRight");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "releaseRight");
        actionMap.put("releaseRight", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                // System.out.println("release right");
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

    // TODO: Mouse Listener and Mouse Motion Listener
    // Other choice: JButton.addActionListener() for buttons like in MenuScene
    public MouseMotionListener getMouseMotionListener() {
        return null;
    }
    public MouseListener getMouseListener() {
        return null;
    }
}

package game;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
// import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import entity.*;
import manager.BlockManager;
import manager.PowerUpManager;

public class GamePanel extends JPanel {
    
    public GamePanel() {
        setupKeyBindings();
        setFocusable(true);
        requestFocusInWindow();
        BlockManager.getInstance().test();
    }
    
    private void setupKeyBindings() {
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
    
    /**
     * Override phương thức paintComponent để vẽ các thành phần game
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Constant.Constant.BACKGROUND_IMG, 0, 0, Constant.Constant.FRAME_WIDTH, Constant.Constant.FRAME_HEIGHT, null);
        BlockManager.getInstance().render(g);
        PowerUpManager.getInstance().update();
        PowerUpManager.getInstance().render(g);
        Ball ball = Ball.getInstance();
        ball.update();
        ball.render(g);
        Paddle paddle = Paddle.getInstance();
        paddle.update();
        paddle.render(g);
    }


    // public void update() {
    //     Ball.getInstance().update();
        

    //     // Ví dụ: di chuyển ball, kiểm tra va chạm, etc.
        
    //     // Yêu cầu vẽ lại sau khi cập nhật logic
    //     repaint();
    // }
}

package game;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
// import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import entity.*;

public class GamePanel extends JPanel {
    
    public GamePanel() {
        setupKeyBindings();
    }
    
    private void setupKeyBindings() {
        // Lấy input map và action map
        javax.swing.InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        javax.swing.ActionMap actionMap = getActionMap();
        
        // Bind phím LEFT ARROW và A cho di chuyển trái
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "moveLeft");
        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paddle.getInstance().moveLeft();
                if (!Ball.getInstance().getIsRunning()) {
                    Ball.getInstance().setX(Paddle.getInstance().getX() + Paddle.getInstance().getWidth() / 2);
                }
                repaint();
            }
        });
        
        // Bind phím RIGHT ARROW và D cho di chuyển phải
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moveRight");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "moveRight");
        actionMap.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paddle.getInstance().moveRight();
                if (!Ball.getInstance().getIsRunning()) {
                    Ball.getInstance().setX(Paddle.getInstance().getX() + Paddle.getInstance().getWidth() / 2);
                }
                repaint();
            }
        });

        // Bind phím SPACE hoặc ENTER để bắt đầu hoặc tạm dừng game
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "toggleStartPause");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "toggleStartPause");
        actionMap.put("toggleStartPause", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball.getInstance().runBall();
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
        Ball ball = Ball.getInstance();
        ball.update();
        ball.render(g);
        Paddle paddle = Paddle.getInstance();
        paddle.render(g);
    }


    // public void update() {
    //     Ball.getInstance().update();
        

    //     // Ví dụ: di chuyển ball, kiểm tra va chạm, etc.
        
    //     // Yêu cầu vẽ lại sau khi cập nhật logic
    //     repaint();
    // }
}

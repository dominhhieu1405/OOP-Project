package game.scenes;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import javax.swing.*;

import game.GamePanel;

public class MenuScene extends game.Scene {
    private Rectangle startButtonRect;
    private boolean hover = false;

    public void init() {

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw background
        g.drawImage(Constant.Constant.BACKGROUND_IMG, 0, 0, Constant.Constant.FRAME_WIDTH, Constant.Constant.FRAME_HEIGHT, null);
        // System.out.println("Repainting Menu background");

        // draw start button (rect for testing)
            int w = 200;
            int h = 80;
            int x = (Constant.Constant.FRAME_WIDTH - w) / 2;
            int y = 180;
            startButtonRect = new Rectangle(x, y, w, h);
            g.setColor(hover ? Color.LIGHT_GRAY : Color.GRAY);
            g.fillRect(startButtonRect.x, startButtonRect.y, startButtonRect.width,
                    startButtonRect.height);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            FontMetrics fm = g.getFontMetrics();
            String buttonText = "Start Game";
            int textX = startButtonRect.x + (startButtonRect.width - fm.stringWidth(buttonText)) / 2;
            int textY = startButtonRect.y + (startButtonRect.height - fm.getHeight()) / 2 + fm.getAscent();
            g.drawString(buttonText, textX, textY);
        //

    }

    public boolean useMouse() {
        return true;
    }
    public boolean useKeyboard() {
        return false;
    }

    public void setupKeyBindings() {
        // No key bindings needed for menu
    }

    // TODO: Implement mouse listener for button clicks
    public MouseListener getMouseListener() {
        return new MouseAdapter() {
            @Override 
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                if (startButtonRect.contains(p)) {
                    // Start the game
                    GamePanel.getInstance().setScene(new GameScene());
                }
            }
        };
    }

    // TODO: Implement mouse motion listener for hover effects
    public MouseMotionListener getMouseMotionListener() {
        return null;
    }
}

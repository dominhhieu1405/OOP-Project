package game.scenes;
import javax.swing.JButton;
import java.io.File;
import java.awt.*;
import Constant.Constant;
import game.GamePanel;

public class GameOver {
    private JButton menuButton;
    private JButton playAgainButton;
    private Font f;

// ==================== Singleton Pattern ====================
    private static GameOver instance = null;

    private GameOver() {
        if (instance == null) {
            // init buttons:
            try {
                f = Font.createFont(Font.TRUETYPE_FONT, new File(Constant.FONT_PATH)).deriveFont(20f);
            } catch (Exception e) {
                System.out.println("1");
                f = new Font("Verdana", Font.BOLD, 28);
            }
            this.menuButton = this.createBtn("Menu");
            this.playAgainButton = this.createBtn("Chơi lại");
            
            // add action listeners
            this.menuButton.addActionListener(e -> {
                System.out.println("Pressed Menu");
                GamePanel.getInstance().setScene(new MenuScene());
            });
            this.playAgainButton.addActionListener(e -> {
                System.out.println("Pressed Play Again");
                // Logic to restart the current level
                
                GamePanel.getInstance().setScene(new GameScene());
            });
            instance = this;
        } 
    }
    public static GameOver getInstance() {
        if (instance == null){
            instance = new GameOver();
            return instance;
        } else {
            return instance;
        }
    }

    public JButton getMenuButton() {
        return menuButton;
    }
    public JButton getPlayAgainButton() {
        return playAgainButton;
    }

// ====================Render===================
    public void render(Graphics g) {
        menuButton.setBounds(220, 320, 360, 60);
        playAgainButton.setBounds(220, 400, 360, 60);
    }

// ========================Utils=========================
    private JButton createBtn(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Image img = getModel().isRollover() ? Constant.BUTTON_ACTIVE_IMG : Constant.BUTTON_IMG;
                g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
                g.setFont(f);
                g.setColor(Color.WHITE);

                FontMetrics fm = g.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() + textHeight) / 2 - 4;

                g.drawString(getText(), x, y);
            }
        };

        // Xóa border, background Swing mặc định
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setRolloverEnabled(true);
        button.setForeground(Color.WHITE);

        return button;
    }
} 
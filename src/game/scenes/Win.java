package game.scenes;
import javax.swing.JButton;
import java.io.File;
import java.awt.*;
import Constant.Constant;
import game.GamePanel;

public class Win {
    private JButton nextLevelButton;
    private JButton menuButton;
    private JButton playAgainButton;

    private Font f;

// ==================== Singleton Pattern ====================
    private static Win instance = null;

    private Win() {
        if (instance == null) {
            // init buttons:
            try {
                f = Font.createFont(Font.TRUETYPE_FONT, new File(Constant.FONT_PATH)).deriveFont(20f);
            } catch (Exception e) {
                System.out.println("1");
                f = new Font("Verdana", Font.BOLD, 28);
            }
            this.nextLevelButton = this.createBtn("Màn tiếp theo");
            this.menuButton = this.createBtn("Menu");
            this.playAgainButton = this.createBtn("Chơi lại");
            
            // add action listeners
            this.nextLevelButton.addActionListener(e -> {
                System.out.println("Pressed Next Level");
                // Logic to go to the next level
                //TODO: Implement next level logic
            });
            this.menuButton.addActionListener(e -> {
                System.out.println("Pressed Menu");
                GamePanel.getInstance().setScene(new MenuScene());
            });
            this.playAgainButton.addActionListener(e -> {
                System.out.println("Pressed Play Again");
                // Logic to restart the current level
                //TODO: Implement play again logic
            });
            
            instance = this;
        }
    }

    public static Win getInstance() {
        if (instance == null){
            instance = new Win();
            return instance;
        } else {
            return instance;
        }
    }

// ==========================================================
// ================= Getters and Setters ====================
    public JButton getNextLevelButton() {
        return nextLevelButton;
    }

    public JButton getMenuButton() {
        return menuButton;
    }

    public JButton getPlayAgainButton() {
        return playAgainButton;
    }

// ==========================================================
// =============== Render and Update methods ================
    public void render(java.awt.Graphics g) {
        renderButtons(g);
    }

    // Render buttons
    public void renderButtons(java.awt.Graphics g) {
        
        
        // Position buttons
        nextLevelButton.setBounds(220, 240, 360, 60);
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
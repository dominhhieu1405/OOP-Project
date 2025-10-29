package game.scenes;
import javax.swing.JButton;
import java.io.File;
import java.awt.*;
import Constant.Constant;
import game.GamePanel;

public class Pause {
    private JButton resumeButton;
    private JButton menuButton;
// ==================== Singleton Pattern ====================
    private static Pause instance = null;

    private Pause() {
        if (instance == null) {
            this.resumeButton = this.createBtn("Tiếp tục");
            this.menuButton = this.createBtn("Menu");
            
            // add action listeners
            this.resumeButton.addActionListener(e -> {
                System.out.println("Pressed Resume");
                GamePanel.getInstance().setScene(new GameScene());
            });
            this.menuButton.addActionListener(e -> {
                System.out.println("Pressed Menu");
                GamePanel.getInstance().setScene(new MenuScene());
            });
            instance = this;
        } 
    }
    public static Pause getInstance() {
        if (instance == null){
            instance = new Pause();
            return instance;
        } else {
            return instance;
        }
    }

    public void render(Graphics g) {
        resumeButton.setBounds(220, 320, 360, 60);
        menuButton.setBounds(220, 400, 360, 60);
    }

    public JButton getResumeButton() {
        return resumeButton;
    }
    public JButton getMenuButton() {
        return menuButton;
    }
    
// ========================Utils=========================
    private JButton createBtn(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Image img = getModel().isRollover() ? Constant.BUTTON_ACTIVE_IMG : Constant.BUTTON_IMG;
                g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
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

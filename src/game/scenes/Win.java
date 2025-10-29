package game.scenes;
import javax.swing.JButton;
import java.io.File;
import java.awt.*;
import Constant.Constant;
import game.GamePanel;
import manager.BlockManager;
import manager.MapManager;

public class Win {
    private JButton nextLevelButton;
    private JButton menuButton;
    private JButton playAgainButton;


// ==================== Singleton Pattern ====================
    private static Win instance = null;

    private Win() {
        if (instance == null) {
            // init buttons:

            this.nextLevelButton = Constant.createBtn("Màn tiếp theo");
            this.menuButton = Constant.createBtn("Menu");
            this.playAgainButton = Constant.createBtn("Chơi lại");



            // add action listeners
            this.nextLevelButton.addActionListener(e -> {
                System.out.println("Pressed Next Level");
                // Logic to go to the next level
                BlockManager.getInstance().setCurrentMap(MapManager.getInstance().getCurrentMap().path);
                GamePanel.getInstance().setScene(new GameScene());
            });
            this.menuButton.addActionListener(e -> {
                System.out.println("Pressed Menu");
                GamePanel.getInstance().setScene(new MenuScene());
            });
            this.playAgainButton.addActionListener(e -> {
                System.out.println("Pressed Play Again");
                GamePanel.getInstance().setScene(new GameScene());
            });
            
            instance = this;
        }
    }

    public static Win getInstance() {

        if (instance == null){
            instance = new Win();
        }

        return instance;
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
}
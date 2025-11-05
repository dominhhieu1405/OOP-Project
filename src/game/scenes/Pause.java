package game.scenes;
import javax.swing.JButton;
import java.io.File;
import java.awt.*;
import Constant.Constant;
import entity.Ball;
import entity.Paddle;
import game.GamePanel;
import game.scenes.GameScene;
import javax.swing.JPanel;
public class Pause {
    private JButton resumeButton;
    private JButton menuButton;
    private JButton playAgainButton;
    // ==================== Singleton Pattern ====================
    private static Pause instance = null;

    /**
     * Constructor mặc định.
     */
    private Pause() {
        if (instance == null) {
            this.resumeButton = Constant.createBtn("Tiếp tục");
            this.menuButton = Constant.createBtn("Menu");
            this.playAgainButton = Constant.createBtn("Chơi lại");

            // add action listeners
            this.resumeButton.addActionListener(e -> {
                System.out.println("Pressed Resume");
                GamePanel.getInstance().setScene(GameScene.getInstance().continueGame());

            });
            this.menuButton.addActionListener(e -> {
                System.out.println("Pressed Menu");
                GameScene.getInstance().resetScene();
                GamePanel.getInstance().setScene(new MenuScene());
            });
            this.playAgainButton.addActionListener(e -> {
                System.out.println("Pressed Play Again");
                GamePanel.getInstance().setScene(GameScene.getInstance().resetScene());
            });
            instance = this;
        }
    }

    /**
     * Lấy instance của pause.
     * @return instance
     */
    public static Pause getInstance() {
        if (instance == null){
            instance = new Pause();
            return instance;
        } else {
            return instance;
        }
    }

    /**
     * Thêm các nút Resume, Menu, Play Again vào panel .
     * @param panel nơi nút được thêm vào.
     */
    public void addButtonsToPanel(JPanel panel) {
        if (!GameScene.getInstance().isAddingButtons){
            if (resumeButton.getParent() != panel) {
                panel.add(resumeButton);
            }
            if (menuButton.getParent() != panel) {
                panel.add(menuButton);
            }
            if (playAgainButton.getParent() != panel) {
                panel.add(playAgainButton);
            }
            GameScene.getInstance().isAddingButtons = true;
        }
    }

    /**
     * Vẽ nút.
     */
    public void render(Graphics g) {
        resumeButton.setBounds(220, 240, 360, 60);
        menuButton.setBounds(220, 400, 360, 60);
        playAgainButton.setBounds(220, 320, 360, 60);
    }

    public JButton getResumeButton() {
        return resumeButton;
    }
    public JButton getMenuButton() {
        return menuButton;
    }

    public JButton getPlayAgainButton() {
        return playAgainButton;
    }
}
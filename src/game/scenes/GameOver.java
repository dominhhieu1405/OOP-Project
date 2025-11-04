package game.scenes;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.awt.*;
import Constant.Constant;
import game.GamePanel;

public class GameOver {
    private JButton menuButton;
    private JButton playAgainButton;

// ==================== Singleton Pattern ====================
    private static GameOver instance = null;

    private GameOver() {
        if (instance == null) {
            this.menuButton = Constant.createBtn("Menu");
            this.playAgainButton = Constant.createBtn("Chơi lại");
            
            // add action listeners
            this.menuButton.addActionListener(e -> {
                System.out.println("Pressed Menu");
                GamePanel.getInstance().setScene(new MenuScene());
            });
            this.playAgainButton.addActionListener(e -> {
                System.out.println("Pressed Play Again");
                // Logic to restart the current level
                
                GamePanel.getInstance().setScene(GameScene.getInstance().resetScene());
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

    public void addButtonsToPanel(JPanel panel) {
        if (menuButton.getParent() != panel) {
            panel.add(menuButton);
        }
        if (playAgainButton.getParent() != panel) {
            panel.add(playAgainButton);
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
        menuButton.setBounds(220, 240, 360, 60);
        playAgainButton.setBounds(220, 320, 360, 60);
    }

//
//    public boolean useKeyboard() {
//        return true;
//    }
//
//    public void setupKeyBindings() {
//        // Lấy input map và action map
//        javax.swing.InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
//        javax.swing.ActionMap actionMap = getActionMap();
//
//        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0, false), "pressPlayAgain");
//        actionMap.put("pressMap", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // System.out.println("press enter");
//                playAgainButton.doClick();
//            }
//        });
//        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_M, 0, false), "pressMenu");
//        actionMap.put("pressPlay", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // System.out.println("press enter");
//                menuButton.doClick();
//            }
//        });
//    }

} 
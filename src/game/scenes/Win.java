package game.scenes;
import javax.swing.JButton;

public class Win {
    private JButton nextLevelButton;
    private JButton menuButton;
    private JButton playAgainButton;


// ==================== Singleton Pattern ====================
    private static Win instance = null;

    private Win() {
        if (instance == null) {
            // init buttons:
            nextLevelButton = new JButton("Next Level");
            menuButton = new JButton("Menu");
            playAgainButton = new JButton("Play Again");
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
        // Render win screen elements here
        g.drawString("You Win!", 350, 200);
        // Note: Actual button rendering is handled by Swing
        renderButtons(g);
    }

    // Render buttons
    public void renderButtons(java.awt.Graphics g) {
        // Buttons are Swing components; they render themselves
        // This method can be used to position buttons if needed
        nextLevelButton.setBounds(350, 250, 100, 30);
        menuButton.setBounds(350, 300, 100, 30);
        playAgainButton.setBounds(350, 350, 100, 30);
        
        nextLevelButton.paint(g);
        menuButton.paint(g);
        playAgainButton.paint(g);
        
    }

}
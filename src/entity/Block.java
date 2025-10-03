package entity;
import java.awt.*;

import javax.swing.ImageIcon;
/**
 * This class is NORMAL BLOCK (BRICK) in the game
 * TNT, LUCKY_BLOCK, METAL_BLOCK will extend and redefine attributes of this class
 * Blocks will be read from level file which has an array of blocks
 */
public class Block extends Entity{
    //x, y represent the top-left corner of the block (with padding)
    //example: (0, 0) => img is drawn from (1, 1) to (99, 49)
    //so there are 2 pixels between blocks and 1 pixel between block and frame border
    private int HP; // default = 2
    private boolean isAlive;
    public static final int DEFAULT_WIDTH = 100;
    public static final int DEFAULT_HEIGHT = 50;
    public static final int PADDING = 1;
    // Real width and height of block is (width - 2*PADDING) and (height - 2*PADDING)

    public Block(int x, int y, int width, int height, int HP) {
        super(x, y, width, height);
        this.HP = HP;
        this.isAlive = true;
        this.img = new ImageIcon("assets/images/block.png").getImage();
    }
    public Block(int x, int y, int HP) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.HP = HP;
        this.isAlive = true;
        this.img = new ImageIcon("assets/images/block.png").getImage();
    }
    public Block(int x, int y) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.HP = 2;
        this.isAlive = true;
        this.img = new ImageIcon("assets/images/block.png").getImage();
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
    
    public void render(Graphics g) {
        if (!isAlive) return;
        if (HP == 2) {
            g.setColor(Color.GREEN);
        } else if (HP == 1) {
            g.setColor(Color.YELLOW);
        } else {
            isAlive = false;
            return;
        }
        // g.fillRect(x + PADDING, y + PADDING, width - 2 * PADDING, height - 2 * PADDING);
        g.drawImage(img, x + PADDING, y + PADDING, width - 2 * PADDING, height - 2 * PADDING, null);
    }
}

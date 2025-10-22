package entity;
import Constant.Constant;
import manager.SoundManager;

import java.awt.*;

/**
 * This class is NORMAL BLOCK (BRICK) in the game
 * TNT, LUCKY_BLOCK, METAL_BLOCK will extend and redefine attributes of this class
 * Blocks will be read from level file which has an array of blocks
 */
public class Block extends Entity {
    //x, y represent the top-left corner of the block (with padding)
    //example: (0, 0) => img is drawn from (1, 1) to (99, 49)
    //so there are 2 pixels between blocks and 1 pixel between block and frame border
    private int HP; // Số máu hiện tại.
    private final int MAX_HP; // Số máu tối đa.
    private boolean isAlive; // Trạng thái block còn hay vỡ rồi.
    public int padding;

    // Real width and height of block is (width - 2*PADDING) and (height - 2*PADDING)

    /**
     * Constructor
     * @param x góc trên bên trái của block (bao gồm padding).
     * @param y góc trên bên trái của block (bao gồm padding).
     * @param width chiều rộng (bao gồm padding).
     * @param height chiều cao (bao gồm padding).
     * @param HP số máu của block.
     */
    public Block(int x, int y, int width, int height, int HP) {
        super(x, y, width, height);
        this.HP = HP;
        this.MAX_HP = HP;
        this.isAlive = true;
        this.img = Constant.BLOCK_IMG;
        this.padding = Constant.BLOCK_PADDING;
    }

    /**
     * Constructor with default width and height
     * @param x góc trên bên trái của block (bao gồm padding).
     * @param y góc trên bên trái của block (bao gồm padding).
     * @param HP số máu của block.
     */
    public Block(int x, int y, int HP) {
        super(x, y, Constant.BLOCK_DEFAULT_WIDTH, Constant.BLOCK_DEFAULT_HEIGHT);
        this.HP = HP;
        this.MAX_HP = HP;
        this.isAlive = true;
        this.img = Constant.BLOCK_IMG;
    }

    /**
     * Constructor with default width and height
     * @param x góc trên bên trái của block (bao gồm padding).
     * @param y góc trên bên trái của block (bao gồm padding).
     */
    public Block(int x, int y) {
        super(x, y, Constant.BLOCK_DEFAULT_WIDTH, Constant.BLOCK_DEFAULT_HEIGHT);
        this.HP = 2;
        this.MAX_HP = 2;
        this.isAlive = true;
        this.img = Constant.BLOCK_IMG;
    }

    /**
     * Set HP.
     * @param HP máu
     */
    public void setHP(int HP) {
        this.HP = HP;
    }

    /**
     * Render block.
     * @param g Graphics
     */
    public void render(Graphics g) {
        if (!isAlive) return;
        // g.fillRect(x + PADDING, y + PADDING, width - 2 * PADDING, height - 2 * PADDING);
        g.drawImage(img, x +  padding, y +  padding, width - 2 * padding, height - 2 *  padding, null);
        if (this.HP < this.MAX_HP) {
            int per10 = (int) ((1.0 * (MAX_HP - HP) / MAX_HP) * 10);
            g.drawImage(Constant.EFFECT_BREAKING_IMG[per10], x +  padding, y + padding, width - 2 * padding, height - 2 *  padding, null);
        }
    }

    /**
     * Xem khối còn không.
     * @return true nếu còn, false nếu vỡ rồi
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Lấy HP hiện tại của block.
     * @return HP hiện tại
     */
    public int getHP() {
        return HP;
    }

    /**
     * Giảm HP của block.
     * Nếu chết thì sẽ gọi hàm die().
     * Có thể Override ở các block đặc biệt (Không thể phá...).
     * @param decrease số HP giảm
     */

    public void decreaseHP(int decrease) {
        if (HP > 0) {
            HP -= decrease;
        }
        if (HP <= 0) {
            die();
        }
    }

    /**
     * Block chết.
     * Gọi hàm này khi HP <= 0.
     * Có thể Override ở các block đặc biệt (nổ...).
     */
    public void die() {
        isAlive = false;
        HP = 0;
        SoundManager.play("break");
    }
}

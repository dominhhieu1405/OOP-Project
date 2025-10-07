package entity;

public class BlockLucky extends Block {

    /**
     * Constructor.
     * @param x x
     * @param y y
     * @param width width
     * @param height height
     * @param HP HP
     */
    public BlockLucky(int x, int y, int width, int height, int HP) {
        super(x, y, width, height, HP);
        this.img = Constant.Constant.BLOCK_LUCKY_IMG;
    }

    /**
     * Constructor.
     * @param x x
     * @param y y
     * @param width width
     * @param height height
     */
    public BlockLucky(int x, int y, int width, int height) {
        super(x, y, width, height, 1);
        this.img = Constant.Constant.BLOCK_LUCKY_IMG;
    }

    /**
     * Constructor.
     * @param x x
     * @param y y
     */
    public BlockLucky(int x, int y) {
        super(x, y);
        this.width = 50;
        this.height = 50;
        this.img = Constant.Constant.BLOCK_LUCKY_IMG;
    }

    /**
     * Ghi đè để thêm các chức năng của block.
     */
    @Override
    public void die() {
        super.die();
        System.out.println("Lucky block");
        // TODO Thêm chức năng lucky
    }
}

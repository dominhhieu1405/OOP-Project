package entity;

public class BlockBedrock extends Block {
    /**
     * Constructor.
     * @param x x
     * @param y y
     * @param width width
     * @param height height
     */
    public BlockBedrock(int x, int y, int width, int height) {
        super(x, y, width, height, Integer.MAX_VALUE);
        // System.out.println("Bedrock block at (" + x + ", " + y + ") with size (" + width + ", " + height + ")");
        this.img = Constant.Constant.BLOCK_BEDROCK_IMG;
    }

    /**
     * Constructor.
     * @param x x
     * @param y y
     */
    public BlockBedrock(int x, int y) {
        super(x, y);
        this.width = 50;
        this.height = 50;
        this.img = Constant.Constant.BLOCK_BEDROCK_IMG;
    }

    /**
     * Override để không trừ máu => không thể phá hủy.
     * @param decrease số HP giảm
     */
    @Override
    public void decreaseHP(int decrease) {
        // Không giảm máu
    }
}

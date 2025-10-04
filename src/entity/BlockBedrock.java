package entity;

public class BlockBedrock extends Block {
    public BlockBedrock(int x, int y, int width, int height) {
        super(x, y, width, height, Integer.MAX_VALUE);
        this.img = Constant.Constant.BLOCK_BEDROCK_IMG;
    }
    public  BlockBedrock(int x, int y) {
        super(x, y);
        this.width = 50;
        this.height = 50;
        this.img = Constant.Constant.BLOCK_BEDROCK_IMG;
    }
    @Override
    public void decreaseHP(int decrease) {
        // Không giảm máu
    }
}

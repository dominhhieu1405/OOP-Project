package entity;

public class BlockLucky extends Block {
    public BlockLucky(int x, int y, int width, int height) {
        super(x, y, width, height, Integer.MAX_VALUE);
        this.img = Constant.Constant.BLOCK_LUCKY_IMG;
    }
    public  BlockLucky(int x, int y) {
        super(x, y);
        this.width = 50;
        this.height = 50;
        this.img = Constant.Constant.BLOCK_LUCKY_IMG;
    }
    @Override
    public void die() {
        super.die();
        System.out.println("Lucky block");
        // Viết code thêm cái lucky vào đây
    }
}

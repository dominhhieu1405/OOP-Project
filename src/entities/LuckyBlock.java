package entities;
import assets.*;
import utils.Constants;
public class LuckyBlock extends Block implements Breakable {
    public LuckyBlock(int x, int y) {
        super(x, y, Constants.LUCKYBLOCK_SPRITE);
    }

    private void dropPowerUp() {
        // Logic to drop a power-up
    }
    @Override
    public void destroy() {
        dropPowerUp();
        // Additional logic for destroying the block
    }
}
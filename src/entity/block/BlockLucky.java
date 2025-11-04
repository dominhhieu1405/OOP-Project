package entity.block;

import entity.powerUp.*;
import manager.PowerUpManager;

public class BlockLucky extends Block {
    private int powerNumber = -1; // Mặc định là -1, tức là random
    
    /**
     * Constructor.
     * @param x x
     * @param y y
     * @param width width
     * @param height height
     * @param HP HP
     */
    
    public BlockLucky(int x, int y, int width, int height, int HP, int powerNumber) {
        super(x, y, width, height, HP);
        this.powerNumber = powerNumber;
        this.img = Constant.Constant.BLOCK_LUCKY_IMG;
    }
    
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
        // System.out.println("Lucky block");
        int sx = this.x + this.width / 2 - 18;
        int xy = this.y + this.height / 2 - 18;

        // System.out.println("SX: " + sx);
        // System.out.println("SY: " + xy);


        PowerUp powerUp;
        if (powerNumber == 0) {
            powerUp = new PowerUpBallExpand(sx, xy);
        } else if (powerNumber == 1) {
            powerUp = new PowerUpBallExtraLife(sx, xy);
        } else if (powerNumber == 2) {
            powerUp = new PowerUpBallFast(sx, xy);
        } else if (powerNumber == 3) {
            powerUp = new PowerUpBallFire(sx, xy);
        } else if (powerNumber == 4) {
            powerUp = new PowerUpBallShrink(sx, xy);
        } else if (powerNumber == 5) {
            powerUp = new PowerUpBallSlow(sx, xy);
        } else if (powerNumber == 6) {
            powerUp = new PowerUpCatchBall(sx, xy);
        } else if (powerNumber == 7) {
            powerUp = new PowerUpPaddleExpand(sx, xy);
        } else if (powerNumber == 8) {
            powerUp = new PowerUpPaddleFast(sx, xy);
        } else if (powerNumber == 9){
            powerUp = new PowerUpPaddleShrink(sx, xy);
        } else if (powerNumber == 10){
            powerUp = new PowerUpPaddleShrink(sx, xy);
        } else {
            powerUp = new PowerUpRandom(sx, xy);
        }

        PowerUpManager.getInstance().addPowerUp(powerUp);
    }
}

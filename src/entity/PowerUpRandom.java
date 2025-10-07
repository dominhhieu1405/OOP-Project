package entity;

public class PowerUpRandom extends PowerUp {
    public PowerUp powerUp;

    public PowerUpRandom(int x, int y) {
        super(x, y, 36, 36, "assets/images/PowerUp/Random.png");
        this.duration = 10000; // hiệu lực 10 giây
    }

    @Override
    public void activate() {

        // Lấy ngẫu nhiên từ 0 đến 10
        int rand = (int) (Math.random() * 10);
        System.out.println("Random powerup: " + rand);
        if (rand == 0) {
            powerUp = new PowerUpBallExpand(x, y);
        } else if (rand == 1) {
            powerUp = new PowerUpBallExtraLife(x, y);
        } else if (rand == 2) {
            powerUp = new PowerUpBallFast(x, y);
        } else if (rand == 3) {
            powerUp = new PowerUpBallFire(x, y);
        } else if (rand == 4) {
            powerUp = new PowerUpBallShrink(x, y);
        } else if (rand == 5) {
            powerUp = new PowerUpBallSlow(x, y);
        } else if (rand == 6) {
            powerUp = new PowerUpCatchBall(x, y);
        } else if (rand == 7) {
            powerUp = new PowerUpPaddleExpand(x, y);
        } else if (rand == 8) {
            powerUp = new PowerUpPaddleFast(x, y);
        } else if (rand == 9){
            powerUp = new PowerUpPaddleShrink(x, y);
        } else {
            powerUp = new PowerUpPaddleSlow(x, y);
        }
        this.duration = powerUp.duration;
        powerUp.activate();

        isActive = true;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void deactivate() {
        powerUp.deactivate();

        isActive = false;
    }

    @Override
    public String toString() {
        return powerUp.toString();
    }
}

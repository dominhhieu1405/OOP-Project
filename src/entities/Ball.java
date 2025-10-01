package entities;
import utils.Constants;
public class Ball extends Entity implements Movable {
    private int speed;
    public Ball(int x, int y) {
        super(x, y, Constants.BALL_SPRITE);
    }
}
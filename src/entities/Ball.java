package entities;
import utils.Constants;
import assets.Sprite;
public class Ball extends Entity implements Movable {
    public Ball(int x, int y) {
        super(x, y, Constants.BALL_SPRITE);
    }
}
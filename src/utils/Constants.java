package utils;
import assets.Sprite;
// các hằng số được sử dụng trong trò chơi

public class Constants {
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 800;
    public static final int FPS = 60;
    public static final String GAME_TITLE = "Arkanoid";
    // Entity dimensions
    // Ball
    public static final int BRICK_WIDTH = 100;
    public static final int BRICK_HEIGHT = 30;
    public static final int PADDLE_WIDTH = 120;
    public static final int PADDLE_HEIGHT = 20;

    // Sprite paths
    public static final Sprite BACKGROUND_SPRITE = new Sprite("background.png");
    public static final Sprite BALL_SPRITE = new Sprite("ball.png");
    public static final Sprite PADDLE_SPRITE = new Sprite("paddle.png");
    public static final Sprite BRICK_SPRITE = new Sprite("brick.png");
    public static final Sprite LUCKYBLOCK_SPRITE = new Sprite("powerup.png");
    public static final Sprite IRON_SPRITE = new Sprite("iron.png");
    public static final Sprite TNT_SPRITE = new Sprite("tnt.png");
}

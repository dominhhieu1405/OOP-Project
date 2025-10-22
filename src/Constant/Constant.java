package Constant;
import javax.swing.ImageIcon;
import java.awt.Image;
public class Constant {
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;

    public static final int PADDLE_WIDTH = 100;
    public static final int PADDLE_HEIGHT = 20;
    public static final int PADDLE_Y_OFFSET = 50;

    public static final int BALL_RADIUS = 10;
    public static final int TOTAL_BALL_HEART = 10;

    public static final int BLOCK_DEFAULT_WIDTH = 100; // Chiều rộng (Đã bao gồm padding).
    public static final int BLOCK_DEFAULT_HEIGHT = 50; // Chiều cao (Đã bao gồm padding).
    public static final int BLOCK_DEFAULT_HP = 2; // Số máu mặc định của block.
    public static final int BLOCK_PADDING = 2; // Khoảng cách giữa các block với nhau và với viền khung hình.

    public static final Image BALL_IMG = new ImageIcon("assets/images/snowball.png").getImage();
    public static final Image FIREBALL_IMG = new ImageIcon("assets/images/fireball.png").getImage();
    public static final Image BACKGROUND_IMG = new ImageIcon("assets/images/background.jpg").getImage();
    public static final Image FAVICON = new ImageIcon("assets/images/favicon.png").getImage();
    public static final Image HEART_IMG = new ImageIcon("assets/images/heart.png").getImage();
    public static final Image HEART0_IMG = new ImageIcon("assets/images/heart0.png").getImage();
    public static final Image BLOCK_IMG = new ImageIcon("assets/images/block.png").getImage();
    public static final Image BLOCK_BOMB_IMG = new ImageIcon("assets/images/tnt.png").getImage();
    public static final Image BLOCK_BEDROCK_IMG = new ImageIcon("assets/images/bedrock.png").getImage();
    public static final Image BLOCK_LUCKY_IMG = new ImageIcon("assets/images/luckyblock.png").getImage();
    public static final Image ARKANOID_IMG = new ImageIcon("assets/images/arkanoid.jpg").getImage();
    public static final Image GAMEOVER_BACKGROUND_IMG = new ImageIcon("assets/images/gameoverBackground.png").getImage();
    public static final Image MENU_BUTTON_IMG = new ImageIcon("assets/images/menuButton.png").getImage();
    public static final Image RESPAWN_BUTTON_IMG = new ImageIcon("assets/images/respawnButton.png").getImage();

    public static final String MAPS_PATH = "data/maps/";

    public static final Image[] EFFECT_BREAKING_IMG = new Image[10];

    static {
        for (int i = 0; i < 10; i++) {
            EFFECT_BREAKING_IMG[i] = new ImageIcon("assets/images/breaking" + i + ".png").getImage();
        }
    }

}

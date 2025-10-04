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

    public static final int BLOCK_ROWS = 5;
    public static final int BLOCK_COLUMNS = 10;

    public static final int BLOCK_WIDTH = 60;
    public static final int BLOCK_HEIGHT = 20;

    public static final Image BACKGROUND_IMG = new ImageIcon("assets/images/background.jpg").getImage();
    public static final Image FAVICON = new ImageIcon("assets/images/favicon.png").getImage();
    public static final Image HEART_IMG = new ImageIcon("assets/images/heart.png").getImage();
    public static final Image HEART0_IMG = new ImageIcon("assets/images/heart0.png").getImage();
    public static final Image BLOCK_IMG = new ImageIcon("assets/images/block.png").getImage();
    public static final Image BLOCK_BOMB_IMG = new ImageIcon("assets/images/tnt.png").getImage();
    public static final Image BLOCK_BEDROCK_IMG = new ImageIcon("assets/images/bedrock.png").getImage();
    public static final Image BLOCK_LUCKY_IMG = new ImageIcon("assets/images/luckyblock.png").getImage();
}

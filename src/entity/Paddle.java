package entity;
import Constant.Constant;
import java.awt.*;
import javax.swing.ImageIcon;
public class Paddle extends Entity {
    private static Paddle instance;
    public int speed = 10;

    private Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.img = new ImageIcon("assets/images/paddle.png").getImage();
    }
    // Singleton pattern
    public static Paddle getInstance() {
        if (instance == null) {
            instance = new Paddle(Constant.FRAME_WIDTH / 2 - Constant.PADDLE_WIDTH / 2, Constant.FRAME_HEIGHT - Constant.PADDLE_Y_OFFSET - Constant.PADDLE_HEIGHT, Constant.PADDLE_WIDTH, Constant.PADDLE_HEIGHT);
            System.out.println("Init Paddle: " + instance.x + "," + instance.y);
        }
        return instance;
    }

    public void render(Graphics g) {
        g.setColor(java.awt.Color.RED);
        g.drawImage(img, x, y, width, height, null);
    }

    public void moveLeft() {
        if (x - speed >= 0) {
            x -= speed;
        }
        // System.out.println("Move Left: " + x + "->" + (x + width));
    }
    public void moveRight() {
        if (x + width + speed < Constant.FRAME_WIDTH) {
            x += speed;
        }
        // System.out.println("Move Right: " + x + "->" + (x + width));
    }
}

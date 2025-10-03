package entity;
import Constant.Constant;
import java.awt.*;
import javax.swing.ImageIcon;
public class Paddle extends Entity {
    private static Paddle instance;
    public double speed = 200; // pixels per second
    private boolean movingLeft = false;
    private boolean movingRight = false;
    long lastTime;
    private double posX;


    public void setMovingLeft(boolean state) {
        if (!movingLeft) {
            lastTime = System.currentTimeMillis();
        }
        movingLeft = state;
    }
    public void setMovingRight(boolean state) {
        if (!movingRight) {
            lastTime = System.currentTimeMillis();
        }
        movingRight = state;
    }

    private Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.posX = x;
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
    public void update() {
        double dt = (System.currentTimeMillis() - lastTime) / 1000.0;
        lastTime = System.currentTimeMillis();

        if (movingLeft && posX - speed * dt >= 0) {
            posX -= speed * dt;
        }
        if (movingRight && posX + width + speed * dt < Constant.FRAME_WIDTH) {
            posX += speed * dt;
        }
        if (!Ball.getInstance().getIsRunning()) {
            Ball.getInstance().setX(Paddle.getInstance().getX() + Paddle.getInstance().getWidth() / 2);
        }
        this.x = (int) Math.round(posX); // Cho cái này lên trên nếu muốn không mượt hơn
    }

    public void render(Graphics g) {
//        g.setColor(java.awt.Color.RED);
//        g.drawImage(img, x, y, width, height, null);
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

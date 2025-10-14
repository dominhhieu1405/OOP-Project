package entity;
import Constant.Constant;
import java.awt.*;
import javax.swing.ImageIcon;
public class Paddle extends Entity {
    private static Paddle instance; // Singleton instance
    public double speed = 600; // pixels per second
    private boolean movingLeft = false; // Có đang di chuyển trái hay không
    private boolean movingRight = false; // Có đang di chuyển phải hay không
    private long lastTime; // Thời gian lần cuối cập nhật vị trí
    private double posX; // Vị trí X thực tế (có thể là số thập phân)

    /**
     * Đặt speed.
     * @param speed speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Lấy speed.
     * @return speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Get trạng thái di chuyển trái.
     */
    public boolean isMovingLeft() {
        return movingLeft;
    }

    /**
     * Get trạng thái di chuyển phải.
     */
    public boolean isMovingRight() {
        return movingRight;
    }

    /**
     * Set trạng thái di chuyển trái.
     * @param state true nếu đang di chuyển, false nếu dừng
     */
    public void setMovingLeft(boolean state) {
        if (!movingLeft) {
            lastTime = System.currentTimeMillis();
        }
        movingLeft = state;
    }

    /**
     * Set trạng thái di chuyển phải.
     * @param state true nếu đang di chuyển, false nếu dừng
     */
    public void setMovingRight(boolean state) {
        if (!movingRight) {
            lastTime = System.currentTimeMillis();
        }
        movingRight = state;
    }

    /**
     * Constructor
     * @param x x
     * @param y y
     * @param width chiều rộng
     * @param height chiều cao
     */
    private Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.posX = x;
        this.img = new ImageIcon("assets/images/paddle.png").getImage();
    }

    /**
     * Lấy instance của Paddle (Singleton pattern)
     * @return instance của Paddle
     */
    public static Paddle getInstance() {
        if (instance == null) {
            instance = new Paddle(Constant.FRAME_WIDTH / 2 - Constant.PADDLE_WIDTH / 2, Constant.FRAME_HEIGHT - Constant.PADDLE_Y_OFFSET - Constant.PADDLE_HEIGHT, Constant.PADDLE_WIDTH, Constant.PADDLE_HEIGHT);
            // System.out.println("Init Paddle: " + instance.x + "," + instance.y);
        }
        return instance;
    }

    /**
     * Reset vị trí và trạng thái của paddle về mặc định.
     */
    public void reset(){
        this.x = Constant.FRAME_WIDTH / 2 - Constant.PADDLE_WIDTH / 2;
        this.y = Constant.FRAME_HEIGHT - Constant.PADDLE_Y_OFFSET - Constant.PADDLE_HEIGHT;
        this.posX = x;
        this.movingLeft = false;
        this.movingRight = false;
        this.lastTime = System.currentTimeMillis();
    }

    /**
     * Di chuyển paddle theo thời gian thực.
     */
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

    /**
     * Vẽ paddle
     * @param g Graphics
     */
    public void render(Graphics g) {
//        g.setColor(java.awt.Color.RED);
//        g.drawImage(img, x, y, width, height, null);
        g.drawImage(img, x, y, width, height, null);
    }
}

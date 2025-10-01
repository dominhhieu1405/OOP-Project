package entities;
import static utils.Constants.*;
public class Ball extends Entity implements Movable {
    private int speed; // Tốc độ di chuyển
    private int angle; // Góc di chuyển. Theo chiều kim đồng hồ. Mốc 0 độ thẳng lên trên
    private int r; // Bán kính bóng

    // Getter & Setter
    public int getSpeed() {
        return this.speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public  int getAngle() {
        return this.angle;
    }
    public void setAngle(int angle) {
        angle = angle % 360; // Giới hạn góc trong khoảng 0-359 độ
        this.angle = angle;
    }

    public int getR() {
        return this.r;
    }


    /**
     * Hàm di chuyển, tốc độ tính theo pixel mỗi lần gọi.
     * Chiều dương trái sang phải, trên xuống dưới.
     * Quy ra Vx, Vy theo góc + tốc độ:
        * Vx = speed * cos(angle)
        * Vy = speed * sin(angle)
     */
    public void move() {
        int velocityX = (int) Math.round(this.speed * Math.sin(Math.toRadians(this.angle)));
        int velocityY = (int) Math.round(-this.speed * Math.cos(Math.toRadians(this.angle)));

        this.x += velocityX;
        this.y += velocityY;
        // Kiểm tra xem có đi quá tường không. Vì bóng là hình cầu nên tâm cách tường 1 đoạn = r thì là chạm tường.
        if (this.x <= r) {
            this.x = r; // Đặt lại vị trí để không bị lệch
        } else if (this.x >= WINDOW_WIDTH - r) {
            this.x = WINDOW_WIDTH - r; // Đặt lại vị trí để không bị lệch
        } if (this.y <= r) {
            this.y = r; // Đặt lại vị trí để không bị lệch
        }
        this.collisionWithWall();
//        this.collisionWithPaddle();
    }


    public boolean Die() {
        return this.y + 2 * r > WINDOW_HEIGHT;
    }

    /**
     * Xử lý va chạm với tường.
     * Đổi hướng đối xứng với trục vuông góc với tường.
     */
    public void collisionWithWall() {
        if (this.y <= r) { // Tường trên
            this.angle = (180 - this.angle + 360) % 360;
        } else if (this.x <= r) { // Tường trái
            this.angle = (360 - this.angle + 360) % 360;
        } else if (this.x >= WINDOW_WIDTH - r) { // Tường phải
            this.angle = (360 - this.angle + 360) % 360;
        }
    }

    public void collisionWithPaddle(Paddle p) {
        // Rectangle paddle
        int px = p.getX();
        int py = p.getY();
        int pw = p.getWidth();
        int ph = p.getHeight();

        // Tâm bóng
        int cx = this.x;
        int cy = this.y;

        // Kiểm tra va chạm
        boolean collide = (cx + r >= px && cx - r <= px + pw &&
                cy + r >= py && cy - r <= py + ph);

        if (collide) {
            // Phản xạ
            this.angle = (360 - this.angle) % 360;
        }
    }


}
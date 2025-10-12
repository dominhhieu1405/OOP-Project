package entity;
import Constant.Constant;
import manager.SoundManager;
import manager.BlockManager;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Ball extends Entity  {

    //x and y represent the center of the ball

    private double speed;
    private int velocityX, velocityY; // Vận tốc theo trục x và y
    private boolean isAlive; // trạng thái sống/chết của bóng
    private boolean isRunning; // trạng thái chạy/dừng của bóng
    long lastTime; // thời gian lần cuối cập nhật vị trí
    long lastEvent; // thời gian lần cuối xảy ra va chạm
    private int RADIUS = Constant.BALL_RADIUS; // bán kính của bóng
    private static final int TOTAL_HEALTH = Constant.TOTAL_BALL_HEART; // tổng số mạng của bóng
    public int health; // mạng hiện tại của bóng
    private int damage; // sát thương của bóng
    private boolean isFire = false; // trạng thái bóng lửa

    private static Ball instance;

    /**
     * Constructor của Ball;
     * @param x tọa độ x của tâm bóng
     * @param y tọa độ y của tâm bóng
     * @param width chiều rộng của bóng
     * @param height chiều cao của bóng
     */
    private Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.speed = 1;
        this.velocityX = 150;
        this.velocityY = 0;
        this.health = 10;
        this.damage = 1;
        this.img = Constant.BALL_IMG;
        this.lastEvent = System.currentTimeMillis();
    }


    /**
     * Lấy instance của Ball.
     * @return instance của Ball
     */
    public static Ball getInstance() {
        if (instance == null) {
            // Khởi tạo ball ở center của frame, phía trên paddle
            int ballCenterX = Constant.FRAME_WIDTH / 2;
            int ballCenterY = Constant.FRAME_HEIGHT - Constant.PADDLE_Y_OFFSET - Constant.PADDLE_HEIGHT - Constant.BALL_RADIUS - 50;
            
            instance = new Ball(ballCenterX, ballCenterY, Constant.BALL_RADIUS * 2, Constant.BALL_RADIUS * 2);
            // System.out.println("Ball init center: " + instance.x + ", " + instance.y);
            instance.velocityX = 150;
            instance.velocityY = -150;
            instance.isAlive = true;
            instance.isRunning = false;
        }
        return instance;
    }

    /**
     * Lấy tốc độ của bóng.
     * @return tốc độ của bóng
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Đặt tốc độ của bóng.
     * @param speed tốc độ mới
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Kiểm tra trạng thái bóng lửa.
     * @return true nếu là bóng lửa, false nếu không phải
     */
    public boolean isFire() {
        return isFire;
    }

    /**
     * Đặt trạng thái bóng lửa.
     * @param fire true để đặt thành bóng lửa, false để đặt về bình thường
     */
    public void setFire(boolean fire) {
        isFire = fire;
        if (fire) {
            this.img = Constant.FIREBALL_IMG;
        } else {
            this.img = Constant.BALL_IMG;
        }
    }

    /**
     * Vẽ bóng và thanh máu.
     * @param g Graphics để vẽ
     */
    public void render(Graphics g) {
//        g.setColor(Color.BLUE);
//        g.fillOval(x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS);
        // Render thanh máu
        for (int i = 0; i < TOTAL_HEALTH; i++) {
            if (i < health) {
                g.drawImage(Constant.HEART_IMG, 10 + i * 20, 10, 18, 18, null);
            } else {
                g.drawImage(Constant.HEART0_IMG, 10 + i * 20, 10, 18, 18, null);
            }
        }

        g.drawImage(img, x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS, null);
    }

    /**
     * Lấy bán kính của bóng.
     * @return bán kính của bóng
     */
    public int getRADIUS() {
        return RADIUS;
    }

    /**
     * Đặt bán kính của bóng.
     * @param RADIUS bán kính mới
     */
    public void setRADIUS(int RADIUS) {
        this.RADIUS = RADIUS;
        this.width = 2 * RADIUS;
        this.height = 2 * RADIUS;
    }

    /**
     * Kiểm tra trạng thái của bóng.
     * @return true nếu bóng còn sống, false nếu chết
     */
    public boolean getIsAlive() {
        return isAlive;
    }

    /**
     * Kiểm tra bóng đã chạy chưa.
     * @return true nếu bóng đang chạy, false nếu chưa chạy
     */
    public boolean getIsRunning() {
        return isRunning;
    }

    /**
     * Lấy vận tốc theo trục x của bóng.
     * @param velocityX vận tốc theo trục x
     */
    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    /**
     * Lấy vận tốc theo trục y của bóng.
     * @param velocityY vận tốc theo trục y
     */
    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    /**
     * Đặt trạng thái sống/chết của bóng.
     * @param isAlive true nếu bóng sống, false nếu chết
     */
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    /**
     * Đặt trạng thái chạy/dừng của bóng.
     * @param isRunning true nếu bóng đang chạy, false nếu dừng
     */
    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * Bắt đầu cho bóng chạy.
     * Đặt vận tốc đầu theo Vx, Vy.
     * Khởi tạo thời gian vào các biển lastTime, lastEvent.
     */
    public void runBall() {
        if (!this.isRunning) {
            // Nếu bóng ở phần bên nào thì tốc độ x hướng về bên đó.
            if (this.velocityY == 0) {
                this.velocityY = -150;
            }
            if (this.x <= Constant.FRAME_WIDTH / 2 - RADIUS) {
                this.velocityX = -150;
            } else {
                this.velocityX = 150;
            }
            this.isRunning = true;
            lastTime = System.currentTimeMillis();
            lastEvent = System.currentTimeMillis();
        }
    }

    /**
     * Đặt bóng về trạng thái mặc định.
     */
    public void reset() {
        this.x = Paddle.getInstance().getX() + Paddle.getInstance().getWidth() / 2;
        this.y = Constant.FRAME_HEIGHT - Constant.PADDLE_HEIGHT - RADIUS - 50;
        this.velocityX = 150;
        this.velocityY = 0;
        this.isRunning = false;
        this.isAlive = true;
    }

    /**
     * Hồi sinh bóng nếu còn mạng.
     */
    public void respawn() {
        if (this.health > 0) {
            this.health--;
            System.out.println("Hoi sinh, con lai: " + this.health);
            this.reset();
        } else {
            this.isAlive = false;
            this.isRunning = false;
            System.out.println("Game Over");
        }
    }

    /**
     * Di chuyển bóng.
     * Xử lý va chạm với paddle, tường và block.
     * Nếu bóng chạm đáy thì gọi spawn().
     * Biến lastTime để tính khoảng cách di chuyển theo thời gian thực.
     * Biến lastEvent để tránh va chạm nhiều lần.
     */
    public void update() {
        if (this.isAlive && this.isRunning) {
//            x += velocityX;
//            y += velocityY;
            double dt = (System.currentTimeMillis() - lastTime) / 1000.0;
            lastTime = System.currentTimeMillis();
            x += (int) (velocityX * dt * speed);
            y += (int) (velocityY * dt * speed);

            if (System.currentTimeMillis() - lastEvent > 100) {

//            if (collisionWithPaddle()) { velocityY = -velocityY; }
                if (!this.collisionWithPaddle().equals("NONE")) {
                    // System.out.println("Dapvaovan");
                    String side = collisionWithPaddle();
                    if (side.equals("TOP") && velocityY > 0) {
                        velocityY = -velocityY;
//                        y = Paddle.getInstance().getY() - RADIUS; // đặt lại trên paddle
                        // Thêm tí random cho velocityX
                        int randomFactor = (int)(Math.random() * 100) - 50;
                        velocityX += randomFactor;
                        // Giới hạn velocityX trong khoảng [-180, 180]
                        if (velocityX > 180) velocityX = 180;
                        if (velocityX < -180) velocityX = -180;
                        // Đổi vy để bóng không bị quá chậm
                        if (Math.abs(velocityX) < 100) {
                            if (velocityX < 0) {
                                velocityX = -100;
                            } else {
                                velocityX = 100;
                            }
                        }

                    } else if (side.equals("LEFT") || side.equals("RIGHT")) {
                        velocityX = -velocityX;
                    }
                    SoundManager.play("click");
                    lastEvent = System.currentTimeMillis();
                }
            }
            if (collisionWithUpperWall()) {
                velocityY = -velocityY;
                SoundManager.play("click");
            }
            if (collisionWithSideWall()) {
                velocityX = -velocityX;
                // Đảm bảo ball không bị "stuck" trong tường
                if (x + RADIUS >= Constant.FRAME_WIDTH) {
                    // System.out.println(2);
                    x = Constant.FRAME_WIDTH - RADIUS; // Đẩy ball ra khỏi tường phải
                }
                if (x - RADIUS <= 0) {
                    // System.out.println(1);
                    x = RADIUS; // Đẩy ball ra khỏi tường trái
                }
                SoundManager.play("click");
            }

            // Check đã chết chưa
            if (y - RADIUS > Constant.FRAME_HEIGHT) {
                this.isAlive = false;
                this.isRunning = false;
                SoundManager.play("dead");
                // System.out.println("Chet me roi con gi nua");
                this.respawn();
            }

            // Check va chạm với block sẽ được xử lý trong GamePanel
            checkBlockCollision();
        }
    }

    /**
     * Kiểm tra va chạm với các block.
     * Nếu có va chạm, đổi hướng bóng và giảm HP của block.
     */
    private void checkBlockCollision() {
        BlockManager bm = BlockManager.getInstance();
        for (Block b : bm.getBlocks()) {
            if (b.isAlive()) {
                String side = this.getCollisionSide(b);
                if (!side.equals("NONE")) {
                    lastEvent = System.currentTimeMillis();
                    if (this.isFire) {
                        b.die();
                    } else {
                        // Xử lý va chạm
                        if (side.equals("TOP") && velocityY > 0) {
                            velocityY = -velocityY;
                        } else if (side.equals("BOTTOM") && velocityY < 0) {
                            velocityY = -velocityY;
                        } else if (side.equals("LEFT") && velocityX > 0) {
                            velocityX = -velocityX;
                        } else if (side.equals("RIGHT") && velocityX < 0) {
                            velocityX = -velocityX;
                        }
                        SoundManager.play("click");
                        b.decreaseHP(this.damage);
                        break; // Chỉ xử lý va chạm với một block tại một thời điểm
                    }
                }
            }
        }
    }

    /**
     * Kiểm tra va chạm với tường trên.
     * @return true nếu va chạm, false nếu không
     */
    private boolean collisionWithUpperWall() {
        return y - RADIUS <= 0;
    }

    /**
     * Kiểm tra va chạm với tường bên.
     * @return true nếu va chạm, false nếu không
     */
    private boolean collisionWithSideWall() {
        boolean rightWall = x + RADIUS >= Constant.FRAME_WIDTH;
        boolean leftWall = x - RADIUS <= 0;
        return (rightWall || leftWall);
    }

    /**
     * Kiểm tra va chạm với paddle.
     * @return phía va chạm hoặc "NONE" nếu không va chạm.
     */
    private String collisionWithPaddle() {
        Paddle paddle = Paddle.getInstance();
        return this.getCollisionSide(paddle);
    }

    /**
     * Lấy phía va chạm với thực thể khác.
     * Tạo một thực thể tạm thời có kích thước bằng hình vuông bao quanh bóng.
     * Gọi phương thức getCollisionSide của thực thể tạm thời.
     * @param other thực thể khác
     * @return phía va chạm hoặc "NONE" nếu không va chạm
     */
    @Override
    public String getCollisionSide(Entity other) {
        Entity e = new Entity(this.x - RADIUS, this.y - RADIUS, 2 * RADIUS, 2 * RADIUS);
        return e.getCollisionSide(other);
    }
}

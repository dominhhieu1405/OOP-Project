package entity;
import Constant.Constant;
import entity.block.Block;
import manager.SoundManager;
import manager.BlockManager;

import java.awt.Graphics;

public class Ball extends Entity  {
    //x and y represent the center of the ball
    private double speed;
    private final double velocity = 350.0; // Tốc độ gốc (Hợp của vx, vy)
    private int velocityX, velocityY; // Vận tốc theo trục x và y
    private boolean isAlive; // trạng thái sống/chết của bóng (tính theo mạng)
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
        this.velocityX = (int) ((int) this.velocity / Math.sqrt(2));
        this.velocityY = (int) ((int) this.velocity / Math.sqrt(2));
        this.health = Constant.TOTAL_BALL_HEART;
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
            instance.velocityX = 150;
            instance.velocityY = -150;
            instance.isAlive = true;
            instance.isRunning = false;
        }
        return instance;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

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

    public int getRADIUS() {
        return RADIUS;
    }

    public void setRADIUS(int RADIUS) {
        this.RADIUS = RADIUS;
        this.width = 2 * RADIUS;
        this.height = 2 * RADIUS;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    /**
     * Lấy vận tốc theo trục x của bóng.
     * @param velocityX vận tốc theo trục x
     */
    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
        // Bảo toàn V
        double sqrt = Math.sqrt(velocity * velocity - velocityX * velocityX);
        if (this.velocityY < 0)
            this.velocityY = (int) sqrt * -1;
        else {
            this.velocityY = (int) sqrt;
        }
    }

    /**
     * Lấy vận tốc theo trục y của bóng.
     * @param velocityY vận tốc theo trục y
     */
    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
        // Bảo toàn V
        double sqrt = Math.sqrt(velocity * velocity - velocityY * velocityY);
        if (this.velocityX < 0)
            this.velocityX = (int) sqrt * -1;
        else {
            this.velocityX = (int) sqrt;
        }
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
        this.lastEvent = System.currentTimeMillis();
        this.lastTime = System.currentTimeMillis();
    }

    /**
     * Vẽ bóng và thanh máu.
     * @param g Graphics để vẽ
     */
    public synchronized void render(Graphics g) {
        int maxHealth = Math.max(0, Constant.TOTAL_BALL_HEART);
        int visibleHealth = Math.max(0, Math.min(this.health, maxHealth));
        for (int i = 0; i < maxHealth; i++) {
            if (i < visibleHealth) {
                g.drawImage(Constant.HEART_IMG, 10 + i * 20, 10, 18, 18, null);
            } else {
                g.drawImage(Constant.HEART0_IMG, 10 + i * 20, 10, 18, 18, null);
            }
        }
        g.drawImage(img, x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS, null);
    }

    /**
     * Bắt đầu cho bóng chạy.
     * Đặt vận tốc đầu theo Vx, Vy.
     * Khởi tạo thời gian vào các biển lastTime, lastEvent.
     */
    public void runBall() {
        if (!this.isRunning) {
            int sqr = (int) Math.sqrt(velocity * velocity / 2);
            this.velocityY = -sqr;
            System.out.println("Run");
            if (this.x + this.RADIUS - Constant.FRAME_WIDTH / 2 <= 10 && this.x - this.RADIUS - Constant.FRAME_WIDTH / 2 >= -10) {
                this.setVelocityX(0);
                this.velocityY = - sqr;
                System.out.println("Run center");
            } else if (this.x <= Constant.FRAME_WIDTH / 2 - RADIUS) {
                this.velocityX = -sqr;
                this.setVelocityX((int) (this.velocity / Math.sqrt(2) * -1));
            } else {
                this.velocityX = sqr;
                this.setVelocityX((int) (this.velocity / Math.sqrt(2)));
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
        this.velocityX = (int) (this.velocity / Math.sqrt(2));
        this.velocityY = (int) (this.velocity / Math.sqrt(2));
        this.setSpeed(1);
        this.damage = 1;
        this.isFire = false;
        this.img = Constant.BALL_IMG;
        this.health = Constant.TOTAL_BALL_HEART;
        this.isRunning = false;
        this.isAlive = true;
    }

    /**
     * Hồi sinh bóng nếu còn mạng.
     */
    public void respawn() {
        System.out.println("Respawn called, health: " + this.health);
        if (this.health > 1) {
            this.x = Paddle.getInstance().getX() + Paddle.getInstance().getWidth() / 2;
            this.y = Constant.FRAME_HEIGHT - Constant.PADDLE_HEIGHT - RADIUS - 50;
            this.velocityX = (int) (this.velocity / Math.sqrt(2));
            this.velocityY = (int) (this.velocity / Math.sqrt(2));
            this.isRunning = false;
            this.health--;
            System.out.println("Ball respawned at: " + this.x + ", " + this.y + " with health: " + this.health);
        } else {
            this.isAlive = false;
            this.isRunning = false;
            System.out.println("Game Over");
        }
    }

    /**
     * Update vị trí bóng.
     */
    public synchronized void update() {
        if (this.isAlive && this.isRunning) {
            double dt = (System.currentTimeMillis() - lastTime) / 1000.0;
            lastTime = System.currentTimeMillis();

            double nextX = x + velocityX * dt * speed;
            double nextY = y + velocityY * dt * speed;

            // Flags để biết có va chạm với tường không (để phát âm thanh / tránh double flip)
            boolean collidedSide = false;
            boolean collidedTop = false;

            // Xử lý va chạm với tường bên (dự đoán vị trí tiếp theo)
            if (nextX + RADIUS > Constant.FRAME_WIDTH) {
                nextX = Constant.FRAME_WIDTH - RADIUS - 1;
                velocityX = -Math.abs(velocityX);
                collidedSide = true;
            } else if (nextX - RADIUS < 0) {
                nextX = RADIUS + 1;
                velocityX = Math.abs(velocityX);
                collidedSide = true;
            }

            // Xử lý va chạm với tường trên
            if (nextY - RADIUS < 0) {
                nextY = RADIUS + 1;
                velocityY = Math.abs(velocityY);
                collidedTop = true;
            }

            // Áp dụng vị trí sau khi xử lý tường
            x = (int) nextX;
            y = (int) nextY;

            // Kiểm tra va chạm paddle / wall sound / tránh va chạm liên tiếp quá nhanh
            if (System.currentTimeMillis() - lastEvent > 100) {
                String paddleSide = collisionWithPaddle();
                if (!paddleSide.equals("NONE")) {
                    if (paddleSide.equals("TOP") && velocityY > 0) {
                        velocityY = -Math.abs(velocityY);

                        double percent = (double) (x - Paddle.getInstance().getX()) / Paddle.getInstance().getWidth();

                        double maxAngle = 60;
                        double angle = (percent - 0.5) * 2 * maxAngle; // từ -max → +max

                        // Giữ tốc độ tổng không đổi
                        double speed = Math.sqrt(velocityX * velocityX + velocityY * velocityY);
                        double rad = Math.toRadians(angle);
                        this.setVelocityX((int)(speed * Math.sin(rad)));
                        this.setVelocityY((int)(-speed * Math.cos(rad)));

                        if (Paddle.getInstance().isMovingLeft()) {
                            velocityX -= 10;
                        } else if (Paddle.getInstance().isMovingRight()) {
                            velocityX += 10;
                        }
                        if (Math.abs(this.velocityX) <= 50) {
                            this.setVelocityX(this.velocityX < 0 ? -50 : 50);
                        }
                        if (Math.abs(this.velocityY) <= 50) {
                            this.setVelocityY(this.velocityY < 0 ? -50 : 50);
                        }
                    } else if (paddleSide.equals("LEFT") || paddleSide.equals("RIGHT")) {
                        velocityX = -velocityX;
                    }
                    
                    SoundManager.play("click");
                    lastEvent = System.currentTimeMillis();
                }

                // Phát âm thanh cho va chạm tường (nếu có)
                if (collidedTop) {
                    SoundManager.play("click");
                    lastEvent = System.currentTimeMillis();
                }
                if (collidedSide) {
                    SoundManager.play("click");
                    lastEvent = System.currentTimeMillis();
                }
            }

            // Check đã chết chưa (rơi quá đáy)
            if (y - RADIUS > Constant.FRAME_HEIGHT) {
                this.isRunning = false;
                SoundManager.play("dead");
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
                        double rand = Math.random() * 3;
                        if (side.equals("TOP") && velocityY > 0) {
                            velocityY = -velocityY;
                            velocityX -= Math.signum(velocityX) * rand;
                        } else if (side.equals("BOTTOM") && velocityY < 0) {
                            velocityY = -velocityY;
                            velocityX -= Math.signum(velocityX) * rand;
                        } else if (side.equals("LEFT") && velocityX > 0) {
                            velocityX = -velocityX;
                            velocityY -= Math.signum(velocityY) * rand;
                        } else if (side.equals("RIGHT") && velocityX < 0) {
                            velocityX = -velocityX;
                            velocityY -= Math.signum(velocityY) * rand;
                        }
                        // Xử lí kẹt bóng
                        if (Math.abs(this.velocityX) <= 50) {
                            this.setVelocityX(this.velocityX < 0 ? -50 : 50);
                        }
                        if (Math.abs(this.velocityY) <= 50) {
                            this.setVelocityY(this.velocityY < 0 ? -50 : 50);
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

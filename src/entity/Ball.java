package entity;
import Constant.Constant;
import Constant.SoundManager;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Ball extends Entity  {

    //x and y represent the center of the ball

    private int velocityX, velocityY;
    private boolean isAlive;
    private boolean isRunning;
    long lastTime;
    private static final int RADIUS = Constant.BALL_RADIUS;
    private static final int TOTAL_HEALTH = Constant.TOTAL_BALL_HEART;
    private int health;

    private static Ball instance;
    
    private Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.velocityX = 150;
        this.velocityY = 0;
        this.health = 10;
        this.img = new ImageIcon("assets/images/snowball.png").getImage();
    }

    // Singleton pattern
    public static Ball getInstance() {
        if (instance == null) {
            // Khởi tạo ball ở center của frame, phía trên paddle
            int ballCenterX = Constant.FRAME_WIDTH / 2;
            int ballCenterY = Constant.FRAME_HEIGHT - Constant.PADDLE_Y_OFFSET - Constant.PADDLE_HEIGHT - RADIUS - 50;
            
            instance = new Ball(ballCenterX, ballCenterY, RADIUS * 2, RADIUS * 2);
            // System.out.println("Ball init center: " + instance.x + ", " + instance.y);
            instance.velocityX = 150;
            instance.velocityY = -150;
            instance.isAlive = true;
            instance.isRunning = false;
        }
        return instance;
    }
    
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

    public int getRADIUS() {
        return RADIUS;
    }
    public boolean getIsAlive() {
        return isAlive;
    }
    public boolean getIsRunning() {
        return isRunning;
    }
    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }
    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    public void runBall() {
        if (!this.isRunning) {
            // Nếu bóng ở phần bên nào thì tốc độ x hướng về bên đó.
            if (this.velocityY == 0) {
                this.velocityY = 150;
            }
            if (this.x <= Constant.FRAME_WIDTH / 2 - RADIUS) {
                this.velocityX = -150;
            } else {
                this.velocityX = 150;
            }
            this.isRunning = true;
            lastTime = System.currentTimeMillis();
        }
    }

    
    public void reset() {
        this.x = Paddle.getInstance().getX() + Paddle.getInstance().getWidth() / 2;
        this.y = Constant.FRAME_HEIGHT - Constant.PADDLE_HEIGHT - RADIUS - 50;
        this.velocityX = 150;
        this.velocityY = 0;
        this.isRunning = false;
        this.isAlive = true;
    }

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


    public void update() {
        if (this.isAlive && this.isRunning) {
//            x += velocityX;
//            y += velocityY;
            double dt = (System.currentTimeMillis() - lastTime) / 1000.0;
            lastTime = System.currentTimeMillis();
            x += (int) (velocityX * dt);
            y += (int) (velocityY * dt);


            if (collisionWithUpperWall()) {
                velocityY = -velocityY;
                SoundManager.play("click");
            }
            if (collisionWithSideWall()) {
                velocityX = -velocityX;
                // Đảm bảo ball không bị "stuck" trong tường
                if (x + RADIUS >= Constant.FRAME_WIDTH) {
                    System.out.println(2);
                    x = Constant.FRAME_WIDTH - RADIUS; // Đẩy ball ra khỏi tường phải
                }
                if (x - RADIUS <= 0) {
                    System.out.println(1);
                    x = RADIUS; // Đẩy ball ra khỏi tường trái
                }
                SoundManager.play("click");
            }
//            if (collisionWithPaddle()) { velocityY = -velocityY; }
            if (!this.collisionWithPaddle().equals("NONE")) {
                String side = collisionWithPaddle();
                if (side.equals("TOP") && velocityY > 0) {
                    velocityY = -velocityY;
                    y = Paddle.getInstance().getY() - RADIUS; // đặt lại trên paddle
                } else if (side.equals("LEFT") || side.equals("RIGHT")) {
                    velocityX = -velocityX;
                }
                SoundManager.play("click");
            }

            // Check đã chết chưa
            if (y - RADIUS > Constant.FRAME_HEIGHT) {
                this.isAlive = false;
                this.isRunning = false;
                SoundManager.play("dead");
                System.out.println("Chet me roi con gi nua");
                this.respawn();
            }
        }
    }

    private boolean collisionWithUpperWall() {
        return y - RADIUS <= 0;
    }
    private boolean collisionWithSideWall() {
        boolean rightWall = x + RADIUS * 2 >= Constant.FRAME_WIDTH;
        boolean leftWall = x - RADIUS <= 0;

        // Debug: in ra khi va chạm tường
        // if (rightWall) {
        //     System.out.println("Right wall collision: x=" + x + ", x+RADIUS=" + (x + RADIUS) + ", FRAME_WIDTH=" + Constant.FRAME_WIDTH);
        // }
        // if (leftWall) {
        //     System.out.println("Left wall collision: x=" + x + ", x-RADIUS=" + (x - RADIUS));
        // }

        return (rightWall || leftWall);
    }
    private String collisionWithPaddle() {
        Paddle paddle = Paddle.getInstance();
        return this.getCollisionSide(paddle);
    }

}

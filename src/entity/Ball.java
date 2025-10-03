package entity;
import Constant.Constant;

import java.awt.Color;
import java.awt.Graphics;
public class Ball extends Entity  {

    //x and y represent the center of the ball

    private int velocityX, velocityY;
    private boolean isAlive;
    private static final int RADIUS = Constant.BALL_RADIUS;

    private static Ball instance;
    
    private Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.velocityX = 2;
        this.velocityY = -2;
    }

    // Singleton pattern
    public static Ball getInstance() {
        if (instance == null) {
            // Khởi tạo ball ở center của frame, phía trên paddle
            int ballCenterX = Constant.FRAME_WIDTH / 2;
            int ballCenterY = Constant.FRAME_HEIGHT - Constant.PADDLE_Y_OFFSET - Constant.PADDLE_HEIGHT - RADIUS - 50;
            
            instance = new Ball(ballCenterX, ballCenterY, RADIUS * 2, RADIUS * 2);
            // System.out.println("Ball init center: " + instance.x + ", " + instance.y);
            instance.velocityX = 2;
            instance.velocityY = -2;
            instance.isAlive = true;
        }
        return instance;
    }
    
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS);
    }


    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }
    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }
    
    public void reset() {
        this.x = Constant.FRAME_WIDTH / 2;
        this.y = Constant.FRAME_HEIGHT - Constant.PADDLE_Y_OFFSET - Constant.PADDLE_HEIGHT - RADIUS - 50;
        this.velocityX = 2;
        this.velocityY = -2;
    }


    public void update() {
        x += velocityX;
        y += velocityY;
        if (collisionWithUpperWall()) { velocityY = -velocityY; }
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
        }
        if (collisionWithPaddle()) { velocityY = -velocityY; }
    }

    private boolean collisionWithUpperWall() {
        return y - RADIUS <= 0;
    }
    private boolean collisionWithSideWall() {
        boolean rightWall = x + RADIUS >= Constant.FRAME_WIDTH;
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
    private boolean collisionWithPaddle() {
        Paddle paddle = Paddle.getInstance();
        return (y + RADIUS >= paddle.getY()
                && x + RADIUS >= paddle.getX() 
                && x - RADIUS <= paddle.getX() + paddle.getWidth());
    }

}

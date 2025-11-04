package main;

import entity.Ball;
import entity.Paddle;
import entity.Entity;
import org.junit.Test;
import static org.junit.Assert.*;

public class Tester {

    // -------------------- ENTITY TESTS --------------------
    @Test
    public void testEntityCollisionTrue() {
        Entity e1 = new Entity(0, 0, 50, 50);
        Entity e2 = new Entity(25, 25, 50, 50);
        assertEquals(true, e1.isCollision(e2));
    }

    @Test
    public void testEntityCollisionFalse() {
        Entity e1 = new Entity(0, 0, 50, 50);
        Entity e2 = new Entity(100, 100, 50, 50);
        assertEquals(false, e1.isCollision(e2));
    }

    @Test
    public void testCollisionSideTop() {
        Entity e1 = new Entity(10, 10, 50, 50);
        Entity e2 = new Entity(10, 55, 50, 50);
        assertEquals("TOP", e1.getCollisionSide(e2));
    }

    // -------------------- PADDLE TESTS --------------------
    @Test
    public void testPaddleSingleton() {
        Paddle p1 = Paddle.getInstance();
        Paddle p2 = Paddle.getInstance();
        assertEquals(p1, p2);
    }

    @Test
    public void testPaddleSetSpeed() {
        Paddle paddle = Paddle.getInstance();
        paddle.setSpeed(800);
        assertEquals(800, paddle.getSpeed(), 0.001);
    }

    @Test
    public void testPaddleWorkingState() {
        Paddle paddle = Paddle.getInstance();
        paddle.setWorking(false);
        assertEquals(false, paddle.isWorking());
    }

    @Test
    public void testPaddleReset() {
        Paddle paddle = Paddle.getInstance();
        paddle.setWorking(false);
        paddle.reset();
        assertEquals(false, paddle.isMovingLeft());
        assertEquals(false, paddle.isMovingRight());
    }

    // -------------------- BALL TESTS --------------------
    @Test
    public void testBallSingleton() {
        Ball b1 = Ball.getInstance();
        Ball b2 = Ball.getInstance();
        assertEquals(b1, b2);
    }

    @Test
    public void testBallSetSpeed() {
        Ball ball = Ball.getInstance();
        ball.setSpeed(2.5);
        assertEquals(2.5, ball.getSpeed(), 0.001);
    }

    @Test
    public void testBallSetFire() {
        Ball ball = Ball.getInstance();
        ball.setFire(true);
        assertEquals(true, ball.isFire());
        ball.setFire(false);
        assertEquals(false, ball.isFire());
    }

    @Test
    public void testBallHealthRespawn() {
        Ball ball = Ball.getInstance();
        int oldHealth = ball.health;
        ball.respawn();
        // Nếu còn mạng thì giảm 1
        if (oldHealth > 1) {
            assertEquals(oldHealth - 1, ball.health);
        } else {
            // Nếu hết mạng thì isAlive = false
            assertEquals(false, ball.getIsAlive());
        }
    }

    @Test
    public void testBallReset() {
        Ball ball = Ball.getInstance();
        ball.setSpeed(3.0);
        ball.reset();
        assertEquals(false, ball.getIsRunning());
        assertEquals(true, ball.getIsAlive());
    }
}

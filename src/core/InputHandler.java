package core;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean spacePressed = false;
    private boolean escapePressed = false;

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isSpacePressed() {
        return spacePressed;
    }
    public boolean isEscapePressed() {
        return escapePressed;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                break;
            case KeyEvent.VK_SPACE:
                spacePressed = true;
                break;
            case KeyEvent.VK_ESCAPE:
                escapePressed = true;
                // Handle ESCAPE key if needed
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                leftPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                break;
            case KeyEvent.VK_SPACE:
                spacePressed = false;
                break;
            case KeyEvent.VK_ESCAPE:
                escapePressed = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }
}
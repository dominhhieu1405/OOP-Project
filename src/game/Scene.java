package game;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.AbstractAction;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.MouseInfo;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
public abstract class Scene extends JPanel{
    
    protected Image background;

    public Scene(Image background) {
        this.background = background;
    }

    public Scene(){};


    // scenes don't need to implement them unless necessary.
    public boolean useMouse() { return false; }
    public boolean useKeyboard() { return false; }
    public KeyListener getKeyListener() { return null; }
    public MouseListener getMouseListener() { return null; }
    public MouseMotionListener getMouseMotionListener() { return null; }

    /**
     * Optional hook for setting up key bindings. Default is no-op.
     */
    public void setupKeyBindings() { /* no-op */ }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
    }

}

package game.scenes;
import Constant.Constant;

import java.awt.*;
import javax.swing.*;

import game.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverScene extends game.Scene {
    private JButton respawnButton;
    private JButton menuButton;

    public GameOverScene() {
        super(new ImageIcon("C:\\Users\\Lenovo\\Downloads\\youdie.png").getImage());

        setPreferredSize(new Dimension(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT));
        //setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        respawnButton = new JButton(new ImageIcon("C:\\Users\\Lenovo\\Downloads\\respawn.png"));
        menuButton = new JButton(new ImageIcon("C:\\Users\\Lenovo\\Downloads\\menu.png"));

        setLayout(null);
        respawnButton.setBounds(187, 300, 199, 52);
        menuButton.setBounds(414, 300, 199, 52);

        respawnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed respawn");
                GamePanel.getInstance().setScene(new GameScene());
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed menu");
                GamePanel.getInstance().setScene(new GameScene());
            }
        });

        add(respawnButton);
        add(menuButton);
    }

    public boolean useMouse() {
        return true;
    }
    public boolean useKeyboard() {
        return false;
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT, null);
    }
}

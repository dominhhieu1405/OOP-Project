package game.scenes;
import Constant.Constant;

import java.awt.*;
import javax.swing.*;

import entity.Ball;
import game.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverScene extends game.Scene {
    private JButton respawnButton;
    private JButton menuButton;

    public GameOverScene() {
        super(Constant.GAMEOVER_BACKGROUND_IMG);

        setPreferredSize(new Dimension(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT));
        //setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        respawnButton = new JButton((Constant.RESPAWN_BUTTON_IMG));
        menuButton = new JButton((Constant.MENU_BUTTON_IMG));

        setLayout(null);
        respawnButton.setBounds(187, 300, 199, 52);
        menuButton.setBounds(414, 300, 199, 52);

        respawnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed respawn");
                Ball.getInstance().reset();
                GamePanel.getInstance().setScene(new GameScene());
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed menu");
                GamePanel.getInstance().setScene(new MenuScene());
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

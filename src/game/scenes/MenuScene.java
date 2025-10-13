package game.scenes;
import Constant.Constant;

import java.awt.*;
import javax.swing.*;

import game.GamePanel;

import java.awt.event.*;

public class MenuScene extends game.Scene {
    private JButton playButton;
    private JButton quitButton;

    public MenuScene() {
        super(new ImageIcon(Constant.ARKANOID_IMG).getImage());

        setLayout(null);
        setPreferredSize(new Dimension(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT));
        //setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);

        playButton = new JButton("PLAY");
        quitButton = new JButton("QUIT");

        playButton.setForeground(Color.WHITE);
        quitButton.setForeground(Color.WHITE);

        playButton.setFont(new Font("Verdana", Font.BOLD, 18));
        quitButton.setFont(new Font("Verdana", Font.BOLD, 18));

        playButton.setBounds(187, 300, 199, 52);
        quitButton.setBounds(414, 300, 199, 52);

        playButton.setBackground(new Color(132, 127, 129));
        quitButton.setBackground(new Color(132, 127, 129));

        playButton.setBorderPainted(false);
        quitButton.setBorderPainted(false);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed PLAY");
                GamePanel.getInstance().setScene(new GameScene());
            }
        });

        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                playButton.setBackground(new Color(50, 150, 210));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                playButton.setBackground(new Color(132, 127, 129));
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed QUIT");
                System.exit(0);
            }
        });

        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                quitButton.setBackground(new Color(50, 150, 210));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                quitButton.setBackground(new Color(132, 127, 129));
            }
        });

        add(playButton);
        add(quitButton);
    }

    public boolean useMouse() {
        return true;
    }
    public boolean useKeyboard() {
        return false;
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0 , Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT, null);
    }
}

package game.scenes;
import Constant.Constant;

import java.awt.*;
import javax.swing.*;

import game.GamePanel;
import manager.BlockManager;
import manager.MapManager;
import manager.SoundManager;

import java.awt.event.*;

public class MenuScene extends game.Scene {
    private JButton mapButton;
    private JButton playButton;
    private JButton quitButton;



    public MenuScene() {
        super(Constant.BACKGROUND_IMG);

        setLayout(null);
        setPreferredSize(new Dimension(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT));
        //setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);


        mapButton = Constant.createBtn("Bản đồ (M)");
        mapButton.setBounds(220, 240, 360, 60);
        playButton = Constant.createBtn("Chơi tiếp (P)");
        playButton.setBounds(220, 320, 360, 60);
        quitButton = Constant.createBtn("Thoát game (E)");
        quitButton.setBounds(220, 400, 360, 60);

        mapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed Map");
                GamePanel.getInstance().setScene(new MapScene());
            }
        });

        playButton.addActionListener(e -> {
            System.out.println("Pressed PLAY");
            SoundManager.stop("bgm"); // Tắt background music
            BlockManager.getInstance().setCurrentMap(MapManager.getInstance().getLastUnlockedMap().path);
            GamePanel.getInstance().setScene(GameScene.getInstance().resetScene());
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed QUIT");
                System.exit(0);
            }
        });

        add(mapButton);
        add(mapButton);

        add(playButton);
        add(quitButton);
    }

    public boolean useMouse() {
        return true;
    }
    public boolean useKeyboard() {
        return true;
    }

    public void setupKeyBindings() {
        // Lấy input map và action map
        javax.swing.InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        javax.swing.ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_M, 0, false), "pressMap");
        actionMap.put("pressMap", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                // System.out.println("press enter");
                mapButton.doClick();
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0, false), "pressPlay");
        actionMap.put("pressPlay", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                // System.out.println("press enter");
                playButton.doClick();
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0, false), "pressExit");
        actionMap.put("pressExit", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                // System.out.println("press enter");
                quitButton.doClick();
            }
        });
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0 , Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT, null);


        // Resize để width bằng 60% của FRAME_WIDTH
        int logoWidth = (int)(Constant.FRAME_WIDTH * 0.75);
        int logoHeight = (logoWidth * Constant.LOGO_IMG.getHeight(null)) / Constant.LOGO_IMG.getWidth(null);

        g.drawImage(Constant.LOGO_IMG, (Constant.FRAME_WIDTH - logoWidth) / 2, logoHeight/3, logoWidth, logoHeight, null);
    }
}

package game.scenes;
import Constant.Constant;

import java.awt.*;
import javax.swing.*;

import entity.Block;
import game.GamePanel;
import manager.SoundManager;

import java.awt.event.*;
import java.io.File;

public class MenuScene extends game.Scene {
    private JButton mapButton;
    private JButton playButton;
    private JButton quitButton;


    private Font f;

    public MenuScene() {
        super(Constant.BACKGROUND_IMG);

        setLayout(null);
        setPreferredSize(new Dimension(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT));
        //setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);

        try {
            f = Font.createFont(Font.TRUETYPE_FONT, new File(Constant.FONT_PATH)).deriveFont(20f);
        } catch (Exception e) {
            f = new Font("Verdana", Font.BOLD, 28);
        }

        mapButton = this.createBtn("Bản đồ (M)");
        mapButton.setBounds(220, 240, 360, 60);
        playButton = this.createBtn("Chơi (P)");
        playButton.setBounds(220, 320, 360, 60);
        quitButton = this.createBtn("Thoát game (E)");
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
            GamePanel.getInstance().setScene(new GameScene());
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

    /**
     * Tạo JButton với hình nền là ảnh.
     * @param text chữ
     * @return jbutton
     */
    private JButton createBtn(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Image img = getModel().isRollover() ? Constant.BUTTON_ACTIVE_IMG : Constant.BUTTON_IMG;
                g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
                g.setFont(f);
                g.setColor(Color.WHITE);

                FontMetrics fm = g.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() + textHeight) / 2 - 4;

                g.drawString(getText(), x, y);
            }
        };

        // Xóa border, background Swing mặc định
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setRolloverEnabled(true);
        button.setForeground(Color.WHITE);

        return button;
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
        g.setFont(f);
        g.drawImage(background, 0, 0 , Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT, null);


        // Resize để width bằng 60% của FRAME_WIDTH
        int logoWidth = (int)(Constant.FRAME_WIDTH * 0.75);
        int logoHeight = (logoWidth * Constant.LOGO_IMG.getHeight(null)) / Constant.LOGO_IMG.getWidth(null);

        g.drawImage(Constant.LOGO_IMG, (Constant.FRAME_WIDTH - logoWidth) / 2, logoHeight/3, logoWidth, logoHeight, null);
    }
}

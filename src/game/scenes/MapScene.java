package game.scenes;

import Constant.Constant;
import game.GamePanel;
import manager.MapManager;
import manager.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MapScene extends game.Scene {
    private Font f;
    public int page;
    private JButton prevButton;
    private JButton nextButton;
    private JButton menuButton;

    public MapScene (){
        super(Constant.BACKGROUND_IMG);
        setLayout(null);
        setPreferredSize(new Dimension(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT));
        MapManager.getInstance().load();

        menuButton = Constant.createBtn("Menu (M)");
        menuButton.setBounds(220, 200 + 80 * 3, 360, 60);

        menuButton.addActionListener(e -> {
            GamePanel.getInstance().setScene(new MenuScene());
        });


        nextButton = Constant.createBtn(">");
        nextButton.setBounds(600, 200 + 80 * 3, 75, 60);
        nextButton.addActionListener(e -> {
            changePage(page + 1);
        });

        prevButton = Constant.createBtn("<");
        prevButton.setBounds(125, 200 + 80 * 3, 75, 60);
        prevButton.addActionListener(e -> {
            changePage(page - 1);
        });

        add(menuButton);
        add(prevButton);
        add(nextButton);


        changePage(1);
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.drawImage(Constant.BACKGROUND_IMG, 0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT, null);
        // Resize để width bằng 60% của FRAME_WIDTH
        int logoWidth = (int)(Constant.FRAME_WIDTH * 0.75);
        int logoHeight = (logoWidth * Constant.LOGO_IMG.getHeight(null)) / Constant.LOGO_IMG.getWidth(null);

        g.drawImage(Constant.LOGO_IMG, (Constant.FRAME_WIDTH - logoWidth) / 2, logoHeight/3 - 36, logoWidth, logoHeight, null);
    }

    public void changePage(int newPage) {
        if (newPage != page && newPage > 0 && newPage <=  MapManager.getInstance().maps.size() / 9 + 1) {
            page = newPage;
            MapManager.getInstance().setPage(newPage);
            for (MapManager.Map mp : MapManager.getInstance().maps) {
                remove(mp.button);
            }
            for (MapManager.Map mp : MapManager.getInstance().getMapsByPage()) {
                add(mp.button);
            }

            prevButton.setEnabled(page != 1);
            nextButton.setEnabled(page * 9 < MapManager.getInstance().maps.size());
        }
    }

    public boolean useKeyboard() {
        return true;
    }

    public void setupKeyBindings() {
        // Lấy input map và action map
        javax.swing.InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        javax.swing.ActionMap actionMap = getActionMap();

        int[] mapKeys = {KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4, KeyEvent.VK_5, KeyEvent.VK_6, KeyEvent.VK_7, KeyEvent.VK_8, KeyEvent.VK_9};
        for (int mk : mapKeys) {
            inputMap.put(KeyStroke.getKeyStroke(mk, 0, false), "pressMap" + mk);
            actionMap.put("pressMap" + mk, new AbstractAction() {
                @Override public void actionPerformed(ActionEvent e) {
                    int index = mk - KeyEvent.VK_1;
                    java.util.List<MapManager.Map> mapsOnPage = MapManager.getInstance().getMapsByPage();
                    if (index < mapsOnPage.size()) {
                        mapsOnPage.get(index).button.doClick();
                    }
                }
            });
        }

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_M, 0, false), "pressMenuBtn");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "pressMenuBtn");
        actionMap.put("pressMenuBtn", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                // System.out.println("press enter");
                menuButton.doClick();
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "pressPrevBtn");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0, false), "pressPrevBtn");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "pressPrevBtn");
        actionMap.put("pressPrevBtn", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                prevButton.doClick();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0, false), "pressNextBtn");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "pressNextBtn");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "pressNextBtn");
        actionMap.put("pressNextBtn", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                nextButton.doClick();
            }
        });
    }
}

package manager;

import Constant.Constant;
import entity.Ball;
import entity.Block;
import game.GamePanel;
import game.scenes.GameScene;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class MapManager {
    private static MapManager instance; // Singleton instance
    private int page = 1;
    private final int pageLimit = 9;
    private final int buttonWidth = 170;
    private final int buttonHeight = 60;
    public ArrayList<Map> maps;
    private Font f;

    public MapManager() {
        maps = new ArrayList<Map>();
        try {
            f = Font.createFont(Font.TRUETYPE_FONT, new File(Constant.FONT_PATH)).deriveFont(20f);
        } catch (Exception e) {
            f = new Font("Verdana", Font.BOLD, 28);
        }
    }

    /**
     * Lấy instance của MapManager (Singleton pattern).
     * @return Instance của MapManager.
     */
    public static MapManager getInstance() {
        if (instance == null) {
            instance = new MapManager();
            instance.load();
        }
        return instance;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) maps.size() / pageLimit);
    }

    public static class Map {
        public int id;
        public String name;
        public String path;
        public boolean unlocked = false;
        public JButton button;

        public Map(int id, String name, boolean unlocked, JButton button) {
            this.id = id;
            this.name = name;
//            this.path = path;
            this.unlocked = unlocked;
            this.button = button;
        }
    }

    public void load() {
        this.maps.clear();
        int index = 0;
        try {
            FileInputStream inputStream = new FileInputStream("data/maps.txt");

            int bufSize = inputStream.available();
            byte[] buffer = new byte[bufSize];
            int n;
            byte[] data = new byte[0];
            int dataLen = 0;
            while ((n = inputStream.read(buffer)) != -1) {
                byte[] newData = new byte[dataLen + n];
                System.arraycopy(data, 0, newData, 0, dataLen);
                System.arraycopy(buffer, 0, newData, dataLen, n);
                data = newData;
                dataLen += n;
            }

            String content = new String(data);
            String[] lines = content.split("\n");

            int startX = Constant.FRAME_WIDTH / 2 - (buttonWidth * 3 + 40) / 2;
            int startY = 200;


            for (String line : lines) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                String[] args = line.split(" ");
                if (args.length == 2) {
                    int id = Integer.parseInt(args[0]);
                    int unlocked = Integer.parseInt(args[1]);

                    int row = (index % 9) / 3;
                    int col = index % 3;
                    JButton button = Constant.createBtn("Level " + id);
                    button.setBounds(startX + col * (buttonWidth + 20), startY + row * (buttonHeight + 20),
                            buttonWidth, buttonHeight);
                    button.addActionListener(e -> {
                        System.out.println("Pressed PLAY Level " + id);
                        Ball.getInstance().reset();
                        BlockManager.getInstance().load("data/maps/Map" + id + ".txt");
                        SoundManager.stop("bgm"); // Tắt background music
                        GamePanel.getInstance().setScene(new GameScene());
                    });

                    button.setEnabled(unlocked == 1);

                    maps.add(new Map(id, "Level " + id, unlocked == 1, button));
                }

                index++;

            }
            inputStream.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }



    /**
     * Lấy các map theo page.
     * @return danh sách map của page hiện tại
     */
    public ArrayList<Map> getMapsByPage() {
        ArrayList<Map> result = new ArrayList<>();
        int startIndex = (page - 1) * 9;
        int endIndex = Math.min(startIndex + 9, maps.size());
        for (int i = startIndex; i < endIndex; i++) {
            result.add(maps.get(i));
        }
        return result;
    }
}

package manager;

import Constant.Constant;
import entity.Ball;
import game.GamePanel;
import game.scenes.GameScene;

import javax.swing.*;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.util.ArrayList;


public class MapManager {
    private static MapManager instance; // Singleton instance
    private int page = 1; // Trang hiên tại
    private final int pageLimit = 9; // Số map tối đa trên mỗi trang
    private final int buttonWidth = 170; // Chiều rộng nút
    private final int buttonHeight = 60; // Chiều cao nút
    public ArrayList<Map> maps;
    private int currentMap;


    public MapManager() {
        maps = new ArrayList<Map>();
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

    /**
     * Lấy trang hiện tại.
     * @return Trang hiện tại.
     */
    public int getPage() {
        return page;
    }

    /**
     * Đặt trang hiện tại.
     * @param page Trang cần đặt.
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Lấy tổng số trang.
     * @return Tổng số trang.
     */
    public int getTotalPages() {
        return (int) Math.ceil((double) maps.size() / pageLimit);
    }

    /**
     * Map.
     */
    public static class Map {
        public int id; // Số thứ tự
        public String name; // Tên
        public String path; // File map
        public boolean unlocked = false; // Đã mở khóa chưa
        public JButton button; // Nút tương ứng

        /**
         * Constructor.
         * @param id id
         * @param name name
         * @param path path
         * @param unlocked unlocked
         * @param button button
         */
        public Map(int id, String name, String path, boolean unlocked, JButton button) {
            this.id = id;
            this.name = name;
            this.path = path;
            this.unlocked = unlocked;
            this.button = button;
        }

        /**
         * Mở khóa map.
         */
        public void unlock() {
            this.unlocked = true;
            this.button.setEnabled(true);
        }
    }

    /**
     * Load map từ file.
     */
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
                        MapManager.getInstance().currentMap = id - 1;
                        SoundManager.stop("bgm"); // Tắt background music
                        GamePanel.getInstance().setScene(GameScene.getInstance().resetScene());
                    });

                    button.setEnabled(unlocked == 1);
                    if (unlocked == 1) {
                        currentMap = index;
                    }
                    maps.add(new Map(id, "Level " + id, "data/maps/Map" + id + ".txt", unlocked == 1, button));
                }

                index++;

            }
            inputStream.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * Lưu trạng thái map vào file.
     */
    public void save() {
        try {
            StringBuilder sb = new StringBuilder();
            for (Map map : maps) {
                sb.append(map.id).append(" ").append(map.unlocked ? 1 : 0).append("\n");
            }
            try {
                FileWriter writer = new FileWriter("data/maps.txt");
                writer.write(sb.toString());
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
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

    /**
     * Lấy map hiện tại.
     */
    public Map getCurrentMap() {
        return maps.get(currentMap);
    }

    /**
     * Lấy map cuối cùng đã mở khóa.
     * @return map
     */
    public Map getLastUnlockedMap() {
        for (int i = maps.size() - 1; i >= 0; i--) {
            if (maps.get(i).unlocked) {
                return maps.get(i);
            }
        }
        return maps.get(0);
    }

    /**
     * Mở khóa map tiếp theo.
     */
    public void unlockNextMap() {
        if (currentMap + 1 < maps.size()) {
            System.out.println("Unlocking map " + (currentMap + 1));
            maps.get(currentMap + 1).unlock();
            currentMap++;
        }
        this.save();
    }

    /**
     * Kiểm tra có map tiếp theo không.
     * @return true nếu có, false nếu không
     */
    public boolean hasNextMap() {
        return currentMap + 1 < maps.size();
    }
}

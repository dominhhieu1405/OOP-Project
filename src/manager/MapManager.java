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
            int startY = 300;


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
                    JButton button = createBtn("Level " + id);
                    button.setBounds(startX + row * (buttonHeight + 20), startY + col * (buttonWidth + 20), buttonWidth, buttonHeight);
                    button.addActionListener(e -> {
                        System.out.println("Pressed PLAY Level " + id);
                        Ball.getInstance().reset();
                        BlockManager.getInstance().load("data/maps/" + id + ".txt");
                        SoundManager.stop("bgm"); // Tắt background music
                        GamePanel.getInstance().setScene(new GameScene());
                    });


                    maps.add(new Map(id, "Level " + id, unlocked == 1, button));
                }

                index++;
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
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



    /**
     * Vẽ các nút theo page.
     * @param g Graphics
     */
    public void render(Graphics g) {
        int from = (page - 1) * 9;
        int to = Math.min(from + 9, maps.size());


        for (int i = from; i < to; i++) {
        }
    }
}

package manager;

import Constant.Constant;
import entity.*;

import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class BlockManager {
    private static BlockManager instance; // Singleton instance
    private ArrayList<Block> blocks; // Danh sách block
    public String currentMap; // Tên map hiện tại

    /**
     * Constructor.
     */
    private BlockManager() {
        blocks = new ArrayList<>();
    }

    /**
     * Lấy instance của BlockManager (Singleton pattern).
     * @return Instance của BlockManager.
     */
    public static BlockManager getInstance() {
        if (instance == null) {
            instance = new BlockManager();
        }
        return instance;
    }

    /**
     * Reset danh sách block.
     */
    public synchronized void reset() {
        blocks.clear();
        load(this.getCurrentMap());
    }

    /**
     * Lấy tên map hiện tại.
     * @return Tên map hiện tại.
     */
    public String getCurrentMap() {
        return currentMap;
    }

    /**
     * Đặt tên map hiện tại.
     * @param map Tên map cần đặt.
     */
    public void setCurrentMap(String map) {
        this.currentMap = map;
    }

    /**
     * Thêm một block vào danh sách.
     * @param block Block cần thêm.
     */
    public synchronized void addBlock(Block block) {
        blocks.add(block);
    }

    /**
     * Lấy danh sách block.
     * @return Danh sách block.
     */
    public synchronized ArrayList<Block> getBlocks() {
        return new ArrayList<Block>(blocks);
    }

    /**
     * Xoá một block khỏi danh sách.
     * @param block Block cần xoá.
     */
    public synchronized void removeBlock(Block block) {
        blocks.remove(block);
    }

    /**
     * Vẽ tất cả các block.
     * @param g Graphics
     */
    public synchronized void render(Graphics g) {
        for (Block block : blocks) {
            block.render(g);
        }
    }

    /**
     * Load map từ file.
     * @param filename Tên file
     */
    public synchronized void load(String filename) {
        try {
            FileInputStream inputStream = new FileInputStream(filename);
            load(inputStream);
            this.setCurrentMap(filename);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * Load map từ input stream.
     * @param inputStream InputStream
     */
    public void load(InputStream inputStream) {
        try {
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

            for (String line : lines) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                String[] args = line.split(" ");
                if (args.length > 2) {
                    String type = args[0];
                    int x = Integer.parseInt(args[1]);
                    int y = Integer.parseInt(args[2]);
                    int w = (args.length > 3) ? Integer.parseInt(args[3]) : Constant.BLOCK_DEFAULT_WIDTH;
                    int h = (args.length > 4) ? Integer.parseInt(args[4]) : Constant.BLOCK_DEFAULT_HEIGHT;
                    Block block;
                    switch (type) {
                        case "bedrock":
                            block = new BlockBedrock(x, y, w, h);
                            break;
                        case "bomb":
                            int HPB = (args.length > 5) ? Integer.parseInt(args[5]) : 1;
                            int damage = (args.length > 6) ? Integer.parseInt(args[6]) : 1;
                            int range = (args.length > 7) ? Integer.parseInt(args[7]) : 100;
                            // System.out.println("Bomb block at (" + x + ", " + y + ") with size (" + w + ", " + h + "), HP: " + HPB + ", damage: " + damage + ", range: " + range);
                            block = new BlockBomb(x, y, w, h, HPB, damage, range);
                            break;
                        case "lucky":
                            int HPL = (args.length > 5) ? Integer.parseInt(args[5]) : 1;
                            int luckyType = (args.length > 6) ? Integer.parseInt(args[6]) : -1;
                            System.out.println("Lucky block at (" + x + ", " + y + ") with size (" + w + ", " + h + "), HP: " + HPL);
                            block = new BlockLucky(x, y, w, h, HPL, luckyType);
                            break;
                        default:
                            int HP = (args.length > 5) ? Integer.parseInt(args[5]) : Constant.BLOCK_DEFAULT_HP;
                            // System.out.println("Normal block at (" + x + ", " + y + ") with size (" + w + ", " + h + "), HP: " + HP);
                            block = new Block(x, y, w, h, HP);
                            break;
                    }
                    this.addBlock(block);

                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * Test.
     */
    public void test() {
        // System.out.println("BlockManager test");
        // tạo test
        this.load("data/maps/test.txt");
//        blockManager.reset();
//        // Thêm các block test vào BlockManager
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 8; j++) {
//                Block b = new Block(100 * j + 1,  + 50 * i + 36);
//                blockManager.addBlock(b);
//            }
//        }
    }

    public synchronized boolean checkWin() {
        boolean allDestroyed = true;
        for (Block block : blocks) {
            if (!(block instanceof BlockBedrock)) {
                if (block.getHP() > 0) {
                    allDestroyed = false;
                    break;
                }
            }
        }
        return allDestroyed;
    }


}

package manager;

import Constant.Constant;
import entity.Block;
import entity.BlockLucky;
import entity.Paddle;

import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;

public class BlockManager {
    private static BlockManager instance; // Singleton instance
    public ArrayList<Block> blocks; // Danh sách block

    /**
     * Constructor.
     */
    public BlockManager() {
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
    public void reset() {
        blocks.clear();
    }

    /**
     * Thêm một block vào danh sách.
     * @param block Block cần thêm.
     */
    public void addBlock(Block block) {
        blocks.add(block);
    }

    /**
     * Lấy danh sách block.
     * @return Danh sách block.
     */
    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    /**
     * Xoá một block khỏi danh sách.
     * @param block Block cần xoá.
     */
    public void removeBlock(Block block) {
        blocks.remove(block);
    }

    /**
     * Vẽ tất cả các block.
     * @param g Graphics
     */
    public void render(Graphics g) {
        for (Block block : blocks) {
            block.render(g);
        }
    }

    /**
     * Load map từ file.
     * @param filename Tên file
     */
    public void load(String filename) {
        // TODO: Load map từ file
    }

    /**
     * Load map từ input stream.
     * @param input InputStream
     */
    public void load(InputStream input) {
        // TODO: Load map từ input stream
    }

    /**
     * Test.
     */
    public void test() {
        // tạo test
        BlockManager blockManager = BlockManager.getInstance();

        blockManager.reset();
        // Thêm các block test vào BlockManager
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                Block b = new BlockLucky(100 * j + 1,  + 50 * i + 36);
                blockManager.addBlock(b);
            }
        }
    }


}

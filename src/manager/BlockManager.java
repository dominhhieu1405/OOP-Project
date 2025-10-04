package manager;

import Constant.Constant;
import entity.Block;
import entity.BlockLucky;
import entity.Paddle;

import java.awt.*;
import java.util.ArrayList;

public class BlockManager {
    private static BlockManager instance;
    public ArrayList<Block> blocks;

    public BlockManager() {
        blocks = new ArrayList<>();
    }
    public static BlockManager getInstance() {
        if (instance == null) {
            instance = new BlockManager();
        }
        return instance;
    }
    public void reset() {
        blocks.clear();
    }
    public void addBlock(Block block) {
        blocks.add(block);
    }
    public ArrayList<Block> getBlocks() {
        return blocks;
    }
    public void removeBlock(Block block) {
        blocks.remove(block);
    }

    public void render(Graphics g) {
        for (Block block : blocks) {
            block.render(g);
        }
    }

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

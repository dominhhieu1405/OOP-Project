package manager;

import Constant.Constant;
import entity.Block;
import entity.BlockLucky;
import entity.Paddle;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        this.reset();
        try (BufferedReader br = new BufferedReader(new FileReader(Constant.MAPS_PATH + filename))) {
            String line;
            // type|x|y|width|height
            while ((line = br.readLine()) != null) {
                String type, x, y, width, height;
                int index = 0;
                char c = line.charAt(0);
                // đọc type
                type = "";
                while (c != '|') {
                    c = line.charAt(index);
                    type = type + c;
                    index++;
                    c = line.charAt(index);
                }
                System.out.println("Type: " + type);
                index++; // skip '|'
                c = line.charAt(index);

                // đọc x
                x = "";
                while (c != '|') {
                    c = line.charAt(index);
                    x = x + c;
                    index++;
                    c = line.charAt(index);
                    
                }
                System.out.println("X: " + x);
                index++; // skip '|'
                c = line.charAt(index);

                // đọc y
                y = "";
                while (c != '|') {
                    c = line.charAt(index);
                    y = y + c;
                    index++;
                    c = line.charAt(index);
                }
                System.out.println("Y: " + y);
                index++; // skip '|'
                c = line.charAt(index);
                
                // đọc width
                width = "";
                while (c != '|') {
                    c = line.charAt(index);
                    width = width + c;
                    index++;
                    c = line.charAt(index);
                }
                System.out.println("Width: " + width);
                index++; // skip '|'
                c = line.charAt(index);
                
                
                // đọc height
                height = "";
                for (int i = index; i < line.length(); i++) {
                    c = line.charAt(i);
                    height = height + c;
                }
                System.out.println("Height: " + height);

                // tạo block và thêm vào danh sách
                switch (type) {
                    case "Block":
                        System.out.println("Creating normal block...");
                        Block b = new Block(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(width), Integer.parseInt(height), 2);
                        System.out.println("Init block with 2 HP");
                        this.addBlock(b);
                        System.out.println("Added Block at (" + x + ", " + y + ") with size (" + width + ", " + height + ")");
                        break;
                    case "BlockLucky":
                        System.out.println("Creating lucky block...");
                        BlockLucky bl = new BlockLucky(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(width), Integer.parseInt(height));
                        System.out.println("Init lucky block");
                        this.addBlock(bl);
                        System.out.println("Added BlockLucky at (" + x + ", " + y + ") with size (" + width + ", " + height + ")");
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Map file not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }

    /**
     * Load map từ input stream.
     * @param input InputStream
     */
    public void load(InputStream input) {
        // TODO: Load map từ input stream
        this.reset();
        if (input == null) {
            System.out.println("Map file not found!");
            return;
        }
        // implement loading logic here
    }

    /**
     * Test.
     */
    public void test() {
        System.out.println("BlockManager test");
        // tạo test
        BlockManager blockManager = BlockManager.getInstance();

        blockManager.reset();
        System.out.println("After reset, number of blocks: " + blockManager.getBlocks().size());
        // Thêm các block test vào BlockManager
        // for (int i = 0; i < 6; i++) {
        //     for (int j = 0; j < 8; j++) {
        //         Block b = new BlockLucky(100 * j + 1,  + 50 * i + 36);
        //         blockManager.addBlock(b);
        //     }
        // }
        load("test.txt");
        System.out.println("After adding blocks, number of blocks: " + blockManager.getBlocks().size());
        
    }


}

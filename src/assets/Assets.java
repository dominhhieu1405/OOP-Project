package assets;

import java.awt.image.BufferedImage;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.HashMap;
import java.util.Map;

/**
 * Quản lý tài nguyên trò chơi như hình ảnh, âm thanh, font
 * Load và cache assets để tái sử dụng
 */
public class Assets {
    
    // Singleton pattern
    private static Assets instance;
    
    // Cache cho assets
    private Map<String, BufferedImage> images;
    private Map<String, Clip> sounds;
    private Map<String, Font> fonts;
    
    private Assets() {
        images = new HashMap<>();
        sounds = new HashMap<>();
        fonts = new HashMap<>();
    }
    
    public static Assets getInstance() {
        if (instance == null) {
            instance = new Assets();
        }
        return instance;
    }
    
    // ===== IMAGE LOADING =====
    
    /**
     * Load hình ảnh từ resources
     */
    public BufferedImage loadImage(String path) {
        // Kiểm tra cache trước
        if (images.containsKey(path)) {
            return images.get(path);
        }
        
        try {
            InputStream stream = getClass().getResourceAsStream("/assets/images/" + path);
            if (stream == null) {
                System.err.println("Cannot find image: " + path);
                return createPlaceholderImage();
            }
            
            BufferedImage image = ImageIO.read(stream);
            images.put(path, image); // Cache image
            stream.close();
            
            System.out.println("Loaded image: " + path);
            return image;
            
        } catch (IOException e) {
            System.err.println("Error loading image: " + path + " - " + e.getMessage());
            return createPlaceholderImage();
        }
    }
    
    /**
     * Tạo placeholder image khi không load được
     */
    private BufferedImage createPlaceholderImage() {
        BufferedImage placeholder = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
        // Tô màu placeholder (pink để dễ nhận biết)
        for (int x = 0; x < 32; x++) {
            for (int y = 0; y < 32; y++) {
                placeholder.setRGB(x, y, 0xFF00FF); // Magenta
            }
        }
        return placeholder;
    }
    
    // ===== SOUND LOADING =====
    
    /**
     * Load âm thanh từ resources
     */
    public Clip loadSound(String path) {
        if (sounds.containsKey(path)) {
            return sounds.get(path);
        }
        
        try {
            InputStream stream = getClass().getResourceAsStream("/assets/sounds/" + path);
            if (stream == null) {
                System.err.println("Cannot find sound: " + path);
                return null;
            }
            
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(stream);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            
            sounds.put(path, clip);
            System.out.println("Loaded sound: " + path);
            return clip;
            
        } catch (Exception e) {
            System.err.println("Error loading sound: " + path + " - " + e.getMessage());
            return null;
        }
    }
    
    // ===== FONT LOADING =====
    
    /**
     * Load font từ resources
     */
    public Font loadFont(String path, float size) {
        String key = path + "_" + size;
        
        if (fonts.containsKey(key)) {
            return fonts.get(key);
        }
        
        try {
            InputStream stream = getClass().getResourceAsStream("/assets/fonts/" + path);
            if (stream == null) {
                System.err.println("Cannot find font: " + path);
                return new Font("Arial", Font.PLAIN, (int)size);
            }
            
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, stream);
            Font sizedFont = baseFont.deriveFont(size);
            
            fonts.put(key, sizedFont);
            stream.close();
            
            System.out.println("Loaded font: " + path + " size: " + size);
            return sizedFont;
            
        } catch (Exception e) {
            System.err.println("Error loading font: " + path + " - " + e.getMessage());
            return new Font("Arial", Font.PLAIN, (int)size);
        }
    }
    
    // ===== UTILITY METHODS =====
    
    /**
     * Pre-load tất cả assets cần thiết
     */
    public void preloadGameAssets() {
        System.out.println("Preloading game assets...");
        
        // Load game images
        loadImage("paddle.png");
        loadImage("ball.png");
        loadImage("brick_red.png");
        loadImage("brick_blue.png");
        loadImage("brick_green.png");
        loadImage("background.png");
        
        // Load game sounds
        loadSound("hit.wav");
        loadSound("break.wav");
        loadSound("game_over.wav");
        
        // Load fonts
        loadFont("game_font.ttf", 24f);
        loadFont("game_font.ttf", 16f);
        
        System.out.println("Assets preloading completed.");
    }
    
    /**
     * Giải phóng tất cả resources
     */
    public void dispose() {
        // Dispose sounds
        for (Clip clip : sounds.values()) {
            if (clip != null) {
                clip.close();
            }
        }
        
        // Clear caches
        images.clear();
        sounds.clear();
        fonts.clear();
        
        System.out.println("Assets disposed.");
    }
    
    // ===== GETTER METHODS =====
    
    public BufferedImage getImage(String path) {
        return images.get(path);
    }
    
    public Clip getSound(String path) {
        return sounds.get(path);
    }
    
    public Font getFont(String path, float size) {
        return fonts.get(path + "_" + size);
    }
}

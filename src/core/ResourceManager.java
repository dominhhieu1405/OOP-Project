package core;
import assets.Assets;

/**
 * Quản lý tài nguyên trò chơi - sử dụng Assets singleton
 */
public class ResourceManager {
    private Assets assets;
    
    public ResourceManager() {
        assets = Assets.getInstance();
    }
    
    /**
     * Load tất cả resources cần thiết cho game
     */
    public void loadImages() {
        System.out.println("Loading images...");
        
        // Load game sprites
        assets.loadImage("paddle.png");
        assets.loadImage("ball.png");
        
        // Load brick sprites
        assets.loadImage("brick_red.png");
        assets.loadImage("brick_blue.png");
        assets.loadImage("brick_green.png");
        assets.loadImage("brick_yellow.png");
        
        // Load UI sprites
        assets.loadImage("background.png");
        assets.loadImage("heart.png");
        assets.loadImage("star.png");
        
        System.out.println("Images loaded successfully.");
    }
    
    public void loadSounds() {
        System.out.println("Loading sounds...");
        
        // Load game sounds
        assets.loadSound("paddle_hit.wav");
        assets.loadSound("brick_break.wav");
        assets.loadSound("wall_bounce.wav");
        assets.loadSound("life_lost.wav");
        assets.loadSound("game_over.wav");
        assets.loadSound("level_complete.wav");
        
        // Load background music
        assets.loadSound("background_music.wav");
        
        System.out.println("Sounds loaded successfully.");
    }
    
    public void loadFonts() {
        System.out.println("Loading fonts...");
        
        // Load custom fonts
        assets.loadFont("game_font.ttf", 12f);
        assets.loadFont("game_font.ttf", 16f);
        assets.loadFont("game_font.ttf", 24f);
        assets.loadFont("game_font.ttf", 32f);
        assets.loadFont("game_font.ttf", 48f);
        
        System.out.println("Fonts loaded successfully.");
    }
    
    /**
     * Load tất cả assets một lần
     */
    public void loadAllResources() {
        assets.preloadGameAssets();
    }
    
    /**
     * Giải phóng resources
     */
    public void dispose() {
        assets.dispose();
    }
    
    /**
     * Get Assets instance
     */
    public Assets getAssets() {
        return assets;
    }
}

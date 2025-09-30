package core;
import assets.Assets;
// quản lý tài nguyên trò chơi (hình ảnh, âm thanh, font, v.v.)

public class ResourceManager {
    private Assets assets;
    public ResourceManager() {
        assets = new Assets();
    }
    public void loadImages() {
        // load hình ảnh từ Assets
    }
    public void loadSounds() {
        // load âm thanh từ Assets
    }
    public void loadFonts() {
        // load font từ Assets
    }
}

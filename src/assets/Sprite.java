package assets;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

/**
 * Class để quản lý và render sprites
 */
public class Sprite {
    private BufferedImage image;
    private int width, height;
    private float scaleX = 1.0f, scaleY = 1.0f;
    private float rotation = 0.0f;
    private int offsetX = 0, offsetY = 0;
    
    public Sprite(String imagePath) {
        this.image = Assets.getInstance().loadImage(imagePath);
        if (image != null) {
            this.width = image.getWidth();
            this.height = image.getHeight();
        }
    }
    
    public Sprite(BufferedImage image) {
        this.image = image;
        if (image != null) {
            this.width = image.getWidth();
            this.height = image.getHeight();
        }
    }
    
    /**
     * Render sprite tại vị trí (x, y)
     */
    public void render(Graphics2D g2d, int x, int y) {
        if (image == null) return;
        
        AffineTransform oldTransform = g2d.getTransform();
        
        // Áp dụng transformations
        AffineTransform transform = new AffineTransform();
        
        // Translate to position
        transform.translate(x + offsetX, y + offsetY);
        
        // Rotate if needed
        if (rotation != 0) {
            transform.rotate(Math.toRadians(rotation), width/2.0, height/2.0);
        }
        
        // Scale if needed
        if (scaleX != 1.0f || scaleY != 1.0f) {
            transform.scale(scaleX, scaleY);
        }
        
        g2d.setTransform(transform);
        g2d.drawImage(image, 0, 0, null);
        
        // Restore original transform
        g2d.setTransform(oldTransform);
    }
    
    /**
     * Render sprite với kích thước tùy chỉnh
     */
    public void render(Graphics2D g2d, int x, int y, int width, int height) {
        if (image == null) return;
        
        g2d.drawImage(image, x + offsetX, y + offsetY, width, height, null);
    }
    
    /**
     * Render một phần của sprite (sprite sheet)
     */
    public void renderSubImage(Graphics2D g2d, int x, int y, 
                              int srcX, int srcY, int srcWidth, int srcHeight) {
        if (image == null) return;
        
        g2d.drawImage(image, 
                     x + offsetX, y + offsetY, x + offsetX + srcWidth, y + offsetY + srcHeight,
                     srcX, srcY, srcX + srcWidth, srcY + srcHeight, null);
    }
    
    // ===== GETTERS & SETTERS =====
    
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    
    public void setScale(float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }
    
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
    
    public void setOffset(int offsetX, int offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
    public boolean isLoaded() {
        return image != null;
    }
}
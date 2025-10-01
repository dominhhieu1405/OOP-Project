package entities;
import assets.Sprite;

public class Brick extends Block implements Breakable {

    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        this.HP = 2; // Brick cần 2 hit để phá hủy
    }
    public void destroy() {
        
    }
}
package entities;
import assets.Sprite;
public class Iron extends Block {

    public Iron(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        this.HP = Integer.MAX_VALUE; // Iron ko thể phá hủy
    }


}
package entities;
import assets.Sprite;
public class Tnt extends Block implements Breakable {
    public Tnt(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        this.HP = 1;
    }
    // will be called in destroy()
    private void explode() {

    }

    public void destroy() {
        explode();
    }
}
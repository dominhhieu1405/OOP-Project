package entities;
import assets.Sprite;

public abstract class Block extends Entity {
    // protected int x, y;
    // protected Sprite sprite;
    protected int HP;

    public Block(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }
    public int getHP() {
        return this.HP;
    }
    public void setHP(int hp) {
        this.HP = hp;
    }
    public void hit() {
        if (this.HP > 0) {
            this.HP--;
        }
    }

}

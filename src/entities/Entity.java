package entities;
import assets.*;
public abstract class Entity {
    protected int x;
    protected int y;
    protected Sprite sprite;

    public Entity(int x, int y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
}
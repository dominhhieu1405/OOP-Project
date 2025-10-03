package entity;

public class Entity {
    public int x, y;
    public int width, height;
    public Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public boolean isCollision(Entity other) {
        return this.x < other.x + other.width &&
               this.x + this.width > other.x &&
               this.y < other.y + other.height &&
               this.y + this.height > other.y;
    }
    public String getCollisionSide(Entity other) {
        int thisRight  = this.x + this.width;
        int thisBottom = this.y + this.height;
        int otherRight  = other.x + other.width;
        int otherBottom = other.y + other.height;

        if (!(this.x < otherRight &&
                thisRight > other.x &&
                this.y < otherBottom &&
                thisBottom > other.y)) {
            return "NONE";
        }

        int overlapLeft   = thisRight - other.x;
        int overlapRight  = otherRight - this.x;
        int overlapTop    = thisBottom - other.y;
        int overlapBottom = otherBottom - this.y;

        int minOverlapX = Math.min(overlapLeft, overlapRight);
        int minOverlapY = Math.min(overlapTop, overlapBottom);

        if (minOverlapX < minOverlapY) {
            // Va chạm ngang
            if (overlapLeft < overlapRight) {
                return "LEFT";   //trái
            } else {
                return "RIGHT";  //phải
            }
        } else {
            // Va chạm dọc
            if (overlapTop < overlapBottom) {
                return "TOP";    //trên
            } else {
                return "BOTTOM"; //dưới
            }
        }
    }


}

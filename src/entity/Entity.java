package entity;
import java.awt.*;
public class Entity {
    protected int x, y; //tọa độ góc trên bên trái
    protected int width, height; //chiều rộng và chiều cao
    protected Image img; //hình ảnh để vẽ

    /**
     * Constructor.
     * @param x tọa độ góc trên bên trái
     * @param y tọa độ góc trên bên trái
     * @param width chiều rộng
     * @param height chiều cao
     */
    public Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    /* Getters and Setters */
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    /**
     * Kiểm tra va chạm với thực thể khác.
     * @param other thực thể khác
     * @return true nếu va chạm, false nếu không
     */
    public boolean isCollision(Entity other) {
        return this.x < other.x + other.width &&
               this.x + this.width > other.x &&
               this.y < other.y + other.height &&
               this.y + this.height > other.y;
    }

    /**
     * Xác định hướng va chạm với thực thể khác.
     * @param other thực thể khác
     * @return hướng va chạm: "LEFT", "RIGHT", "TOP", "BOTTOM", hoặc "NONE
     */
    public String getCollisionSide(Entity other) {
        // Tính toán các cạnh của cả hai thực thể
        int thisRight  = this.x + this.width;
        int thisBottom = this.y + this.height;
        int otherRight  = other.x + other.width;
        int otherBottom = other.y + other.height;

        // Kiểm tra nếu không có va chạm
        if (!(this.x < otherRight &&
                thisRight > other.x &&
                this.y < otherBottom &&
                thisBottom > other.y)) {
            return "NONE";
        }

        // Tính toán đè lên ở mỗi hướng
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

    /**
     * Xác định hướng var cham với thực thể khác.
     * @param x tọa độ góc trên bên trái
     * @param y tọa độ góc trên bên trái
     * @param width chiều rộng
     * @param height chiều cao
     * @return hướng va chạm: "LEFT", "RIGHT", "TOP", "BOTTOM", hoặc "NONE
     */
    public String getCollisionSide(int x, int y, int width, int height) {
        Entity other = new Entity(x, y, width, height);
        return getCollisionSide(other);
    }


}

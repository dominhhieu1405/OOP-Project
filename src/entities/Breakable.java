package entities;

public interface Breakable {
    // Public static final
    int DEFAULT_HIT_POINTS = 1; // Số hit mặc định
    double DEFAULT_POWERUP_CHANCE = 0.2; // Xác suất rơi power-up
    int DEFAULT_SCORE_VALUE = 100; // Điểm khi phá gạch

    // abstract method
    /**
     * Khi bóng va chạm với gach.
     * 
     * @return true nếu gach bị phá hủy
     */
    boolean hit();

    /**
     * Kiểm tra gạch đã bị phá chưa.
     */
    boolean isDestroyed();
    /**
     * Vẽ gạch lên màn hình
     */
}
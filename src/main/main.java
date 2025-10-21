package main;
import javax.swing.SwingUtilities;
import game.GameEngine;
public class main {
    public static void main(String[] args) {
        /**
         * Khởi chạy giao diện người dùng trong luồng sự kiện Swing
         * Điều này giúp tránh các vấn đề liên quan đến đa luồng khi làm việc với
         * giao diện người dùng Swing
         */

        // SwingUtilities.invokeLater(() -> {
        //     GameEngine game = new GameEngine();
        //     game.start();
        // });

        SwingUtilities.invokeLater(() -> {
            GameEngine game = new GameEngine();
            // chạy vòng lặp trong background thread
            Thread loop = new Thread(game::start, "GameLoop");
            loop.setDaemon(true); // tùy chọn: cho thread là daemon để không ngăn JVM exit
            loop.start();
        });
    }
}
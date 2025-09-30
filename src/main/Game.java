package main;
import core.GameEngine;

public class Game {
    public static void main(String[] args) {
        System.out.println("Starting Arkanoid Game...");
        
        // Tạo và khởi tạo game engine
        GameEngine gameEngine = new GameEngine();
        
        // Load resources (nếu cần)
        // gameEngine.loadResources();
        
        // Bắt đầu game
        gameEngine.startGame();
        
        // Chương trình sẽ chạy cho đến khi user đóng window
        // GameEngine sẽ tự động cleanup khi window đóng
    }
}

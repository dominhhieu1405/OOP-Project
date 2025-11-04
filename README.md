# 🎮 BÁO CÁO BÀI TẬP LỚN OOP – GAME ARKANOID

## 🧩 1. Giới thiệu

**Arkanoid** là trò chơi “đập gạch” cổ điển được phát triển bằng **Java** với phong cách hiện đại.  
Người chơi điều khiển thanh đỡ (Paddle) để bật bóng phá hủy các khối gạch trên màn hình, thu thập các **Power-up** (vật phẩm tăng sức mạnh) và vượt qua các **màn chơi (Map)** có độ khó tăng dần.

Dự án được phát triển theo **Lập trình Hướng đối tượng (OOP)**, áp dụng đầy đủ bốn nguyên lý:  
- Đóng gói (Encapsulation)  
- Kế thừa (Inheritance)  
- Đa hình (Polymorphism)  
- Trừu tượng hóa (Abstraction)

---

## 🎯 2. Video demo

🎥 **Link video:** [https://youtu.be/your-demo-link](https://youtu.be/your-demo-link)

Trong video nhóm trình bày:
- Cách tổ chức mã nguồn, chia **package** rõ ràng theo chức năng.
- Các **interface và lớp trừu tượng** dùng để tách biệt hành vi.
- Phần xử lý **logic game – va chạm, phản xạ vật lý** nhóm tâm đắc.
- Demo gameplay: khởi tạo, di chuyển paddle, va chạm bóng, phá gạch, nhận power-up.
- Giải thích sơ đồ **biểu đồ lớp UML** và mối quan hệ giữa các lớp.

---

## 🧱 3. Tổ chức mã nguồn

### 🌲 Cấu trúc thư mục

```
Arkanoid/
│
├── assets/                        # Tài nguyên game (ảnh, âm thanh, font)
│   ├── fonts/
│   ├── images/
│   ├── sounds/
│   └── index.html
│
├── data/                          # Dữ liệu game (map, điểm)
│   ├── maps/
│   │   ├── Map1.txt … Map11.txt   # Bố cục gạch từng màn
│   │   ├── maps.txt               # Danh sách map mở khóa
│   │   ├── test.txt
│   │   └── README.md
│   ├── highscores.txt             # Lưu điểm cao
│
├── src/                           # Mã nguồn chính
│   ├── Constant/                  # Hằng số toàn cục
│   │   └── Constant.java
│   │
│   ├── entity/                    # Các đối tượng trong game
│   │   ├── Entity.java            # Lớp cha trừu tượng (tọa độ, kích thước)
│   │   ├── Ball.java              # Quả bóng
│   │   ├── Paddle.java            # Thanh đỡ người chơi
│   │   │
│   │   ├── block/                 # Các loại gạch
│   │   │   ├── Block.java
│   │   │   ├── BlockBedrock.java
│   │   │   ├── BlockBomb.java
│   │   │   └── BlockLucky.java
│   │   │
│   │   └── powerUp/               # Các loại vật phẩm
│   │       ├── PowerUp.java
│   │       ├── PowerUpBallExpand.java
│   │       ├── PowerUpBallExtraLife.java
│   │       ├── PowerUpBallFast.java
│   │       ├── PowerUpBallFire.java
│   │       ├── PowerUpBallShrink.java
│   │       ├── PowerUpBallSlow.java
│   │       ├── PowerUpCatchBall.java
│   │       ├── PowerUpPaddleExpand.java
│   │       ├── PowerUpPaddleFast.java
│   │       ├── PowerUpPaddleShrink.java
│   │       ├── PowerUpPaddleSlow.java
│   │       └── PowerUpRandom.java
│   │
│   ├── game/                      # Logic và giao diện trò chơi
│   │   ├── GameEngine.java
│   │   ├── GamePanel.java
│   │   ├── Scene.java
│   │   └── scenes/
│   │       ├── MenuScene.java
│   │       ├── GameScene.java
│   │       ├── MapScene.java
│   │       ├── Pause.java
│   │       ├── GameOver.java
│   │       └── Win.java
│   │
│   ├── manager/                   # Quản lý tài nguyên & trạng thái
│   │   ├── BlockManager.java
│   │   ├── MapManager.java
│   │   ├── PowerUpManager.java
│   │   └── SoundManager.java
│   │
│   └── main/                      # Điểm khởi động chương trình
│       └── Main.java
│
└── README.md 
```

### 💡 Giải thích nhanh
- **entity/**: Các đối tượng hiển thị trong game (Ball, Paddle, Block, PowerUp).  
- **game/**: Điều khiển logic, cập nhật khung hình, xử lý input, hiển thị GUI.  
- **manager/**: Singleton quản lý các tài nguyên (âm thanh, bản đồ, vật phẩm).  
- **data/**: Lưu trữ dữ liệu ngoài, dễ mở rộng mà không cần thay code.  
- **Constant.java**: Khai báo kích thước khung hình, tốc độ, màu, file đường dẫn.  

---

## 🧩 4. Các Interface và nguyên lý OOP áp dụng

| Nguyên lý | Ứng dụng trong dự án |
|------------|----------------------|
| **Encapsulation (Đóng gói)** | Các thuộc tính như `x`, `y`, `velocityX`, `velocityY` được khai báo `private`, truy cập qua getter/setter. |
| **Inheritance (Kế thừa)** | `BlockBedrock`, `BlockBomb`, `BlockLucky` kế thừa từ `Block`. Tương tự các lớp PowerUp kế thừa từ `PowerUp` cha. |
| **Polymorphism (Đa hình)** | Phương thức `activate()` và `deactivate()` được ghi đè ở từng loại PowerUp với hiệu ứng riêng. |
| **Abstraction (Trừu tượng hóa)** | `Entity` và `Scene` là lớp trừu tượng định nghĩa hành vi chung (`update()`, `render()`). |
| **Design Pattern** | Sử dụng **Singleton Pattern** trong các lớp `MapManager`, `BlockManager`, `SoundManager`. |

---

## ⚙️ 5. Phần xử lý code nhóm tâm đắc

Nhóm đặc biệt tâm đắc với phần **xử lý va chạm bóng – thanh đỡ – gạch**.  
Cụ thể, va chạm được xác định theo góc tiếp xúc, tạo phản xạ tự nhiên và chính xác:

```java
if (ball.intersects(paddle)) {
    double hitPos = (ball.getCenterX() - paddle.getX()) / paddle.getWidth();
    ball.setVelocity(Vector2D.reflect(ball.getVelocity(), hitPos));
    SoundManager.play("bounce");
}
````

* Bóng thay đổi hướng dựa vào vị trí va chạm.
* Giúp người chơi có thể “điều khiển” quỹ đạo bóng bằng kỹ năng.
* Tích hợp âm thanh và hiệu ứng rung nhẹ khi va chạm.

Ngoài ra, nhóm còn xây dựng hệ thống:

* **Map động**: Load dữ liệu `.txt` để tạo cấp độ tự động.
* **Power-up ngẫu nhiên**: Xuất hiện với xác suất nhất định khi phá gạch.
* **GameLoop đa luồng**: Đảm bảo FPS ổn định ~60.

---

## 🧭 6. Bản thiết kế – Biểu đồ lớp UML

```
Entity
 ├── Paddle
 ├── Ball
 ├── Block
 │    ├── BlockBedrock
 │    ├── BlockBomb
 │    └── BlockLucky
 └── PowerUp
      ├── PowerUpBallExpand
      ├── PowerUpBallFire
      ├── PowerUpPaddleExpand
      └── PowerUpRandom

Scene
 ├── MenuScene
 ├── GameScene
 ├── MapScene
 ├── Pause
 ├── GameOver
 └── Win

Manager
 ├── MapManager
 ├── BlockManager
 ├── PowerUpManager
 └── SoundManager
```

Quan hệ kế thừa, trừu tượng, và sử dụng Singleton được thể hiện rõ theo UML.
(Lược đồ UML đầy đủ được trình bày trong file `UML.puml` hoặc bản PDF đính kèm.)

---

## 👥 7. Danh sách nhóm

| STT | Họ và tên        | Vai trò        | Công việc phụ trách                                      |
| --- | ---------------- | -------------- | -------------------------------------------------------- |
| 1   | **Nguyễn Văn A** | Nhóm trưởng    | Thiết kế UML, tổ chức package, GameEngine, xử lý va chạm |
| 2   | **Trần Thị B**   | Lập trình viên | Thiết kế giao diện GUI, MenuScene, MapScene              |
| 3   | **Lê Văn C**     | Lập trình viên | BlockManager, MapManager, hệ thống bản đồ                |
| 4   | **Phạm Minh D**  | Lập trình viên | PowerUpManager, các lớp PowerUp                          |
| 5   | **Vũ Thị E**     | Báo cáo – Demo | Chuẩn bị README, video demo, báo cáo trình bày           |

---

## 🧠 8. Công nghệ sử dụng

* **Ngôn ngữ:** Java 17
* **Giao diện:** Java Swing
* **Đa luồng:** Thread + Runnable Game Loop
* **Thiết kế UML:** PlantUML / StarUML
* **IDE:** IntelliJ IDEA / NetBeans
* **Quản lý mã nguồn:** GitHub
* **Âm thanh:** WAV / MP3 (SoundManager)

---

## 🏁 9. Kết luận

Dự án **Arkanoid OOP** giúp nhóm:

* Củng cố kiến thức thiết kế hướng đối tượng qua một sản phẩm thực tế.
* Rèn kỹ năng teamwork, chia module và tổ chức code rõ ràng.
* Vận dụng OOP kết hợp với giao diện, âm thanh, và đa luồng để tạo trải nghiệm mượt mà.

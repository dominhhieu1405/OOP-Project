# 🎮 BÁO CÁO BÀI TẬP LỚN OOP – GAME ARKANOID

## 🧩 1. Giới thiệu

**Arkanoid** là trò chơi “đập gạch” cổ điển được phát triển bằng **Java Swing** với phong cách hiện đại.  
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

## ⚙️ 5. Hệ thống overlay scene và quản lý nút tương tác

Phần mình tâm đắc nhất là thiết kế hệ thống các overlay (Pause / GameOver / Win) và cách quản lý các nút tương tác đi kèm. Thiết kế này tách biệt rõ ràng giữa logic game core và UI overlay, giúp code dễ bảo trì và mở rộng.

Những điểm chính:
- Kiến trúc: `GamePanel` là container chính, chứa `Scene` hiện tại. `GameScene` chịu trách nhiệm vẽ màn chơi và, khi game bị dừng, hiển thị các overlay bằng cách vẽ lớp phủ và gọi `render()` của overlay tương ứng.
- Overlay singleton: Các overlay (`Pause`, `GameOver`, `Win`) dùng kiểu Singleton để giữ một bộ `JButton` duy nhất trong suốt vòng đời ứng dụng. Điều này tránh tạo/xóa nút nhiều lần và giữ trạng thái nhất quán.
- Thêm nút an toàn: Mỗi overlay cung cấp `addButtonsToPanel(JPanel panel)` — trước khi `add`, có kiểm tra `button.getParent() != panel` để tránh add trùng.
- Vị trí nút cố định: Overlay đặt vị trí nút bằng `button.setBounds(x,y,w,h)`; do đó `GameScene` sử dụng layout null (`setLayout(null)`) và `setPreferredSize(...)` để `setBounds` có hiệu lực.
- Xoá nút khi chuyển trạng thái: `GameScene` có hàm `RemoveAllButton()` dùng để loại bỏ các nút overlay cũ trước khi reset hoặc chuyển scene — đảm bảo không còn nút sót lại trên panel.

Hợp đồng ngắn (inputs / outputs / effect):
- Inputs: trạng thái game (playing / pause / gameover / win), sự kiện từ `JButton`.
- Outputs: thêm/loại bỏ `JButton` trên `GameScene`, gọi `GamePanel.setScene(...)`, hoặc gọi `GameScene.resetScene()` / `continueGame()`.

Các trường hợp biên cần lưu ý:
- Focus & key bindings: khi đổi scene cần cập nhật key bindings (GamePanel xóa listeners cũ và gọi `scene.setupKeyBindings()` nếu cần).
- Double-add: overlay kiểm tra parent trước khi add để tránh add nhiều lần.
- Reset state: khi khởi động lại level, cần loại bỏ nút overlay cũ (hiện thực bằng `RemoveAllButton()`).

Lưu ý về workaround hiện tại
- Hiện tại `GameScene` sử dụng `RemoveAllButton()` như một biện pháp tạm thời để đảm bảo không còn nút overlay cũ sót lại khi chuyển trạng thái (ví dụ khi restart hoặc quay về Menu). Đây là phương án phòng ngừa cho một bug nhỏ trong luồng thêm nút.
- Kế hoạch sửa chính thức: chuyển sang cơ chế báo hiệu (flag) khi trạng thái scene thay đổi — chỉ thêm các nút overlay khi phát hiện sự thay đổi trạng thái. Cách này sẽ loại trừ nhu cầu xoá toàn bộ nút mỗi lần và quản lý lifecycle của các nút chính xác hơn.

Kiểm thử nhanh:
- Thua → xuất hiện `GameOver` với các nút (Chơi lại, Menu). Nhấn Chơi lại → `GameScene.resetScene()` được gọi, không còn nút thừa, các entity được reset.
- Pause → resume bằng nút Tiếp tục hoặc phím tắt; xác nhận key bindings và trạng thái paddle/ball.

Gợi ý mở rộng:
- Thay Singleton bằng factory/DI nếu cần nhiều cấu hình overlay khác nhau.
- Thêm animation (fade-in/out) khi overlay xuất hiện để cải thiện UX.

---

## 👥 6. Danh sách nhóm

| Họ và Tên         | Nhiệm vụ                                                                 |
|-------------------|---------------------------------------------------------------------------|
| **Đỗ Minh Hiếu**  | - BlockManager, MapManager, PowerUpManager, SoundManager<br>- Menu, Map, Pause<br>- JUnit tester |
| **Nguyễn Quốc Huy** | - Multi-threading<br>- Code base<br>- GameScene, GameOver, Win |
| **Nguyễn Mạnh Đức** | - Paddle<br>- PowerUp<br>- Block |
| **Lương Minh Dương** | - Ball<br>- Physics |

---

## 🧠 7. Công nghệ sử dụng

* **Ngôn ngữ:** Java 17
* **Giao diện:** Java Swing
* **Đa luồng:** Thread + Timer
* **IDE:** IntelliJ IDEA / VsCode
* **Quản lý mã nguồn:** GitHub
* **Âm thanh:** WAV / MP3 (SoundManager)

---

## 🏁 8. Kết luận

Dự án **Arkanoid OOP** giúp nhóm:

* Củng cố kiến thức thiết kế hướng đối tượng qua một sản phẩm thực tế.
* Rèn kỹ năng teamwork, chia module và tổ chức code rõ ràng.
* Vận dụng OOP kết hợp với giao diện, âm thanh, và đa luồng để tạo trải nghiệm mượt mà.

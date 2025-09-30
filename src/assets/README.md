# Asset Loading và Rendering Guide

## Cấu trúc Assets đã tạo:

### 1. **Assets.java** - Singleton Asset Manager
- Load images, sounds, fonts
- Cache assets để tái sử dụng
- Tạo placeholder khi file không tồn tại

### 2. **Sprite.java** - Sprite Rendering
- Render images với transformations
- Scale, rotate, offset
- Sub-image rendering (sprite sheets)

### 3. **GameScene.java** - Demo Usage
- Load và render sprites
- Fallback rendering khi assets không có

## Cách sử dụng:

### Load Assets:
```java
Assets assets = Assets.getInstance();
BufferedImage image = assets.loadImage("paddle.png");
Sprite sprite = new Sprite("paddle.png");
```

### Render Sprites:
```java
sprite.render(g2d, x, y);                    // Normal render
sprite.render(g2d, x, y, width, height);     // Scaled render
sprite.setScale(2.0f, 1.0f);                 // Set scale
sprite.setRotation(45);                      // Set rotation
```

### Folder structure cần thiết:
```
src/assets/
├── images/
│   ├── paddle.png
│   ├── ball.png
│   ├── brick_red.png
│   └── background.png
├── sounds/
│   ├── hit.wav
│   └── break.wav
└── fonts/
    └── game_font.ttf
```

## Testing:
1. Đặt file images vào `src/assets/images/`
2. Run game - sẽ thấy placeholders (màu magenta) nếu file không có
3. Add real assets để thấy sprites thật

## Features:
- ✅ Automatic caching
- ✅ Placeholder fallbacks  
- ✅ Error handling
- ✅ Multiple render modes
- ✅ Resource disposal
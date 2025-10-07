# Format

<>: Bắt buộc

[]: Tùy chọn (Nếu k đặt thì sẽ là giá trị mặc định)

## Block thường
```
normal <x> <y> <width> <height> [HP=2]
```

## Block cứng (Block cứng không thể phá hủy)
```
bedrock <x> <y> <width> <height>
```

## Block bom
```
bomb <x> <y> <width> <height> [HP=1] [damage=1] [range=100]
```

## Block may mắn
``` 
lucky <x> <y> <width> <height> [HP=1] [type=-1]
```
- -1: Random
- 0: Tăng kích thước bóng
- 1: Tăng số mạng
- 2: Bóng di chuyển nhanh hơn
- 3: Bóng lửa (Phả hủy tất cả block, kể cả bedrock)
- 4: Thu nhỏ kích thước bóng
- 5: Bóng di chuyển chậm hơn
- 6: Đưa bóng lên ván
- 7: Tăng chiều rộng ván
- 8: Tăng tốc độ ván
- 9: Giảm chiều rộng ván
- 10: Giảm tốc độ ván
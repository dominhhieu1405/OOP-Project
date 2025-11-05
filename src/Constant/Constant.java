package Constant;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Constant {
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;

    public static final int PADDLE_WIDTH = 100;
    public static final int PADDLE_HEIGHT = 20;
    public static final int PADDLE_Y_OFFSET = 50;

    public static final int BALL_RADIUS = 10;
    public static final int TOTAL_BALL_HEART = 3;

    public static final int BLOCK_DEFAULT_WIDTH = 100; // Chiều rộng (Đã bao gồm padding).
    public static final int BLOCK_DEFAULT_HEIGHT = 50; // Chiều cao (Đã bao gồm padding).
    public static final int BLOCK_DEFAULT_HP = 1; // Số máu mặc định của block.
    public static final int BLOCK_PADDING = 2; // Khoảng cách giữa các block với nhau và với viền khung hình.

    public static final String FONT_PATH = "assets/fonts/Arkanoid-Regular.ttf";

    public static final Image BALL_IMG = new ImageIcon("assets/images/snowball.png").getImage();
    public static final Image FIREBALL_IMG = new ImageIcon("assets/images/fireball.png").getImage();
    public static final Image BACKGROUND_IMG = new ImageIcon("assets/images/background.jpg").getImage();
    public static final Image LOGO_IMG = new ImageIcon("assets/images/logo.png").getImage();
    public static final Image FAVICON = new ImageIcon("assets/images/favicon.png").getImage();
    public static final Image HEART_IMG = new ImageIcon("assets/images/heart.png").getImage();
    public static final Image HEART0_IMG = new ImageIcon("assets/images/heart0.png").getImage();
    public static final Image BLOCK_1_IMG = new ImageIcon("assets/images/Block/Stone.png").getImage();
    public static final Image BLOCK_2_IMG = new ImageIcon("assets/images/Block/Iron.png").getImage();
    public static final Image BLOCK_3_IMG = new ImageIcon("assets/images/Block/Gold.png").getImage();
    public static final Image BLOCK_4_IMG = new ImageIcon("assets/images/Block/Diamond.png").getImage();
    public static final Image BLOCK_5_IMG = new ImageIcon("assets/images/Block/Emerald.png").getImage();
    public static final Image BLOCK_BOMB_IMG = new ImageIcon("assets/images/Block/tnt.png").getImage();
    public static final Image BLOCK_BEDROCK_IMG = new ImageIcon("assets/images/Block/bedrock.png").getImage();
    public static final Image BLOCK_LUCKY_IMG = new ImageIcon("assets/images/Block/luckyblock.png").getImage();
    public static final Image BUTTON_IMG = new ImageIcon("assets/images/btn.png").getImage();
    public static final Image BUTTON_ACTIVE_IMG = new ImageIcon("assets/images/btn-active.png").getImage();

    public static final Image GAMEOVER_BACKGROUND_IMG = new ImageIcon("assets/images/gameoverBackground.png").getImage();
    public static final ImageIcon MENU_BUTTON_IMG = new ImageIcon("assets/images/menuButton.png");
    public static final ImageIcon RESPAWN_BUTTON_IMG = new ImageIcon("assets/images/respawnButton.png");

    public static final String MAPS_PATH = "data/maps/";

    public static final Image[] EFFECT_BREAKING_IMG = new Image[10];

    public static Font f20;

    static {
        for (int i = 0; i < 10; i++) {
            EFFECT_BREAKING_IMG[i] = new ImageIcon("assets/images/breaking" + i + ".png").getImage();
        }


        try {
            f20 = Font.createFont(Font.TRUETYPE_FONT, new File(Constant.FONT_PATH)).deriveFont(20f);
        } catch (Exception e) {
            f20 = new Font("Verdana", Font.BOLD, 28);
        }
    }



    /**
     * Tạo JButton với hình nền là ảnh.
     * @param text chữ
     * @return jbutton
     */
    public static JButton createBtn(String text) {
        JButton button = new JButton(text) {
            protected void paintComponent(Graphics g) {
                Image img = getModel().isRollover() ? Constant.BUTTON_ACTIVE_IMG : Constant.BUTTON_IMG;
                g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
                g.setFont(Constant.f20);
                g.setColor(Color.WHITE);

                FontMetrics fm = g.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() + textHeight) / 2 - 4;

                g.drawString(getText(), x, y);

                if (!isEnabled()){
                    g.setColor(new Color(0, 0, 0, 72));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };

        // Xóa border, background Swing mặc định
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setRolloverEnabled(true);
        button.setForeground(Color.WHITE);

        return button;
    }

}

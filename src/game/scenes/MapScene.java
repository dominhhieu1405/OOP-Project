package game.scenes;

import Constant.Constant;
import manager.MapManager;

import java.awt.*;

public class MapScene extends game.Scene {
    private Font f;
    public int page;

    public MapScene (){
        super(Constant.BACKGROUND_IMG);
        setLayout(null);
        setPreferredSize(new Dimension(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT));
        MapManager.getInstance().load();
        changePage(1);
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.drawImage(Constant.BACKGROUND_IMG, 0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT, null);
        // Resize để width bằng 60% của FRAME_WIDTH
        int logoWidth = (int)(Constant.FRAME_WIDTH * 0.75);
        int logoHeight = (logoWidth * Constant.LOGO_IMG.getHeight(null)) / Constant.LOGO_IMG.getWidth(null);

        g.drawImage(Constant.LOGO_IMG, (Constant.FRAME_WIDTH - logoWidth) / 2, logoHeight/3 - 36, logoWidth, logoHeight, null);
    }

    public void changePage(int newPage) {
        if (newPage != page && newPage > 0 && newPage <=  MapManager.getInstance().maps.size() / 9 + 1) {
            page = newPage;
            MapManager.getInstance().setPage(newPage);
            for (MapManager.Map mp : MapManager.getInstance().maps) {
                remove(mp.button);
            }
            for (MapManager.Map mp : MapManager.getInstance().getMapsByPage()) {
                add(mp.button);
            }
        }
    }

    public boolean useMouse() {
        return false;
    }
    public boolean useKeyboard() {
        return false;
    }
}

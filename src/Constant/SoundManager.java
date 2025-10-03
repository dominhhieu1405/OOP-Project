package Constant;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SoundManager {
    private static HashMap<String, Clip> sounds = new HashMap<>();

    public static void init(){
        loadSound("click", "assets/sounds/wooden.wav");
        loadSound("dead", "assets/sounds/dead.wav");
        //loadSound("bgm", "assets/sounds/bgm.wav");
    }

    /**
     * Cache file âm thanh
     * @param key  tên
     * @param path đường dẫn tới file .wav
     */

    public static void loadSound(String key, String path) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            sounds.put(key, clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Phát một hiệu ứng âm thanh (reset từ đầu)
     */
    public static void play(String key) {
        Clip clip = sounds.get(key);
        if (clip != null) {
            if (clip.isRunning()) clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }

    /**
     * Phát nhạc nền loop liên tục
     */
    public static void loop(String key) {
        Clip clip = sounds.get(key);
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * Dừng phát âm thanh
     */
    public static void stop(String key) {
        Clip clip = sounds.get(key);
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}

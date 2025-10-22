package manager;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SoundManager {
    private static HashMap<String, Clip> sounds = new HashMap<>();

    /**
     * Khởi tạo và load tất cả file âm thanh.
     */
    public static void init(){
        System.out.println("Loading sounds...");
        try {
            loadSound("click", "assets/sounds/wooden.wav");
            loadSound("dead", "assets/sounds/dead.wav");
            loadSound("break", "assets/sounds/break.wav");
            loadSound("explode", "assets/sounds/explode.wav");
            loadSound("bgm", "assets/sounds/background.wav");
            System.out.println("Sounds loaded.");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error initializing SoundManager.");
        }
    }

    /**
     * Load file âm thanh.
     * @param key  tên
     * @param path đường dẫn tới file .wav
     */

    public static void loadSound(String key, String path) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(path));
            System.out.println("Loading sound: " + key + " from " + path);
            // Error line below
            Clip clip = AudioSystem.getClip();
            // ^
            System.out.println("Loaded clip for sound: " + key);
            clip.open(ais);
            sounds.put(key, clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error loading sound: " + key + " from " + path);
            e.printStackTrace();
        }
    }

    /**
     * Phát một hiệu ứng âm thanh (reset từ đầu).
     */
    public static void play(String key) {
        Clip clip = sounds.get(key);
        if (clip != null) {
            // System.out.println("Play sound: " + key);
            if (clip.isRunning()) clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }

    /**
     * Phát nhạc nền loop liên tục.
     */
    public static void loop(String key) {
        Clip clip = sounds.get(key);
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * Dừng phát âm thanh.
     */
    public static void stop(String key) {
        Clip clip = sounds.get(key);
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}

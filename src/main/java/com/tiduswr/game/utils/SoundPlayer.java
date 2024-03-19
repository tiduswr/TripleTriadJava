package com.tiduswr.game.utils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.*;

public class SoundPlayer {
    private Clip clip;

    public SoundPlayer(String path) {
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(path);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void stop() {
        if(clip != null){
            clip.stop();
            clip.close();
        }
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}

package design.adapter;

/**
 * Created by abc on 2015/7/20.
 */
public class Mp4MediaPlayer implements AdvanceMediaPlayer {
    @Override
    public void playVlv(String file) {

    }

    @Override
    public void playMp4(String file) {
        System.out.printf("play mp4"+file);
    }
}

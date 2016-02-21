package design.adapter;

/**
 * Created by abc on 2015/7/20.
 */
public class VlcMediaPlayer implements AdvanceMediaPlayer {
    @Override
    public void playVlv(String file) {
        System.out.printf("play vlc"+file);
    }

    @Override
    public void playMp4(String file) {

    }
}

package adapter;

/**
 * Created by abc on 2015/7/20.
 */
public class MediaAdapter implements MediaPlayer {

    AdvanceMediaPlayer advanceMediaPlayer;

    public MediaAdapter(String type){
            if(type.equalsIgnoreCase("mp4")){
                advanceMediaPlayer=new Mp4MediaPlayer();
            }else if(type.equalsIgnoreCase("vlc")){
                advanceMediaPlayer=new VlcMediaPlayer();
            }
    }

    @Override
    public void play(String type, String file) {
            if(type.equalsIgnoreCase("mp4")){
                advanceMediaPlayer.playMp4(file);
            }else if(type.equalsIgnoreCase("vlc")){
                advanceMediaPlayer.playVlv(file);
            }
    }
}

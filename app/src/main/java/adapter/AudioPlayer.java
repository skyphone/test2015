package adapter;

/**
 * Created by abc on 2015/7/20.
 */
public class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String type, String file) {

        if(type.equalsIgnoreCase("mp3")){
            System.out.printf("mp3 play");
        }else if(type.equalsIgnoreCase("mp4")||type.equalsIgnoreCase("vlc")){
            mediaAdapter=new MediaAdapter(type);
            mediaAdapter.play(type,file);
        }else{
            System.out.printf("unknow file");
        }
    }
}

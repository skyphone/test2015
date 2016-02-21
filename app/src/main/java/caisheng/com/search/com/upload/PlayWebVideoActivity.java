package caisheng.com.search.com.upload;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.VideoView;

import com.danikula.videocache.CacheListener;
import com.danikula.videocache.FileNameGenerator;
import com.danikula.videocache.HttpProxyCacheServer;
import com.danikula.videocache.Md5FileNameGenerator;

import java.io.File;

import caisheng.com.search.R;

public class PlayWebVideoActivity extends Activity implements CacheListener {
    //String url="http://swf.ws.126.net/openplayer/v01/-0-2_MB4SGU93O_MB4SHDDCB-vimg1_ws_126_net//image/snapshot_movie/2015/10/6/4/MB4SHDB64-1429002752199.swf";
    //String url="http://www.w3school.com.cn/example/html5/mov_bbb.mp4";
    String url = "http://www.quirksmode.org/html5/videos/big_buck_bunny.mp4";
    VideoView videoView;
    SeekBar seekBar;
    ProgressBar progressBar;
    HttpProxyCacheServer proxy;
    FileNameGenerator nameGenerator;
    int currentProgress = 100;
    int selectProgress = 0;
    int allLength=1;
    boolean isFirst = true;
    boolean isLoading=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_web_video);
        initView();

        nameGenerator = new Md5FileNameGenerator(getExternalCacheDir());
       /* File f=new File(getExternalCacheDir().getPath());

        for(File ff:f.listFiles()){
            ff.delete();
        }*/
        Log.e("path", getExternalCacheDir().getAbsolutePath());
        proxy = new HttpProxyCacheServer(nameGenerator);//要单例的。
        proxy.registerCacheListener(this, url);
        String proxyUrl = proxy.getProxyUrl(url);


        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        // videoView.setVideoURI(Uri.parse(url));
        videoView.setVideoPath(proxyUrl);

    }

    private void initView() {
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                     selectProgress=progress * 100/ allLength ;
                    Log.e("seekbar",selectProgress+" percent "+progress+" progress-- "+currentProgress+" current   "+allLength+" alllength");
                    if ( selectProgress> currentProgress) {
                        isLoading=true;
                        videoView.pause();
                       // seekBar.removeCallbacks(onEverySecond);
                        progressBar.setVisibility(View.VISIBLE);
                        //seekBar.setProgress(progress);
                    }else{
                        videoView.seekTo(progress);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        videoView = (VideoView) findViewById(R.id.play_web_video_view);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                allLength=videoView.getDuration();
                seekBar.setMax(videoView.getDuration());
            }
        });
    }

    @Override
    public void onCacheAvailable(File cacheFile, String url, int percentsAvailable) {
        Log.e("percent", percentsAvailable + "");
        currentProgress = percentsAvailable;
        if (percentsAvailable >= 500 && isFirst) {
            isFirst = false;
            progressBar.setVisibility(View.GONE);
            videoView.start();
            seekBar.postDelayed(onEverySecond, 1000);
        } else if (percentsAvailable >= selectProgress&&isLoading) {
            isLoading=false;
            progressBar.setVisibility(View.GONE);
            videoView.start();
            videoView.seekTo(selectProgress*allLength/100-5000);
           // seekBar.postDelayed(onEverySecond, 1000);
        }

    }

    Runnable onEverySecond = new Runnable() {
        public void run() {
          //  if (videoView.isPlaying()) {
                seekBar.setProgress(videoView.getCurrentPosition());
                Log.e("current", videoView.getCurrentPosition() + "");
                seekBar.postDelayed(onEverySecond, 1000);
           // }
        }
    };

    public void down(View v){
        nameGenerator = new Md5FileNameGenerator(getExternalCacheDir());
        proxy = new HttpProxyCacheServer(nameGenerator);//要单例的。
        proxy.registerCacheListener(this, url);

        videoView.setVideoPath(proxy.getProxyUrl(url));
    }
    public void stop(View v){
        proxy.shutdown();
    }
}

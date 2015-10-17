package otcapp.com.upload;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import com.danikula.videocache.FileNameGenerator;
import com.danikula.videocache.HttpProxyCacheServer;
import com.danikula.videocache.Md5FileNameGenerator;

import caisheng.com.search.R;

public class PlayWebVideoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_web_video);
        FileNameGenerator nameGenerator = new Md5FileNameGenerator(getExternalCacheDir());
        Log.e("path",getExternalCacheDir().getAbsolutePath());
        HttpProxyCacheServer proxy = new HttpProxyCacheServer(nameGenerator);//要单例的。


        VideoView videoView = (VideoView) findViewById(R.id.play_web_video_view);
//Use a media controller so that you can scroll the video contents
//and also to pause, start the video.
        //String url="http://swf.ws.126.net/openplayer/v01/-0-2_MB4SGU93O_MB4SHDDCB-vimg1_ws_126_net//image/snapshot_movie/2015/10/6/4/MB4SHDB64-1429002752199.swf";
         //String url="http://www.w3school.com.cn/example/html5/mov_bbb.mp4";
        String url="http://www.quirksmode.org/html5/videos/big_buck_bunny.mp4";
        //String url="/storage/sdcard1/movie/魂断蓝桥.mkv";
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
       // videoView.setVideoURI(Uri.parse(url));
        String proxyUrl = proxy.getProxyUrl(url);
        videoView.setVideoPath(proxyUrl);
        videoView.start();
    }
}

package caisheng.com.search.com.upload;

import android.app.Activity;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.googlecode.mp4parser.BasicContainer;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import com.googlecode.mp4parser.authoring.container.mp4.MovieCreator;
import com.googlecode.mp4parser.authoring.tracks.AppendTrack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import caisheng.com.search.R;

public class CameraActivity extends Activity implements View.OnClickListener, SurfaceHolder.Callback {
    MediaRecorder recorder;
    SurfaceHolder holder;
    boolean recording = false;
    int filename=0;
    int timeCount=0;
    TextView tv_time;
    Timer T;//计时器
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        recorder = new MediaRecorder();
        initRecorder();
        setContentView(R.layout.activity_camera);

        SurfaceView cameraView = (SurfaceView) findViewById(R.id.activity_camera_surface);
        tv_time=(TextView)findViewById(R.id.activity_camear_tv);
        holder = cameraView.getHolder();
        holder.addCallback(this);
        cameraView.setClickable(true);
        cameraView.setOnClickListener(this);
    }

    /**
     * 初始化录制参数
     */
    private void initRecorder() {
        recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        recorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);

        CamcorderProfile cpHigh = CamcorderProfile
                .get(CamcorderProfile.QUALITY_480P);
        recorder.setProfile(cpHigh);
        recorder.setOutputFile("/sdcard/" + filename + ".mp4");
        recorder.setMaxDuration(1800000); // 30 minutes
        recorder.setMaxFileSize(500000000); // Approximately 500 megabytes

    }

    private void prepareRecorder() {
        recorder.setPreviewDisplay(holder.getSurface());
        try {
            recorder.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            finish();
        } catch (IOException e) {
            e.printStackTrace();
            finish();
        }
    }

    /**
     * 界面点击开始录制，再点击停止录制
     * @param v
     */
    public void onClick(View v) {

        if (recording) {
            T.cancel();
            recorder.stop();
            recording = false;
            filename++;
            // Let's initRecorder so we can record again
            initRecorder();
            prepareRecorder();
        } else {
            recording = true;
            recorder.start();
            T=new Timer();
            T.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_time.setText("count=" + timeCount);//计时器
                            timeCount++;
                        }
                    });
                }
            }, 0, 1000);
        }
    }




    public void surfaceCreated(SurfaceHolder holder) {
        prepareRecorder();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        if (recording) {
            recorder.stop();
            recording = false;
        }
        recorder.release();
        finish();
    }

    /**
     * 开始拼接视频
     * @param view
     * @throws IOException
     */
    public void start(View view) throws IOException {
        appendVideo2();

    }

    public void appendVideo2(){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {

                super.onPreExecute();
                Toast.makeText(getApplicationContext(),"start",Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Movie[] inMovies = new Movie[filename];
                    for(int i=0;i<filename;i++){
                        inMovies[i] = MovieCreator.build("/sdcard/" + i + ".mp4");
                    }

                    List<Track> videoTracks = new LinkedList<Track>();
                    List<Track> audioTracks = new LinkedList<Track>();
                    for (Movie m : inMovies) {
                        for (Track t : m.getTracks()) {
                            if (t.getHandler().equals("soun")) {
                                audioTracks.add(t);
                            }
                            if (t.getHandler().equals("vide")) {
                                videoTracks.add(t);
                            }
                        }
                    }

                    Movie result = new Movie();

                    if (audioTracks.size() > 0) {
                        result.addTrack(new AppendTrack(audioTracks
                                .toArray(new Track[audioTracks.size()])));
                    }
                    if (videoTracks.size() > 0) {
                        result.addTrack(new AppendTrack(videoTracks
                                .toArray(new Track[videoTracks.size()])));
                    }

                    BasicContainer out = (BasicContainer) new DefaultMp4Builder()
                            .build(result);

                    FileChannel fc = new RandomAccessFile(String.format(Environment
                            .getExternalStorageDirectory() + "/append.mp4"),
                            "rw").getChannel();
                    out.writeContainer(fc);
                    fc.close();
                } catch (FileNotFoundException e) {
                } catch (IOException e) {
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(),"end",Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}


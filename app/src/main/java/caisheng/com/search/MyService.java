package caisheng.com.search;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by abc on 2015/9/10.
 */
public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("service","onbind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("service","oncreate");
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("service","onStartCommand");
        new Thread(){
            @Override
            public void run() {
                super.run();
                for (int i = 0; i <20 ; i++) {
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.e("service",i+"eeeeeeeeee");
                }
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("service","ondestory");
    }


}

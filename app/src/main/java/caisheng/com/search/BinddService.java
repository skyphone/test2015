package caisheng.com.search;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by abc on 2015/9/10.
 */
public class BinddService extends Service {



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("service", "onbind");
        return new Binder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("service","oncreate");
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("service","onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("service","ondestory");
    }
}

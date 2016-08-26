package shopping;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import shopping.main.UserInfo;
import shopping.util.CrashHandler;

/**
 * Created by abc on 2015/4/22.
 */
public class MyApplication extends
        Application {
    public static UserInfo userInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        //异常处理
        CrashHandler crashHandler=CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        userInfo=new UserInfo();


    }



}

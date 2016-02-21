package caisheng.com.search;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import caisheng.com.search.com.login.LoginInfo;

/**
 * Created by abc on 2015/4/22.
 */
public class MyApplication extends
        Application {

    public LoginInfo loginInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .build();
        Fresco.initialize(this);
        loginInfo = new LoginInfo();
        // SDKInitializer.initialize(getApplicationContext());   //baidu map
    }


}

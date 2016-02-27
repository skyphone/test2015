package shopping;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import shopping.main.UserInfo;

/**
 * Created by abc on 2015/4/22.
 */
public class MyApplication extends
        Application {

    public UserInfo userInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .build();
        Fresco.initialize(this);
        userInfo=new UserInfo();

    }


}

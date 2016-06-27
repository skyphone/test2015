package shopping;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import design.mvp.dagger.AppComponent;
import design.mvp.dagger.AppModule;
import design.mvp.dagger.DaggerAppComponent;
import shopping.main.UserInfo;

/**
 * Created by abc on 2015/4/22.
 */
public class MyApplication extends
        Application {
    private static final String BASE_URL = "https://api.github.com/";
    public UserInfo userInfo;
    static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .build();
        Fresco.initialize(this);
        userInfo=new UserInfo();
        setup();
    }

    protected void setup(){
        appComponent= DaggerAppComponent.builder().appModule(new AppModule(BASE_URL)).build();
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }

}

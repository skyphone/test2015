package design.mvp.dagger;

import javax.inject.Singleton;

import dagger.Component;
import design.mvp.MvpActivity;
import design.mvp.domain.GithubApi;

/**
 * Created by lenovo on 2016/6/27.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    GithubApi getRestService();

    void inject(MvpActivity mvpActivity);
}

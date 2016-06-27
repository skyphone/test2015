package design.mvp.dagger;

import dagger.Module;
import dagger.Provides;
import design.mvp.domain.GithubApi;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by lenovo on 2016/6/27.
 */
@Module
public class AppModule {
    private String baseUrl;
    public AppModule(String baseUrl){
        this.baseUrl=baseUrl;
    }

    @Provides
    GithubApi provideService(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build();
        GithubApi githubApi=retrofit.create(GithubApi.class);
        return githubApi;
    }
}

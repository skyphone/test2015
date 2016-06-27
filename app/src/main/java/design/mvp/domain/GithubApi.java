package design.mvp.domain;

import java.util.List;

import design.mvp.model.Repository;
import design.mvp.model.User;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by lenovo on 2016/6/27.
 */
public interface GithubApi {
    @GET("users/{username}")
    Call<User> getUserProfile(@Path("username") String username);

    @GET("users/{username}/repos")
    Call<List<Repository>> getUserRespositoies(@Path("username") String username);
}

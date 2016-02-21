package shop.test.network;

import com.google.gson.JsonObject;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import shop.test.QzoneListBean;

/**
 * Created by Administrator on 2016/2/21.
 */
public interface Qzone {

    @GET("/users/{user}")
    public void getFeed(@Path("user") String user);

    @POST("/Shop/GetQzoneList")
    Call<QzoneListBean> getQzoneList(@Body JsonObject bean);




}

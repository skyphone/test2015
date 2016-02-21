package shopping.myinterface;

import com.google.gson.JsonObject;

import caisheng.com.search.test.QzoneListBean;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Administrator on 2016/2/21.
 */
public interface VisitInterface {

    @GET("/users/{user}")
    public void getFeed(@Path("user") String user);


    //获取列表
    @POST("/Shop/GetQzoneList")
    Call<QzoneListBean> getQzoneList(@Body JsonObject bean);

    @POST("/Shop/Register")
    Call<JsonObject> register(@Body JsonObject jsonObject);

    @POST("/Shop/Login")
    Call<JsonObject> login(@Body JsonObject jsonObject);

    @POST("/Shop/AddQzone")
    Call<JsonObject> addQzone(@Body JsonObject jsonObject);



}

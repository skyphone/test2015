package shopping.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.JsonObject;

import caisheng.com.search.R;
import caisheng.com.search.test.network.Qzone;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import shopping.BaseConfig;
import shopping.MyApplication;
import shopping.main.GuideActivity;
import shopping.main.HomeActivity;
import shopping.util.CustomRetroFit;
import shopping.util.PrefUtils;
/**
 * 启动页页面
 *@auth ccs
 *create at 2016/3/28 23:20
 */
public class LunchActivity extends Activity {
    public static final String ISLOADED = "isLoad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        login();
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean isFirstLoad = preferences.getBoolean(ISLOADED, false);
        if (!isFirstLoad) {
            preferences.edit().putBoolean(ISLOADED, true).apply();
            startActivity(new Intent(this, GuideActivity.class));
            finish();
        } else {
            getWindow().getDecorView().setBackgroundResource(R.drawable.lunch_icon);
            getWindow().getDecorView().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(LunchActivity.this, HomeActivity.class));
                    finish();
                }
            }, 2000);
        }
    }


    /*自动登录*/
    public void login() {
        String username = PrefUtils.getFromPrefs(this, BaseConfig.USERNAME, "");
        String passWord = PrefUtils.getFromPrefs(this, BaseConfig.PASSWORD, "");
        if (username.equals("") || passWord.equals("")) {
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userName", username);
        jsonObject.addProperty("passWord", passWord);

        Retrofit retrofit = CustomRetroFit.getRetrofit();
        retrofit.create(Qzone.class).login(jsonObject).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject object = response.body();
                if (object.get("status").getAsString().equals("succ")) {
                    Toast.makeText(LunchActivity.this, object.get("info").getAsString(), Toast.LENGTH_SHORT).show();
                    MyApplication.userInfo.userName = PrefUtils.getFromPrefs(LunchActivity.this, BaseConfig.USERNAME, "");
                    MyApplication.userInfo.isLoaded=true;
                } else {

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                MyApplication.userInfo.isLoaded=false;
            }
        });
    }
}

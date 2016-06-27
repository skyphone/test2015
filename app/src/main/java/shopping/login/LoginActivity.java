package shopping.login;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;

import caisheng.com.search.R;
import caisheng.com.search.test.StringEncrypt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import shopping.BaseConfig;
import shopping.MyApplication;
import shopping.myinterface.VisitInterface;
import shopping.util.CustomRetroFit;
import shopping.util.PrefUtils;

public class LoginActivity extends Activity {
    private EditText login_name_edt;
    private EditText login_pswd_edt;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        login_name_edt= (EditText) findViewById(R.id.login_name_edt);
        login_pswd_edt=(EditText) findViewById(R.id.login_pswd_edt);
        btnLogin=(Button) findViewById(R.id.login_btn);
    }


    /**
     * 登录
     */
    public void login(View v) {

        final String userName=login_name_edt.getText().toString();
        final String pass=login_pswd_edt.getText().toString();
        if(TextUtils.isEmpty(userName)){
            login_name_edt.setError("用户名不能为空");
            return;
        }

        if(TextUtils.isEmpty(pass)){
            login_pswd_edt.setError("密码不能为空");
            return;
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userName", userName);
        jsonObject.addProperty("passWord", StringEncrypt.EncryptBySHA_256(pass));

        Retrofit retrofit = CustomRetroFit.getRetrofit();

        retrofit.create(VisitInterface.class).login(jsonObject).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject object=response.body();
                if(object.get("status").getAsString().equals("succ")){
                    PrefUtils.saveToPrefs(LoginActivity.this, BaseConfig.USERNAME, userName);
                    PrefUtils.saveToPrefs(LoginActivity.this, BaseConfig.PASSWORD, StringEncrypt.EncryptBySHA_256(pass));//密码加密保存

                    Toast.makeText(LoginActivity.this, object.get("info").getAsString(), Toast.LENGTH_SHORT).show();
                    MyApplication.userInfo.isLoaded=true;
                    MyApplication.userInfo.userName=login_name_edt.getText().toString();
                    setResult(RESULT_OK);
                    finish();
                }else{
                    MyApplication.userInfo.isLoaded=false;
                    Toast.makeText(LoginActivity.this, object.get("info").getAsString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                MyApplication.userInfo.isLoaded=false;
            }
        });

    }
}

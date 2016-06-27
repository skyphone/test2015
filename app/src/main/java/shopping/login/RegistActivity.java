package shopping.login;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import caisheng.com.search.R;
import caisheng.com.search.test.network.Qzone;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import shopping.util.CustomRetroFit;


public class RegistActivity extends Activity {


    private EditText editUserName;
    private EditText ed_pass;
    private EditText ed_pass_again;
    private Button btn_regis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        editUserName=(EditText)findViewById(R.id.ed_username);
        ed_pass=(EditText)findViewById(R.id.ed_pass);
        ed_pass_again=(EditText)findViewById(R.id.ed_pass_again);
        btn_regis=(Button)findViewById(R.id.btn_regis);
    }


    /**
     * 注册
     */
    public void regis(View v) {
        if(TextUtils.isEmpty(editUserName.getText().toString())){
            editUserName.setError("请输入用户名");
            return;
        }
        if(TextUtils.isEmpty(ed_pass.getText().toString())){
            ed_pass.setError("请输入密码");
            return;
        }

        if(TextUtils.isEmpty(ed_pass_again.getText().toString())){
            ed_pass_again.setError("请再次输入密码");
            return;
        }

        if(!ed_pass.getText().toString().equals(ed_pass_again.getText().toString())){
            ed_pass_again.setError("密码输入不一致");
            return;
        }


        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userName", editUserName.getText().toString());
        jsonObject.addProperty("passWord", ed_pass.getText().toString());
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
        jsonObject.addProperty("createTime", dateFormat.format(new Date()));

        Retrofit retrofit = CustomRetroFit.getRetrofit();
        retrofit.create(Qzone.class).register(jsonObject).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject object=response.body();
                if(object.get("status").getAsString().equals("succ")){
                    Toast.makeText(RegistActivity.this, object.get("info").getAsString(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegistActivity.this, object.get("info").getAsString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
}

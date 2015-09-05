package otcapp.com.login;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import caisheng.com.search.MyApplication;
import caisheng.com.search.R;
import caisheng.com.search.VolleryInstance;

public class LoginActivity extends Activity {
    String url = "http://otcapp.hkbf.com.cn/api/ApiUser/Login";
    EditText edName, edPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edName = (EditText) findViewById(R.id.ed_username);
        edPass = (EditText) findViewById(R.id.ed_pass);
    }

    public void login(View v) {
        final String userNmae = edName.getText().toString();
        final String pass = edPass.getText().toString();
        if (TextUtils.isEmpty(userNmae)) {
            edName.setError("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            edPass.setError("请输入密码");
            return;
        }

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject jsonObject=new JSONObject(s);
                    if(jsonObject.optString("status").equals("100")){
                        ((MyApplication)getApplication()).loginInfo=new Gson().fromJson(jsonObject.optString("data"),LoginInfo.class);
                        Toast.makeText(getApplicationContext(),"登陆成功",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, null) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map=new HashMap<String, String>();
                map.put("mobile",userNmae);
                map.put("pwd", Base64.encode(pass.getBytes()));
                return map;
            }
        };

        VolleryInstance.getInstance(this).addToRequestQueue(request);

    }
}

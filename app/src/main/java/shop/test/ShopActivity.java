package shop.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import caisheng.com.search.R;
import caisheng.com.search.VolleryInstance;

public class ShopActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);



    }


    /**
     * 文件上次
     */
    public void file(View v){
        new Thread(){
            @Override
            public void run() {
                super.run();
                HttpClient httpClient = new DefaultHttpClient() ;

                HttpPost httpPost = new HttpPost("http://192.168.123.1:8080/Shop/FileUpload");
                MultipartEntity entity = new MultipartEntity();
                File file1=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/123");
                File file2=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/2.txt");

                entity.addPart("file", new FileBody(file1));
                entity.addPart("file", new FileBody(file2));

                httpPost.setEntity(entity);

                HttpResponse response = null;

                try {
                    response = httpClient.execute(httpPost);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                    String json = reader.readLine();

                    Log.e("ss", json);
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e("IOException : "+e, e.getMessage());

                }
            }
        }.start();

    }

    /**
     * 注册
     */
    public void regis(View v){
        String url = "http://192.168.123.1:8080/Shop/Register";
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("userName", "cai1");
            jsonObject.put("passWord", "cai");
            jsonObject.put("createTime", "2015");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Toast.makeText(ShopActivity.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
                Log.e("home", jsonObject.toString());
            }
        }, null);


        VolleryInstance.getInstance(this).addToRequestQueue(jsonRequest);
    }

    /**
     * 登录
     */
    public void login(View v){
        String url = "http://192.168.123.1:8080/Shop/Login";
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("userName", "cai1");
            jsonObject.put("passWord", StringEncrypt.EncryptBySHA_256("cai"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Toast.makeText(ShopActivity.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
                Log.e("home", jsonObject.toString());
            }
        }, null);


        VolleryInstance.getInstance(this).addToRequestQueue(jsonRequest);
    }

    /* public void regis(){
        String url = "http://192.168.123.1:8080/Shop/Register";
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(MyVolley.this, s, Toast.LENGTH_SHORT).show();
                        Log.e("content", s);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("error",volleyError.toString());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // JSONObject params = new JSONObject();
                Map<String, String> jsonObject = new HashMap<>();
                jsonObject.put("userName", "cai");
                jsonObject.put("passWord", "qweq");
                jsonObject.put("createTime", "2015");
                return jsonObject;
            }


        };



        VolleryInstance.getInstance(this).addToRequestQueue(request);
    }*/
}

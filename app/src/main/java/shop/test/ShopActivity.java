package shop.test;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import java.util.ArrayList;

import caisheng.com.search.R;
import caisheng.com.search.VolleryInstance;

public class ShopActivity extends Activity {
    ArrayList<String> files=new ArrayList<>();
    private String imagUrls="";
    public static String baseUrl="http://192.168.1.133:8080/Shop/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);



    }


    /**
     * 多图选择
     * @param v
     */
    public void multi(View v){
        Intent chooseIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        chooseIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(chooseIntent, 2);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if(requestCode==2&&resultCode==RESULT_OK){
                    if (data != null) {
                        files.clear();
                        ClipData clipData = data.getClipData();
                        if (clipData != null) {
                            for (int i = 0; i < clipData.getItemCount(); i++) {
                                ClipData.Item item = clipData.getItemAt(i);
                                Uri uri = item.getUri();

                               files.add(uri.getPath());
                            }
                        }
                    }
                }
    }

    /**
     * 提交数据
     * @param v
     */
    public void submitQzone(View v){
            uploadFile(true);
    }


    /**
     * 文件上次
     */
    public void file(View v){
        uploadFile(false);

    }
    /**
     * 文件上次
     */
    private void uploadFile(final boolean send) {

        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                HttpClient httpClient = new DefaultHttpClient() ;

                HttpPost httpPost = new HttpPost(baseUrl+"FileUpload");
                MultipartEntity entity = new MultipartEntity();
              /*  File file1=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/123");
                File file2=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/2.txt");
                entity.addPart("file", new FileBody(file1));
                entity.addPart("file", new FileBody(file2));*/
                for (String s:files) {
                    File file=new File(s);
                    entity.addPart("file", new FileBody(file));
                }

                httpPost.setEntity(entity);

                HttpResponse response = null;

                try {
                    response = httpClient.execute(httpPost);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                    String content = reader.readLine();
                    JSONObject json= null;
                    try {
                        json = new JSONObject(content);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(json.optString("status").equals("succ")){
                        imagUrls=json.optString("list");
                    }



                    Log.e("ss", content);
                } catch (ClientProtocolException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    Log.e("IOException : " + e, e.getMessage());


                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if(send){
                    String url = baseUrl+"AddQzone";
                    JSONObject jsonObject = new JSONObject();
                    try {
                        EditText ed=(EditText)findViewById(R.id.editText2);
                        jsonObject.put("content", ed.getText().toString());
                        jsonObject.put("images",imagUrls);
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

                    VolleryInstance.getInstance(ShopActivity.this).addToRequestQueue(jsonRequest);
                }

            }
        }.execute();


    }


    /**
     * 列表
     */
    public void getSpaceList(View v){
       startActivity(new Intent(this,QzoneListActivity.class));
    }

    /**
     * 注册
     */
    public void regis(View v){
        String url = baseUrl+"Register";
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

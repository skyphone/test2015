package shop.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import caisheng.com.search.R;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import shop.test.network.Qzone;

public class QzoneListActivity extends Activity {


    private ListView listView;
    private QzoneListAdapter adapter;
    List<QzoneBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qzone_list);

        listView = (ListView) findViewById(R.id.listview);
        adapter = new QzoneListAdapter(list, this);
        listView.setAdapter(adapter);
        getSpaceList();
    }


    /**
     * 列表
     */
/*    public void getSpaceList(){
        String url = ShopActivity.baseUrl+"GetQzoneList";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page", 0);
            jsonObject.put("pageCount", 10);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e("home", jsonObject.toString());

                if (jsonObject.optString("status").equals("succ")) {

                    ArrayList<QzoneBean> b = new Gson().fromJson(jsonObject.optJSONArray("list").toString(), new TypeToken<ArrayList<QzoneBean>>() {
                    }.getType());
                    list.addAll(b);
                    adapter.notifyDataSetChanged();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("error",volleyError.getMessage());
            }
        });


        VolleryInstance.getInstance(this).addToRequestQueue(jsonRequest);
    }*/
    public void getSpaceList() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("page", 0);
        jsonObject.addProperty("pageCount", 200);

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(ShopActivity.baseUrl).build();

        Qzone qzone = retrofit.create(Qzone.class);
        Call<QzoneListBean> listBean = qzone.getQzoneList(jsonObject);

        listBean.enqueue(new Callback<QzoneListBean>() {
            @Override
            public void onResponse(Response<QzoneListBean> response) {
                if (response.body().status.equals("succ")) {
                    list.addAll(response.body().list);
                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", t.getMessage());
            }
        });

    }


}

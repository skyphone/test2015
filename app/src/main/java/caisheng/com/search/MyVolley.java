package caisheng.com.search;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by abc on 2015/6/30.
 */
public class MyVolley extends Activity {

    public static final String TAG = "tag";
    ImageView imageView;
    ArrayList<String> list = new ArrayList<String>();
    ListView listView;
    ImageLoader imageLoader;

    //SwipeRefreshLayout swipeRefreshLayout;
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public void next(View v) {
        Test.isNumeric("");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_three);
        //setTranslucentStatus(true);
        listView = (ListView) findViewById(R.id.listView);
        imageView = (ImageView) findViewById(R.id.imageView);
       /* swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getApplicationContext(), "refresh", Toast.LENGTH_SHORT).show();

                new Thread() {
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }.start();
            }
        });*/
        imageLoader = VolleryInstance.getInstance(getApplicationContext()).getImageLoader();
       /* TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("tab1").setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("tab2").setContent(R.id.tab2));*/

   /*     try {
            String j = "{\"desc\":[{\"type\":\"img\",\"value\":\"M00/11/5C/CgkeMlVZnxTS_LrwAAEBK7dQT54977.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZponBBe1CAAEX1ZbSng0353.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/5C/CgkeMlVZnxTRKx_EAADFZi1v43U442.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZpomSeRjeAAExsSyJ-co089.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/5C/CgkeMlVZnxSzUM5fAAExRRgdSE0470.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZpomSBZEhAAELElVWZkM211.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZponwnkx9AAQa30EVCa0793.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZpomBqQetAASZsl5Qj4E390.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZporhiztIAATw9UGmbS4187.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZpoqQhYVlAAT4zUNQE48841.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZpovDlu0YAAZGx89rYE4146.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0B/CgkeM1VZnxXROLVwAAD7QehT1Yg758.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZpoqDnskWAADyaofGF8k225.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZpovjs3D5AAQPrQkesj4744.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZpovwaSVBAANTNcQMrd4005.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZpouz9nOdAANItZrQ0gc106.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZpouDrpe8AARoK-NqT6g800.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZpoySTneDAAVQK0_Majw273.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/5C/CgkeMlVZnxWy9ScxAAIweogh0nk985.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0B/CgkeM1VZnxTgt0tGAAMSdJT3y6M647.jpg\",\"size\":\"0\"}]}";
            JSONArray jsonArray = new JSONObject(j).optJSONArray("desc");
            String s = "";
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add("http://img1.aolaigo.com/group1/" + jsonArray.getJSONObject(i).optString("value"));
            }
           // Log.v("abc", s);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        try {
           /* sendString(new VolleyCallBack() {
                @Override
                public void onSucc(String response) {
                    Log.e("callback",response);
                }
            });
            requestImage();
            setListImage();
            requestJson();
           */
           // UpdateAdress();
        } catch (Exception e) {
            e.printStackTrace();
        }


      /*  String url="mqqwpa://im/chat?chat_type=wpa&uin=400470778";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));*/
    }


    public void sendString(final VolleyCallBack callBack) throws Exception {
        String myUrl = "http://api.map.baidu.com/telematics/v3/weather?location=" + URLEncoder.encode("北京", "utf-8") + "&output=json&ak=EC57b8ffc279a3eda12d4486a4f7cf03&qq-pf-to=pcqq.c2c";


        StringRequest request = new StringRequest(Request.Method.GET, myUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("mess", s);
                callBack.onSucc(s);
            }
        }, null);
        request.setTag(TAG);
        VolleryInstance.getInstance(this).addToRequestQueue(request);
    }


    public void requestImage() throws Exception {

        String img = "http://img1.aolaigo.com/group1/M00/02/FB/CgkeM1WaN5CRW9-mAAERwo5HLKk259.jpg";
        ImageRequest imageRequest = new ImageRequest(img, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }
        }, 0, 0, null, null);
        VolleryInstance.getInstance(this).addToRequestQueue(imageRequest);

    }

    public void setListImage() {
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.iv, parent, false);
                }
                NetworkImageView imageView = (NetworkImageView) convertView.findViewById(R.id.ivv);
                imageView.setImageUrl(list.get(position).toString(), imageLoader);
                /*imageLoader.get(list.get(position).toString(),
                        ImageLoader.getImageListener(imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher));*/
                return convertView;
            }
        };
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView, true);
    }


    /**
     * 设置listView高度
     */

    public static void setListViewHeightBasedOnChildren(ListView listView,
                                                        Boolean flag) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int len = 1;
        if (flag)
            len = listAdapter.getCount();
        for (int i = 0; i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += (listItem.getMeasuredHeight() - 2); // 统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度

        listView.setLayoutParams(params);
    }



    public void requestJson() {
        String url = "http://memberapi.aolaigo.com/appmember.ashx";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("opt", "7");
        map.put("cmd", "3");
        JSONObject jsonObject = new JSONObject(map);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                TextView textView = new TextView(getApplicationContext());
                textView.setText(jsonObject.toString());
                //listView.addHeaderView(textView);
            }
        }, null);
        VolleryInstance.getInstance(this).addToRequestQueue(request);
    }

    public void json() {
        String url = "http://cms.aolaigo.com/Handler/app_ActivityHandler.ashx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("opt", "2");
            jsonObject.put("cmd", "2");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e("home", jsonObject.toString());
            }
        }, null);*/

        StringRequest jsonRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
               Datas d=new Gson().fromJson(s,Datas.class);
               // int abc=d.error;
            }
        },null);

        VolleryInstance.getInstance(this).addToRequestQueue(jsonRequest);
    }





    class Datas{
        int error;
        String  msg;

    }

    public void UpdateAdress() throws Exception {

        final String url = "http://otcapp.hkbf.com.cn/api/ApiUser/EditAddress";
        // 传递的参数


        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.e("content", s);
                    }
                }, null)
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // JSONObject params = new JSONObject();
                Map<String, String> params = new HashMap<>();
                params.put("id", "222");
                params.put("uid", "03945345-d117-4821-b39d-b7a8381b71b1");
                params.put("name", "zengy54333i");
                params.put("province", "北京");
                params.put("city", "东城区");
                params.put("county", "东华门街道");
                params.put("addr", "123456789");
                params.put("mobile", "13590404481");
                params.put("zipcode", "525357");
                params.put("isdefault", 1 + "");
                return params;
            }


        };



        VolleryInstance.getInstance(this).addToRequestQueue( request);

    }


}

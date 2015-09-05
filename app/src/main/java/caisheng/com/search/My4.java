package caisheng.com.search;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.ArrayList;

/**
 * Created by abc on 2015/7/25.
 * 收货地址
 */
public class My4 extends Activity {
    String json = "{\"OS\":\"Android\",\"address_parent_code\":\"0\",\"app_key\":\"001\",\"app_version\":\"1.4.0\",\"channel\":\"\",\"crc\":\"23363675574cc02c6b2acd1232247601\",\"deviceToken\":\"\",\"imei\":\"866500020632560\",\"uid\":\"201504080935082566\",\"time_stamp\":\"2015-07-31  11:09:03\",\"pagesize\":0,\"pageindex\":0,\"opt\":3,\"loginType\":1,\"cmd\":91}";
    String url = "http://memberapi.taolaigo.com/appmember.ashx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lllll);

        CustomCaiView customCaiView = (CustomCaiView) findViewById(R.id.custom);
        Log.e("s", customCaiView.onclick + "---" + customCaiView.num1);

        method1();

    }


    public void method1() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("keyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void dia(final Data data) {
        final ArrayList<Code> list = data.data;

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                this);
        builderSingle.setIcon(R.drawable.ic_launcher);
        builderSingle.setTitle("Select One Name:-");
        builderSingle.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        BaseAdapter arrayAdapter = new BaseAdapter() {
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
                convertView = getLayoutInflater().inflate(R.layout.select_item_list, parent, false);
                CheckedTextView tv = (CheckedTextView) convertView.findViewById(R.id.ck);
                tv.setText(list.get(position).address_name);
                return convertView;
            }
        };


        builderSingle.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request(list.get(which).address_code);
                    }
                });
        builderSingle.show();
    }

    public void show(View v) {
        //setContentView(R.layout.iv);
        request(0);
    }

    private void request(int code) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            jsonObject.put("address_parent_code", code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Data data = new Gson().fromJson(jsonObject.toString(), Data.class);
                dia(data);
            }
        }, null);
        VolleryInstance.getInstance(this).addToRequestQueue(request);
    }

    class Data {
        String error, msg;
        ArrayList<Code> data = new ArrayList<Code>();
    }

    class Code {
        int address_code, id;
        String address_name;
    }

}

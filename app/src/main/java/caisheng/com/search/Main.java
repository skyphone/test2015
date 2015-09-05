package caisheng.com.search;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.liuguangqiang.swipeback.SwipeBackActivity;
import com.liuguangqiang.swipeback.SwipeBackLayout;

import java.net.URLEncoder;

public class Main extends SwipeBackActivity {

	RequestQueue mQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setDragEdge(SwipeBackLayout.DragEdge.LEFT);
		//mQueue = Volley.newRequestQueue(this);
		mQueue=Volley.newRequestQueue(this.getApplicationContext());
		
		try {
			//sendString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendString() throws Exception {
		String myUrl = "http://api.map.baidu.com/telematics/v3/weather?location="
				+ URLEncoder.encode("����", "utf-8")
				+ "&output=json&ak=EC57b8ffc279a3eda12d4486a4f7cf03&qq-pf-to=pcqq.c2c";

		StringRequest request = new StringRequest(Method.GET, myUrl,
				new Listener<String>() {
					@Override
					public void onResponse(String s) {
						Log.e("mess", s);
					}
				}, null);

	/*	String myUrl = "http://api.map.baidu.com/telematics/v3/weather?location=" + URLEncoder.encode("北京", "utf-8") + "&output=json&ak=EC57b8ffc279a3eda12d4486a4f7cf03&qq-pf-to=pcqq.c2c";


		StringRequest request = new StringRequest(Request.Method.GET, myUrl, new Response.Listener<String>() {
			@Override
			public void onResponse(String s) {
				Log.e("mess", s);
			}
		}, null);*/
		mQueue.add(request);

	}

}

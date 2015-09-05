package caisheng.com.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SecondFragment extends Fragment {

    ViewFlipper flipper;
    ViewPager pager;
    ImageView[] ims=new ImageView[3];


    ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_second, container, false);
        listView=(ListView)v.findViewById(R.id.lv22);
        flipper = (ViewFlipper) v.findViewById(R.id.viewFlipper);
        pager=(ViewPager)v.findViewById(R.id.senond_pager);
        ImageView image = new ImageView(getActivity());
        image.setImageResource(R.drawable.a);
        flipper.addView(image);

        image = new ImageView(getActivity());
        image.setImageResource(R.drawable.b);
        flipper.addView(image);

        image = new ImageView(getActivity());
        image.setImageResource(R.drawable.c);
        flipper.addView(image);

        flipper.startFlipping();




        PagerAdapter ad=new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView im=new ImageView(getActivity());
                im.setImageResource(R.drawable.a);
                container.addView(im);
                return im;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View)object);

            }
        };

        pager.setAdapter(ad);



        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListImage();
       // ((ViewPagerActivity)getActivity()).fragments[0]

    }

    public void setListImage() {
      final  ArrayList<String> list=new ArrayList<String>();
        try {
            String j = "{\"desc\":[{\"type\":\"img\",\"value\":\"M00/11/5C/CgkeMlVZnxTS_LrwAAEBK7dQT54977.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZponBBe1CAAEX1ZbSng0353.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/5C/CgkeMlVZnxTRKx_EAADFZi1v43U442.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZpomSeRjeAAExsSyJ-co089.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/5C/CgkeMlVZnxSzUM5fAAExRRgdSE0470.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZpomSBZEhAAELElVWZkM211.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZponwnkx9AAQa30EVCa0793.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZpomBqQetAASZsl5Qj4E390.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZporhiztIAATw9UGmbS4187.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZpoqQhYVlAAT4zUNQE48841.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZpovDlu0YAAZGx89rYE4146.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0B/CgkeM1VZnxXROLVwAAD7QehT1Yg758.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZpoqDnskWAADyaofGF8k225.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZpovjs3D5AAQPrQkesj4744.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZpovwaSVBAANTNcQMrd4005.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZpouz9nOdAANItZrQ0gc106.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZpouDrpe8AARoK-NqT6g800.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZpoySTneDAAVQK0_Majw273.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/5C/CgkeMlVZnxWy9ScxAAIweogh0nk985.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0B/CgkeM1VZnxTgt0tGAAMSdJT3y6M647.jpg\",\"size\":\"0\"}]}";

            JSONArray jsonArray = new JSONObject(j).optJSONArray("desc");
            String s = "";
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add("http://img1.aolaigo.com/group1/" + jsonArray.getJSONObject(i).optString("value"));

            }
            Log.v("abc", s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.iv, parent, false);
                }
                NetworkImageView imageView = (NetworkImageView) convertView.findViewById(R.id.ivv);
                imageView.setImageUrl(list.get(position).toString(), VolleryInstance.getInstance(getActivity()).getImageLoader());
                /*imageLoader.get(list.get(position).toString(),
                        ImageLoader.getImageListener(imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher));*/
                return convertView;
            }
        };
        listView.setAdapter(adapter);
        MyVolley.setListViewHeightBasedOnChildren(listView, true);
    }

}

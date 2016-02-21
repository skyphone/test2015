package shopping.home;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import caisheng.com.search.R;


/**
 * Created by Administrator on 2015/9/18.
 */
public class HomeFragment extends Fragment {

    ArrayList<ImageView> list=new ArrayList<>();
    int currentPage=0;
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.home_fragment,container,false);
        viewPager=(ViewPager)v.findViewById(R.id.home_viewpager);
        return v;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageView imageView1=new ImageView(getActivity());
        imageView1.setImageResource(R.drawable.gen11);
        ImageView imageView2=new ImageView(getActivity());
        imageView2.setImageResource(R.drawable.gen22);
        ImageView imageView3=new ImageView(getActivity());
        imageView3.setImageResource(R.drawable.gen33);
        ImageView imageView4=new ImageView(getActivity());
        imageView4.setImageResource(R.drawable.gen44);
        list.add(imageView1);
        list.add(imageView2);
        list.add(imageView3);
        list.add(imageView4);



        PagerAdapter adapter=new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {

                return view==object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(list.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(list.get(position));
                return list.get(position);
            }
        };
        viewPager.setAdapter(adapter);



        final Handler handler = new Handler();

        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == list.size()) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage, false);
                currentPage++;
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 100, 2000);
    }
}

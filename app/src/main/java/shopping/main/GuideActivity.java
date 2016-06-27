package shopping.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import caisheng.com.search.R;
/**
 *引导页面
 *@auth ccs
 *create at 2016/3/28 23:21
 */
public class GuideActivity extends Activity {
    private ViewPager viewPager;
    private int guide[]={R.drawable.a,R.drawable.b,R.drawable.c};
    private ImageView[] imgs=new ImageView[guide.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewPager= (ViewPager) findViewById(R.id.view_pager);
        LinearLayout.LayoutParams paams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        for (int i = 0; i < guide.length; i++) {
            ImageView img=new ImageView(this);
            img.setLayoutParams(paams);
            img.setImageResource(guide[i]);
            imgs[i]=img;
        }

        PagerAdapter adapter=new PagerAdapter() {
            @Override
            public int getCount() {
                return guide.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(imgs[position]);
                return imgs[position];
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView(imgs[position]);

            }
        };

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==guide.length-1){
                    viewPager.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(GuideActivity.this, HomeActivity.class));
                            finish();
                        }
                    },1000);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setAdapter(adapter);
    }


}

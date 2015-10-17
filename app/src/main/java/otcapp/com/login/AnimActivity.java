package otcapp.com.login;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import caisheng.com.search.R;

public class AnimActivity extends ActionBarActivity {
    ListView lv4;
    ImageView iv;
    String[] list1={"i","am","chen","xi","ping","hei","hei"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim2);
        lv4=(ListView)findViewById(R.id.listView4);
        iv=(ImageView)findViewById(R.id.image12);
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list1);
        lv4.setAdapter(adapter1);




    }

    public void go(View v){
        ObjectAnimator animator=ObjectAnimator.ofFloat(lv4,"rotationY",0f,90f);
        animator.setDuration(1000);
        animator.start();
    }
}

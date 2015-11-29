package anim;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

import caisheng.com.search.R;

public class AnimActivity extends Activity {
    ImageView imageView6;
    ImageView imgList;
    AnimationDrawable animationDrawable;
    GridLayout gridView;
    int numButtons=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim3);

        //属性动画
        imageView6=(ImageView)findViewById(R.id.imageView6);
        final Animation animation= AnimationUtils.loadAnimation(this,R.anim.view_anim);
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView6.startAnimation(animation);
            }
        });

        //帧动画
        imgList=(ImageView)findViewById(R.id.img_list);
        imgList.setBackgroundResource(R.anim.anim_list);
        animationDrawable=(AnimationDrawable)imgList.getBackground();


        gridView=(GridLayout)findViewById(R.id.gridView);
        Button addButton=(Button)findViewById(R.id.button41);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button newButton=new Button(AnimActivity.this);
                newButton.setText(String.valueOf(numButtons++));
                gridView.addView(newButton,Math.min(1,gridView.getChildCount()));
            }
        });



    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus)
        animationDrawable.start();
    }

  /*  @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){

            animationDrawable.start();
        }
        return super.onTouchEvent(event);
    }*/
}

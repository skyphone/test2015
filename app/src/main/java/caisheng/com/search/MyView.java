package caisheng.com.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by abc on 2015/7/8.
 */
public class MyView extends ViewGroup {

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Button bt=new Button(context);
        bt.setText("caiping");
        this.addView(bt);

        TextView tv=new TextView(context);
        tv.setText("asdfasdf");
        this.addView(tv);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
           int count=getChildCount();
        for (int i = 0; i <count ; i++) {
            View v=getChildAt(i);
            v.layout(l,t,r,b);
        }
    }
    @Override
    //对每个子View进行measure():设置每子View的大小，即实际宽和高
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        //通过init()方法，我们为该ViewGroup对象添加了三个视图 ， Button、 ImageView、TextView
        int childCount = getChildCount() ;

        //设置本ViewGroup的宽高
        setMeasuredDimension(300 , 300) ;




        for(int i=0 ;i<childCount ; i++){
            View child = getChildAt(i) ;   //获得每个对象的引用
            child.measure(50, 50) ;   //简单的设置每个子View对象的宽高为 50px , 50px
            //或者可以调用ViewGroup父类方法measureChild()或者measureChildWithMargins()方法
            //this.measureChild(child, widthMeasureSpec, heightMeasureSpec) ;
        }

    }

  /*  public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array=context.getTheme().obtainStyledAttributes(attrs,R.styleable.MyView,0,0);
        //boolean show=array.getBoolean(R.styleable.MyView_showText,false);
        //int dir=array.getInt(R.styleable.MyView_labelPosition,1);

    }*/
}

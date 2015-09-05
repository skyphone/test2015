package caisheng.com.search;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by abc on 2015/8/17.
 */
public class CustomCaiView extends View {


    String onclick;

    int num1;
    Paint paint;


    public CustomCaiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        TypedArray array=context.getTheme().obtainStyledAttributes(attrs,R.styleable.CustomCaiView,0,0);
        onclick=array.getString(R.styleable.CustomCaiView_onclick);
        num1=array.getInt(R.styleable.CustomCaiView_num,0);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        //canvas.drawCircle(getWidth()/2,getHeight()/2,100,paint);

       // canvas.drawLine(10,10,100,200,paint);

       canvas.drawText("asdfadf"+y,10,y,paint);

       /* Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.mm);
        canvas.drawBitmap(bitmap,10,10,paint);*/

    }

        float y=0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()== MotionEvent.ACTION_MOVE){
            y=event.getY();
            invalidate();
        }


        return true;
    }


    /*    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      int minW=getPaddingLeft()+getPaddingRight()+getSuggestedMinimumWidth();
        int w=resolveSizeAndState(minW,widthMeasureSpec,1);
    }*/
}

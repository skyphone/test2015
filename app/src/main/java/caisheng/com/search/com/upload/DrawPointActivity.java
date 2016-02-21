package caisheng.com.search.com.upload;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2015/12/19.
 */
/**
 * 
 *@auth ccs
 *create at 2015/12/30 21:39
 */
public class DrawPointActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));

    }

    public static class MyView extends View{
        private Paint mPaint=new Paint();
        private float[] mPts;

        public static final float  SIZE=600;
        public static final int SEGS=20;
        public static final int X=0;
        public static final int Y=1;
        private float[] caiLine={0,0,0,200,0,200,200,0,200,200,200,0,200,0,0,0};

        private void buildPoints(){
            final int ptCount=(SEGS+1)*2;
            mPts=new float[ptCount*2];

            float value=0;
            final float delta=SIZE/SEGS;
            for (int i = 0; i <=SEGS ; i++) {
                mPts[i*4 + X]=SIZE-value;
                mPts[i*4+Y]=0;
                mPts[i*4+X+2]=0;
                mPts[i*4+Y+2]=value;
                value+=delta;
            }
        }

        public MyView(Context context) {
            super(context);
            buildPoints();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint=mPaint;
            canvas.translate(20,20);
            canvas.drawColor(Color.WHITE);
            paint.setColor(Color.RED);
            paint.setStrokeWidth(0);
            canvas.drawLines(mPts, paint);
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(3);
            canvas.drawPoints(mPts, paint);

            paint.setColor(Color.BLACK);
            canvas.drawLines(caiLine,paint);

            paint.setTextSize(30);
            canvas.drawText("chencaisheng",200,200,paint);

            canvas.drawCircle(400,400,200,paint);
            paint.setColor(Color.BLUE);
            canvas.drawCircle(300,500,200,paint);


        }
    }
}

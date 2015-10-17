package otcapp.com.upload;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.Random;

import caisheng.com.search.R;
import otcapp.com.login.AnimActivity;

/**
 * 每个人这辈子都会遇到三个人，
 * 一个是你爱但不爱你的人，
 * 一个是爱你但你不爱的人，
 * 一个是爱你你也爱的人。
 */


//http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/1118/2006.html  toolbar

public class UploadActivity extends AppCompatActivity {
    String url = "http://otcapp.hkbf.com.cn/api/ApiElse/FileUpload";
    Button view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);




        view=(Button)findViewById(R.id.button9);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_layout);
        toolbar.setTitle("i love you");
        setSupportActionBar(toolbar);



        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {



                switch (item.getItemId()){
                    case R.id.action_settings:
                        Toast.makeText(getApplicationContext(),"setting",Toast.LENGTH_SHORT).show();
                        int startDelay = 100;
                        vibrate(startDelay);
                        break;
                    case R.id.search:
                        Toast.makeText(getApplicationContext(),"searvc",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }

            private void vibrate(int startDelay) {
                ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(1500);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    Random random = new Random();

                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        view.setTranslationX((random.nextFloat() - 0.5f) * view.getWidth() * 0.05f);
                        view.setTranslationY((random.nextFloat() - 0.5f) * view.getHeight() * 0.05f);

                    }
                });
                animator.start();

                //view.animate().setDuration(1500).setStartDelay(startDelay).scaleX(0f).scaleY(0f).alpha(0f).start();
            }
        });
    }


    public  void getAllVedio(View v){
        Uri uri=MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection={MediaStore.Video.VideoColumns.DATA};
        Cursor c=getContentResolver().query(uri,projection,null,null,null);
        int vidsCount=0;
        if(c!=null){
            vidsCount=c.getCount();
            while (c.moveToNext()){
                Log.e("vedio",c.getString(0));
            }
            c.close();
        }
    }

    public void camera(View v){


        startActivity(new Intent(this,CameraActivity.class));
    }

    public void playWebVideo(View v){
        startActivity(new Intent(this,PlayWebVideoActivity.class));
    }

    public void anim1(View v){
        startActivity(new Intent(this, AnimActivity.class));
        overridePendingTransition(R.anim.aa, R.anim.bb);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_upload,menu);
        return true;
    }

    public void select(View v) {
       /* Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image*//*");
        startActivityForResult(intent, 100);*/
       // startActivity(new Intent(this, ChoosePicActivity.class));

        ObjectAnimator.ofFloat(v,"rotationX",0.0f,120.0f).setDuration(1000).start();
    }

    public void noti(View v){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.abc);
        builder.setContentTitle("my title");
        builder.setContentText("hey,want to get somelunch?");
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.a)));
        builder.setStyle(new NotificationCompat.InboxStyle().addLine("could you make my best friend").setBigContentTitle("i miss you").setSummaryText("nice to meet you,ping,sometimes we have not to talk,but when the time go by,we will know each other"));

        Notification noti= builder.build();
        NotificationManagerCompat.from(this).notify(2,noti);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 100) {
            String imagePath = getRealPathFromURI(this, data.getData());//获取图片的路径
            try {
                Bitmap b=BitmapFactory.decodeStream(getContentResolver().openInputStream(data.getData()));
               // Bitmap bitmap = BitmapFactory.decodeFile(imagePath);//转为文件
                ((ImageView) findViewById(R.id.imageView3)).setImageBitmap(b);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

         /*   File f1 = new File(getRealPathFromURI(this, data.getData()));
            Log.e("length", bitmap.getByteCount() + "");
            // First decode with inJustDecodeBounds=true to check dimensions
             BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;//压缩图片
           Bitmap bitmap1= BitmapFactory.decodeFile(imagePath, options);

            Log.e("length",bitmap1.getByteCount()+"");*/

        }
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

 /*   public void scale(){
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        options.inSampleSize=2;//二分之一
        BitmapFactory.decodeFile(file,options);
    }*/

}

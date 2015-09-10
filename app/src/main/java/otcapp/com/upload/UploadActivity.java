package otcapp.com.upload;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;

import caisheng.com.search.R;
import single.Singleton;
import single.Test;

/**
 * 每个人这辈子都会遇到三个人，
 * 一个是你爱但不爱你的人，
 * 一个是爱你但你不爱的人，
 * 一个是爱你你也爱的人。
 */

public class UploadActivity extends ActionBarActivity {
    String url = "http://otcapp.hkbf.com.cn/api/ApiElse/FileUpload";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        Toast.makeText(this, Test.getValue3()+ Singleton.getValue3(),Toast.LENGTH_SHORT).show();
    }

    public void select(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, 100);
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

        /*    File f1 = new File(getRealPathFromURI(this, data.getData()));
            Log.e("length",bitmap.getByteCount()+"");
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

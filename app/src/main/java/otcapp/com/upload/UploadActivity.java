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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.io.File;

import caisheng.com.search.R;
import caisheng.com.search.VolleryInstance;
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
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);//转为文件
            ((ImageView) findViewById(R.id.imageView3)).setImageBitmap(bitmap);
            File f1 = new File(getRealPathFromURI(this, data.getData()));
            File f2 = new File(getRealPathFromURI(this, data.getData()));
            File[] f=new File[]{f1,f2};
            MultipartRequest multipartRequest = new MultipartRequest(url, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.e("error", volleyError.toString());
                }
            }, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    Log.e("response", s);
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                }
            }, f);
            VolleryInstance.getInstance(this).addToRequestQueue(multipartRequest);
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

}

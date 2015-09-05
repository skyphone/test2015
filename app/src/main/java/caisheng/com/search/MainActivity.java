package caisheng.com.search;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;


public class MainActivity extends Activity {
    private String jsons;

    class A {
        int a = 9;
    }

    class First<T> {
        ArrayList<A> a = new ArrayList<A>();
    }



    public void noti(){
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.mm);
        // Base notification
        Notification.Builder b = new Notification.Builder(this);
        b.setSmallIcon(R.mipmap.ic_launcher);
        b.setContentTitle("asdfasdfad");
        b.setContentText("asdfasdfasdfasdfasdfasdfaqwerqwerqwerqwerqwerqwerqer");
        b.setTicker("sfd");
        b.setWhen(System.currentTimeMillis());
        // BigPictureStyle
       Notification.BigPictureStyle s = new Notification.BigPictureStyle();
        s.setBigContentTitle("asdfasdf");
        s.setSummaryText("asdfadfa\nasdfasdfasd\nasdfasd\nasdfasdf\n");
        s.bigPicture(bitmap);
        //s.bigLargeIcon(bitmap);
        b.setStyle(s);
        Notification n = b.build();
        NotificationManager mnotiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mnotiManager.notify("",0, n);
    }
    ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
        @Override
        public void onFinalImageSet(
                String id,
                @Nullable ImageInfo imageInfo,
                @Nullable Animatable anim) {
            if (imageInfo == null) {
                return;
            }
            QualityInfo qualityInfo = imageInfo.getQualityInfo();
            FLog.d("Final image received! " +
                            "Size %d x %d",
                    "Quality level %d, good enough: %s, full quality: %s",
                    imageInfo.getWidth(),
                    imageInfo.getHeight(),
                    qualityInfo.getQuality(),
                    qualityInfo.isOfGoodEnoughQuality(),
                    qualityInfo.isOfFullQuality());
        }

        @Override
        public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
            Log.d("abc","Intermediate image received");
        }

        @Override
        public void onFailure(String id, Throwable throwable) {
           // Log.d("abc", "Error loading %s", id)
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //noti();
       // getDex();
        final ArrayList<String> list=new ArrayList<String>();
        try {
            String j="{\"desc\":[{\"type\":\"img\",\"value\":\"M00/11/5C/CgkeMlVZnxTS_LrwAAEBK7dQT54977.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZponBBe1CAAEX1ZbSng0353.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/5C/CgkeMlVZnxTRKx_EAADFZi1v43U442.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZpomSeRjeAAExsSyJ-co089.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/5C/CgkeMlVZnxSzUM5fAAExRRgdSE0470.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZpomSBZEhAAELElVWZkM211.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZponwnkx9AAQa30EVCa0793.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZpomBqQetAASZsl5Qj4E390.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZporhiztIAATw9UGmbS4187.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZpoqQhYVlAAT4zUNQE48841.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZpovDlu0YAAZGx89rYE4146.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0B/CgkeM1VZnxXROLVwAAD7QehT1Yg758.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZpoqDnskWAADyaofGF8k225.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZpovjs3D5AAQPrQkesj4744.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZpovwaSVBAANTNcQMrd4005.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZpouz9nOdAANItZrQ0gc106.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0E/CgkeM1VZpouDrpe8AARoK-NqT6g800.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/60/CgkeMlVZpoySTneDAAVQK0_Majw273.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/11/5C/CgkeMlVZnxWy9ScxAAIweogh0nk985.jpg\",\"size\":\"0\"},{\"type\":\"img\",\"value\":\"M00/00/0B/CgkeM1VZnxTgt0tGAAMSdJT3y6M647.jpg\",\"size\":\"0\"}]}";

            JSONArray jsonArray=new JSONObject(j).optJSONArray("desc");
            String s="";
            for(int i=0;i<jsonArray.length();i++){
                s+="\"http://img1.aolaigo.com/group1/"+jsonArray.getJSONObject(i).optString("value")+"\",";

            }
            Log.v("abc",s);
        } catch (JSONException e) {
            e.printStackTrace();
        }

       /* ListView listView=(ListView)findViewById(R.id.lll);
        BaseAdapter adapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView==null){
                    convertView=getLayoutInflater().inflate(R.layout.it,parent,false);
                }

                SimpleDraweeView v=(SimpleDraweeView)convertView.findViewById(R.id.my_image_view);
               *//* DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setControllerListener(controllerListener).setUri(Uri.parse(list.get(position)))
                        .build();
                v.setController(controller);*//*
                v.setImageURI(Uri.parse(list.get(position)));
                return convertView;
            }
        };

        listView.setAdapter(adapter);*/
    /*    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lin);
        try {
            test(linearLayout);
        } catch (Exception e) {
            e.printStackTrace();
        }*/



      /*  try {
            Context context= createPackageContext("com.csc.aolaigo",CONTEXT_INCLUDE_CODE);
           context.

        } catch (Exception e) {
            e.printStackTrace();
        }*/

      /*  String abc = Base64.encode("chencai".getBytes());
        new AlertDialog.Builder(this).setMessage(abc)
                .setTitle(new String(android.util.Base64.decode(abc.getBytes(), android.util.Base64.DEFAULT)))
                .show();

        ArrayList<A> a = new ArrayList<A>();
        ArrayList<A> b = new ArrayList<A>();
        A qq = new A();

        for (int i = 0; i < 5; i++) {
            a.add(qq);

        }
        b.addAll(a);

        a.clear();
        A ww=new A();
        ww.a=3333;
        for (int i = 0; i < 5; i++) {
            a.add(ww);
        }
        Log.v("abc", b.get(2).a + "");*/

       /* MyViewGroup layout=(MyViewGroup)findViewById(R.id.view2);
        Button bt;
        for(int i=0;i<33;i++){
             bt=new Button(this);
            bt.setText("asdfas"+i);
            //bt.setPadding(0,0,0,0);
            layout.addView(bt);
        }*/

    /*    try {
            InputStream inputStream=getAssets().open("1.txt");
            InputStreamReader reader=new InputStreamReader(inputStream);
            BufferedReader reader1=new BufferedReader(reader);
            jsons=reader1.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray array=null;
        try {
             array=new JSONArray(jsons);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<Items> list1=new ArrayList<Items>();
        Gson gson=new Gson();
        for(int i=0;i<array.length();i++){
            try {
                JSONObject js=array.getJSONObject(i);
              //  Log.v("abc",js.toString());
                list1.add(gson.fromJson(js.toString(),Items.class));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
       // ArrayList<String> mylist=new ArrayList<String>();
        Set<String> set=new HashSet<String>();
            for(Items i:list1){
                set.add(i.aname);
            }
            Log.v("abc","size"+set.size());*/


    }

    private void getDex() {
        try {
            String libPath= Environment.getExternalStorageDirectory()+"/cai.jar";
            File temDir=getDir("dex",0);
            Toast.makeText(this, temDir.getAbsolutePath().toString(), Toast.LENGTH_LONG).show();
            DexClassLoader classLoader=new DexClassLoader(libPath,
                   temDir.getAbsolutePath().toString(),null,this.getClass().getClassLoader());
            Class<Object> classToLoad=(Class<Object>)classLoader.loadClass("com.cai.Test");
            Object myInstance=classToLoad.newInstance();
            Method doSomething=classToLoad.getMethod("out");
            doSomething.invoke(myInstance);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /*    public ArrayList<Items> getLast(ArrayList<Items> list,int pos){
               for(int i=1;i<list.size();i++){
                   if(list.get(pos).aname.equals(list.get(i).aname)){
                       list.remove(i);
                   }
               }

        }*/
    private void copyInputStreamToFile(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testUseAndroidString() {
        getLayoutInflater();
        try {
            //  Context context = createPackageContext("com.csc.aolaigo",CONTEXT_INCLUDE_CODE);
            Resources res = null;
            //I want to use the clear_activities string in Package com.android.settings
            res = getPackageManager().getResourcesForApplication("com.csc.aolaigo");
            int resourceId = res.getIdentifier("com.csc.aolaigo:string/app_name", null, null);
            if (0 != resourceId) {
                CharSequence s = getPackageManager().getText("com.csc.aolaigo", resourceId, null);
                Log.i("abc", "resource=" + s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void test(LinearLayout v) throws Exception {
        String packageName = "com.csc.aolaigo";
        String className = "com.csc.aolaigo.view.AutoClearEditText";

        String apkName = getPackageManager().getApplicationInfo(
                packageName, 0).sourceDir;
        PathClassLoader myClassLoader = new dalvik.system.PathClassLoader(
                apkName, ClassLoader.getSystemClassLoader());
        Class<?> handler = Class.forName(className, true, myClassLoader);
        Constructor[] m = handler.getConstructors();
        for (int i = 0; i < m.length; i++) {
            if (m[i].getName().contains("Auto")) {
                View animation = (View) m[i].newInstance(new Object[]{this});
                v.addView(animation);
            }
        }
    }

}

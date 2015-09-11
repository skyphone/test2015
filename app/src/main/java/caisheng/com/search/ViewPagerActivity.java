package caisheng.com.search;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import caisheng.com.search.dummy.SimpleCrypto;

public class ViewPagerActivity extends FragmentActivity implements FragCallback{
    ViewPager viewPager;
    FragmentPagerAdapter adapter;
    public Fragment[] fragments=new Fragment[3];
    Button tb;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        String fileName="chen";
        File f=new File(getFilesDir(),fileName);
        try {
            FileOutputStream outputStream=openFileOutput(fileName,MODE_PRIVATE);
            outputStream.write("asldjfasdf;a;skdjflkajs".getBytes());
            outputStream.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        }.start();

        viewPager=(ViewPager)findViewById(R.id.viewpage);

        adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {

                if(fragments[0]==null){
                    fragments[0]=new FirstFragment();
                }
                if(fragments[1]==null){
                    fragments[1]=new SecondFragment();
                }
                if(fragments[2]==null){
                    fragments[2]=new FirstFragment();
                }

                return fragments[i] ;
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
        viewPager.setAdapter(adapter);


               try {
                   String key="ping";
                   String password="b123456";
                   String encrypted= SimpleCrypto.encrypt(key,password);
                   Log.e("encrypt",encrypted);
                   String password1= SimpleCrypto.decrypt(key,encrypted);
                   Log.e("pass",password1);

               } catch (Exception e) {
                   e.printStackTrace();
               }


        Intent intent=new Intent(this,BinddService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {


                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            for (int i = 0; i <20 ; i++) {
                                try {
                                    Thread.sleep(4000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Log.e("service",i+"asdfad");
                            }
                        }
                    }.start();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        },BIND_AUTO_CREATE);

    }

    public void cal(){
        int a=1;
        boolean isReal=true;

        String all="";
        while (true){
            all="";
            a++;
            for (int i = 1; i <=20 ; i++) {
                if(a%i==0){
                    all+="1";
                }else{
                    all+="0";
                }
            }
            if(!all.contains("0")){
                break;
            }
        }
        Log.e("value",a+"");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bbb,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action0:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void myItemClick(int pos) {
        Toast.makeText(getApplicationContext(),pos+"",Toast.LENGTH_SHORT).show();
    }
}

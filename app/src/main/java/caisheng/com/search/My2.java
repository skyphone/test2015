package caisheng.com.search;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.stericson.RootTools.RootTools;
import com.stericson.RootTools.execution.Command;
import com.stericson.RootTools.execution.CommandCapture;
import com.stericson.RootTools.execution.Shell;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by abc on 2015/6/27.
 * 设置字体大小
 * 读取系统WiFi文件。
 */
public class My2 extends Activity {
    private Configuration configuration;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my2);
        configuration=new Configuration();

        final File f=new File("/data/misc/wifi/wpa_supplicant.conf");
     /*   Toast.makeText(this,f.canRead()+"",Toast.LENGTH_SHORT).show();
         tv=(TextView)findViewById(R.id.tv);
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String mOriginal = "";
                ArrayList<String> arrayList = runAndWait1("cat " + f.getPath(), true);
                for (String x : arrayList) {
                    if (mOriginal.equals("")) mOriginal = x;
                    else mOriginal = mOriginal + "\n" + x;

                }
                return mOriginal;
            }

            @Override
            protected void onPostExecute(String s) {
                tv.setText(s);
                super.onPostExecute(s);
            }
        }.execute();*/

       // grantPermission();
       // setFontSize();
        getPackageSize();


    }

    public void getPackageSize(){
        PackageManager mPackageManager=getPackageManager();
        try {
            Method getPackageSizeInfo = mPackageManager.getClass().getMethod(
                    "getPackageSizeInfo", String.class, IPackageStatsObserver.class);

            getPackageSizeInfo.invoke(mPackageManager, "com.csc.aolaigo", new IPackageStatsObserver.Stub() {
                @Override
                public void onGetStatsCompleted(final PackageStats pStats, boolean succeeded)
                        throws RemoteException {
                    long size;
                    if (succeeded)
                         size = pStats.codeSize + pStats.cacheSize + pStats.dataSize
                                + pStats.externalCodeSize + pStats.externalCacheSize + pStats.externalDataSize
                                + pStats.externalMediaSize + pStats.externalObbSize;
                    else
                        size = -1L;

                    Log.v("size", android.text.format.Formatter.formatFileSize(My2.this,size));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void open(View v){

     Intent intent=   new Intent();
        intent.setComponent(new ComponentName("com.csc.aolaigo","com.csc.aolaigo.ui.personal.RecommandProductActivity"));
        startActivity(intent);
    }
    public static ArrayList<String> runAndWait1(String cmd, final boolean root) {
        final ArrayList<String> output = new ArrayList<String>();
        Command cc = new Command(1, cmd) {
            @Override
            public void commandOutput(int i, String s) {
                output.add(s);
//        System.out.println("output "+root+s);
            }

            @Override
            public void commandTerminated(int i, String s) {

                System.out.println("error" + root + s);

            }

            @Override
            public void commandCompleted(int i, int i2) {

            }
        };
        try {
            RootTools.getShell(root).add(cc);
        } catch (Exception e) {
            //       Logger.errorST("Exception when trying to run shell command", e);
            e.printStackTrace();
            return null;
        }

        if (!waitForCommand(cc)) {
            return null;
        }

        return output;
    }

    private static boolean waitForCommand(Command cmd) {
        while (!cmd.isFinished()) {
            synchronized (cmd) {
                try {
                    if (!cmd.isFinished()) {
                        cmd.wait(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (!cmd.isExecuting() && !cmd.isFinished()) {
                //         Logger.errorST("Error: Command is not executing and is not finished!");
                return false;
            }
        }

        //Logger.debug("Command Finished!");
        return true;
    }
    private void updateConfiguration(){
        try{
            Class<?> activityManagerNative=Class.forName("android.app.ActivityManagerNative");
            Object am=activityManagerNative.getMethod("getDefault").invoke(activityManagerNative);
            Object config=am.getClass().getMethod("getConfiguration").invoke(am);
            Configuration configs=(Configuration)config;
            configuration.updateFrom(configs);
        }catch (Exception e){

        }
    }

    private void grantPermission(){
        try {
            String cmds="pm grant "+getPackageName()+" android.permission.CHANGE_CONFIGURATION";
            CommandCapture cmd=new CommandCapture(0,false,cmds);
            RootTools.getShell(true).add(cmd);
            while(!cmd.isFinished()){
                Shell.startRootShell().getCommandQueuePositionString(cmd);
                synchronized(cmd){
                    if(!cmd.isFinished()){
                        cmd.wait(2000);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //设置字体大小
    public void setFontSize(){
        try {
            Class<?> activityManagerNative=Class.forName("android.app.ActivityManagerNative");
            Object am=activityManagerNative.getMethod("getDefault").invoke(activityManagerNative);
            Method method=am.getClass().getMethod("updatePersistentConfiguration",Configuration.class);
            method.invoke(am,configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

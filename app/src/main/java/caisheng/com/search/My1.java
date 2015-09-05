package caisheng.com.search;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.stericson.RootTools.RootTools;
import com.stericson.RootTools.execution.Command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by abc on 2015/6/8.
 * 使用root读取系统文件。
 */
public class My1 extends Activity {
    String json="";
    private static final String UNIX_ESCAPE_EXPRESSION = "(\\(|\\)|\\[|\\]|\\s|\'|\"|`|\\{|\\}|&|\\\\|\\?)";
    public static String getCommandLineString(String input) {
        return input.replaceAll(UNIX_ESCAPE_EXPRESSION, "\\\\$1");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aaa);
        //asdf();


        //myfor();
        Log.e("abc", fab(5) + "");
        ArrayList<ArrayList<Integer>> abc=permute(new int[]{1,2,3,4,5});
        Log.v("abc", "asd");
        Car<String> stringCar=new Car<String>();
        stringCar.add("abc");
         stringCar.getT();

        Car<ArrayList<String>> listCar=new Car<ArrayList<String>>();
        listCar.add(new ArrayList<String>());

        writeObj();


       final  File f=new File("/data/misc/wifi/wpa_supplicant.conf");

        new Thread(){
            @Override
            public void run() {
                super.run();
                ArrayList<String> list=new ArrayList<String>();
              //  String cpath = getCommandLineString(f.getPath());
                String s="cat " + f.getPath();
                list = runAndWait1(s, true);
                for (int i = 0; i < list.size(); i++) {
                    Log.e("content",list.get(i));
                }


            }
        }.start();


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


    private void readObj() {
        try {
            FileInputStream inputStream=new FileInputStream(getCacheDir()+"/student");
            ObjectInputStream objectInputStream=new ObjectInputStream(inputStream);
            Student student=(Student)objectInputStream.readObject();
            objectInputStream.close();
            inputStream.close();
            Toast.makeText(this,student.list.get(0).toString(),Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeObj() {
        Student student=new Student();
        student.age="asafd";
        student.name="cai";
        student.when="1992-4-4";
        student.list.add("abcd");
        try {
            FileOutputStream outputStream=new FileOutputStream(getCacheDir()+"/student");
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(student);
            objectOutputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        readObj();
    }

    class Car<T>{
        public T t;
        public void add(T t){
            this.t=t;
        }
        public T getT(){
            return t;
        }
    }



    public void myfor(){
        int aa=0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                for(int k=0;k<5;k++){
                    for(int n=0;n<5;n++){
                        for(int m=0;m<5;m++){
                            if(!(i==j||i==k||i==n||i==m||j==k||j==n||j==m||k==n||k==m||n==m)){
                                Log.v("abc"+(aa++),i+"-"+j+"-"+k+"-"+n+"-"+m);
                            }

                        }
                    }
                }
            }
        }
    }

    public static int fab(int index){
        if(index==1){
            return index;
        }else {

            return fab(index-1)*index;
        }
    }

    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        //start from an empty list
        result.add(new ArrayList<Integer>());

        for (int i = 0; i < num.length; i++) {
            //list of list in current iteration of the array num
            ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();

            for (ArrayList<Integer> l : result) {
                // # of locations to insert is largest index + 1
                for (int j = 0; j < l.size()+1; j++) {
                    // + add num[i] to different locations
                    l.add(j, num[i]);

                    ArrayList<Integer> temp = new ArrayList<Integer>(l);
                    current.add(temp);

                    //System.out.println(temp);

                    // - remove num[i] add
                    l.remove(j);
                }
            }

            result = new ArrayList<ArrayList<Integer>>(current);
        }

        return result;
    }

}

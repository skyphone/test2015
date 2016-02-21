package design.single;

/**
 * Created by abc on 2015/7/3.
 */
public class Singleton {
    private String value="";

    public Singleton(){

    }

    private static Singleton singleton;

    public static Singleton getInstance(){
        if(singleton==null){
            singleton=new Singleton();
        }
        return singleton;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private String getValue1(){
        return value;
    }

    final public String getValue2(){
        return value;
    }

    public static  String getValue3(){
        return "sdfas";
    }




}

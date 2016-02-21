package design.single;

/**
 * Created by abc on 2015/7/3.
 */
public class Test extends Singleton {

    private Test() {
    }


    public String getValue() {
        return "";
    }
    private String getValue1(){
        return "";
    }

    public static  String getValue3(){
        return "sdfas";
    }


}

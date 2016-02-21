package design.IterorAndCollection;

import java.util.ArrayList;

/**
 * Created by abc on 2015/7/7.
 */
public class PanCakeHourseMenu {
    ArrayList<MenuItem> list;
    public PanCakeHourseMenu(){
        list=new ArrayList<MenuItem>();
        addItem("cai","11111");
        addItem("cai11","22222");
        addItem("cai222","33333");
    }

    public void addItem(String name,String des){
        MenuItem item=new MenuItem(name,des);
        list.add(item);
    }

    public ArrayList<MenuItem> getMenuItems(){
        return list;
    }
}

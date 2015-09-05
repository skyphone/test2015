package caisheng.com.search.dummy;

import java.util.ArrayList;

/**
 * Created by abc on 2015/8/13.
 */
public class zengyi {
    String message,status;
    ArrayList<Data> data=new ArrayList<Data>();
}

class Data{
    String number,integral,status,time;
    ArrayList<Goods> goods=new ArrayList<Goods>();
}

class Goods{
    String id,name,number,url,pic;
}

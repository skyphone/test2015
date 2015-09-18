package otcapp.com.upload;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/9/18.
 */
public class Folder {
    String name,path;
    Image image;
    ArrayList<Image> list;


    @Override
    public boolean equals(Object o) {
        try {
            Folder other = (Folder) o;
            return this.path.equalsIgnoreCase(other.path);
        }catch (ClassCastException e){
            e.printStackTrace();
        }
        return super.equals(o);
    }
}

package caisheng.com.search;

import java.util.ArrayList;

/**
 * Created by abc on 2015/7/23.
 */
public class Test {

    public static ArrayList<String> isNumeric(String str) {
       // String str = "普通商品满100.00元马上马上324234减少425减少1元满件";
        ArrayList<String> position = new ArrayList<String>();
        boolean isFirst = true;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i)) || str.substring(i, i + 1).equals(".")) {
                if (isFirst) {
                    position.add(i + "");
                    isFirst = false;
                }
            } else {
                if (!isFirst) {
                    position.add(i + "");
                    isFirst = true;
                }
            }
        }
        return position;
     /*   for (int j = 0; j < position.size(); j++) {
            Log.e("first", position.get(j));
        }*/
    }
}

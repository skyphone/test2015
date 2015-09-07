package observer;

import java.util.ArrayList;

/**
 * Created by abc on 2015/9/6.
 */
public class WeatherData implements Subject {
    ArrayList<Update> list=new ArrayList<>();
    @Override
    public void add(Update update) {
        list.add(update);
    }

    @Override
    public void remove(Update update) {
        list.remove(update);
    }

    @Override
    public void notiAll() {
            for(Update update:list){
                update.update("test");
            }
    }
}

package caisheng.com.search.eventbus;

/**
 * Created by lenovo on 2016/3/16.
 */
public class FirstEvent {
    private String mMsg;
    public FirstEvent(String msg) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
}

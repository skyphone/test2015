package caisheng.com.search.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import caisheng.com.search.R;

public class EventBus2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus2);

    }

    public void sendMess(View v){
        EventBus.getDefault().post(new FirstEvent("miss you"));
    }
}

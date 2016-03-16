package caisheng.com.search.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import caisheng.com.search.R;
//http://blog.csdn.net/harvic880925/article/details/40660137
public class EventBusActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        EventBus.getDefault().register(this);
    }


    public void goTo(View view){
        Intent intent = new Intent(getApplicationContext(),
                EventBus2Activity.class);
        startActivity(intent);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    @Subscribe
    public void onEventMainThread(FirstEvent event) {

        String msg = "onEventMainThread收到了消息：" + event.getMsg();
        Log.d("harvic", msg);
        ((TextView) findViewById(R.id.tv)).setText(msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}

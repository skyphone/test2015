package caisheng.com.search.com.map;

import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import caisheng.com.search.R;

/**
 * 模拟gps.要打开模拟gps设置
 */
public class MockGpsActivity extends AppCompatActivity {
    GPSTracker gpsTracker;
    LocationManager manager;
    TextView text;
    String info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_ping_one);
        text=(TextView)findViewById(R.id.text);
        manager=(LocationManager)getSystemService(LOCATION_SERVICE);

        manager.addTestProvider(LocationManager.GPS_PROVIDER,

                "requiresNetwork" == "", "requiresSatellite" == "", "requiresCell" == "", "hasMonetaryCost" == "",

                "supportsAltitude" == "", "supportsSpeed" == "",

                "supportsBearing" == "", android.location.Criteria.POWER_LOW,

                android.location.Criteria.ACCURACY_FINE);


    }


    public void gps(View v){
        gpsTracker=new GPSTracker(this);
        if(gpsTracker.canGetLocation()){
            double lat=gpsTracker.getLatitude();
            double lon=gpsTracker.getLongitude();
            //Toast.makeText(PingOneActivity.this, "lat="+lat+"  lon="+lon, Toast.LENGTH_SHORT).show();
            info+="lat="+lat+"  lon="+lon+"\n";
            text.setText(info);
        }else{
            gpsTracker.showSettingsAlert();
        }
    }


    public void mock(View v){

       /* Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        String provider = manager.getBestProvider( criteria, true );

        if ( provider == null ) {
            Toast.makeText(PingOneActivity.this, "No location provider found!", Toast.LENGTH_SHORT).show();
            return;
        }*/
        Random r=new Random(4);
        Location loc = new Location(LocationManager.GPS_PROVIDER);
        //30.6928918882,111.3065109718
        loc.setLatitude(30.6928918882);
        loc.setLongitude(111.3065109718);
        loc.setAccuracy(16F);
        loc.setAltitude(0D);
        loc.setTime(System.currentTimeMillis());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            loc.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        }
        loc.setBearing(0F);
        manager.setTestProviderLocation(LocationManager.GPS_PROVIDER, loc);
    }

}

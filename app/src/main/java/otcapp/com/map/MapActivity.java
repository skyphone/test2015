package otcapp.com.map;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import java.util.List;
import java.util.Random;

import caisheng.com.search.R;

public class MapActivity extends ActionBarActivity {

    MapView map;
    CheckBox checkMap;
    Button btMyPosition;
    public LocationClient mLocationClient = null;
    PoiSearch poi; //地图检索
    public BDLocationListener myListener = new MyLocationListener();
    BDLocation location1;
    boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        map = (MapView) findViewById(R.id.bmapView);
        checkMap = (CheckBox) findViewById(R.id.checkBox2);
        //卫星地图
        checkMap.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    map.getMap().setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                } else {
                    map.getMap().setMapType(BaiduMap.MAP_TYPE_NORMAL);
                }
            }
        });


        //定位
        btMyPosition = (Button) findViewById(R.id.button11);
        btMyPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(new LatLng(location1.getLatitude(), location1.getLongitude()));
                map.getMap().animateMapStatus(u);
                //构建Marker图标
                BitmapDescriptor bitmap = BitmapDescriptorFactory .fromResource(R.drawable.abc);
                Random random=new Random();
                for (int i = 0; i < 4; i++) {
                    //构建MarkerOption，用于在地图上添加Marker
                    OverlayOptions option = new MarkerOptions()
                            .position(new LatLng(location1.getLatitude() + random.nextDouble() * 0.1, location1.getLongitude() - random.nextDouble() * 0.1))
                            .icon(bitmap);
                    //在地图上添加Marker，并显示
                    map.getMap().addOverlay(option);
                }

            }
        });
        initLocation();

        map.getMap().setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                //创建InfoWindow展示的view
                View v=getLayoutInflater().inflate(R.layout.cai_map_view,null);
                //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                LatLng lat=new LatLng(marker.getPosition().latitude,marker.getPosition().longitude);
                InfoWindow mInfoWindow = new InfoWindow(v,lat, -47);
                //显示InfoWindow
                map.getMap().showInfoWindow(mInfoWindow);
                return false;
            }
        });

        map.getMap().setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
               if(latLng!=null){
                   Toast.makeText(getApplicationContext(),latLng.toString(),Toast.LENGTH_SHORT).show();

               }
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                    if(mapPoi!=null&&mapPoi.getName()!=null){
                        Toast.makeText(getApplicationContext(),mapPoi.getName(),Toast.LENGTH_SHORT).show();
                    }
                return false;
            }
        });

        poi=PoiSearch.newInstance();
        poi.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {


            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }
        });
        poi.searchInCity(new PoiCitySearchOption().city("深圳").keyword("公园").pageNum(4));

    }

    private void initLocation() {
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 15 * 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        //option.setOpenGps(false);//可选，默认false,设置是否使用gps
        // option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        // option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        //option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        // option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        // option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        //  option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            location1 = location;
            if (isFirst) {
                isFirst = false;
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(new LatLng(location1.getLatitude(), location1.getLongitude()));
                map.getMap().animateMapStatus(u);
                BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                        .fromResource(R.drawable.i);
              /*  MyLocationConfiguration config = new MyLocationConfiguration(map.getMap().getLocationConfigeration().locationMode,true, mCurrentMarker);
                map.getMap().setMyLocationConfigeration(config);*/
            }
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        map.onDestroy();
        poi.destroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }
}

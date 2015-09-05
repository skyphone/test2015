package caisheng.com.search;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by abc on 2015/5/5.
 */
public class MyFragment extends Fragment {
    ArrayList<String> list=new ArrayList<String>() ;
    BaseAdapter adapter;
    ListView li;
    String[] abc="http://img2.aolaigo.com/group1/M00/0B/08/CgkeMlTTFrrjzspSAAAc9iyoZIU223.jpg,http://img2.aolaigo.com/group1/M00/0B/08/CgkeMlTTFrrjzspSAAAc9iyoZIU223.jpg,http://img2.aolaigo.com/group1/M00/0B/08/CgkeMlTTFsOyn8-eAAATfmaIzEE064.jpg,http://img2.aolaigo.com/group1/M00/0B/08/CgkeMlTTFtDB2jjmAAAkRPlXCkI144.jpg,http://img2.aolaigo.com/group1/M00/0B/08/CgkeMlTTFtqQAudkAAAiduGB1lc367.jpg,http://img2.aolaigo.com/group1/M00/0B/08/CgkeMlTTFxOA-1dbAAAk9FTH5FE743.jpg,http://img2.aolaigo.com/group1/M00/0B/08/CgkeMlTTF2qhuJcOAAAdZSzQpD0967.jpg,http://img2.aolaigo.com/group1/M00/0B/08/CgkeMlTTF3ui_9LuAAASTtL0zWM371.jpg,http://img2.aolaigo.com/group1/M00/0B/08/CgkeMlTTF3ui_9LuAAASTtL0zWM371.jpg,http://img2.aolaigo.com/group1/M00/0B/08/CgkeMlTTF4TzwjFnAAAQQmdJ9LI580.jpg,http://img2.aolaigo.com/group1/M00/0B/08/CgkeMlTTF4zBXgaZAAAUkW96Cg0395.jpg,http://img2.aolaigo.com/group1/M00/0B/08/CgkeMlTTF5eTZ5QlAAAc627Sfrg892.jpg".split(",");
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.activity_main,container,false);
        // li=(ListView)v.findViewById(R.id.list11);

        //adapter=new ArrayAdapter<String>(getActivity(),R.layout.item,abc);
        adapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return abc.length;
            }

            @Override
            public Object getItem(int position) {
                return abc[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = getView11(position, parent);

                return convertView;
            }

            private View getView11(int position, ViewGroup parent) {
                View convertView;
                convertView=getActivity().getLayoutInflater().inflate(R.layout.item,parent,false);
                SimpleDraweeView vie = (SimpleDraweeView) convertView.findViewById(R.id.view);
                vie.setImageURI(Uri.parse(abc[position]));
                return convertView;
            }
        };
        Log.v("abc",abc.length+"");
        li.setAdapter(adapter);
       setListViewHeightBasedOnChildren(li,true);
        return v;
    }

    public void add(){
       /* adapter.setNotifyOnChange(false);
        adapter.addAll(list);
        adapter.setNotifyOnChange(true);*/
        adapter.notifyDataSetChanged();
        setListViewHeightBasedOnChildren(li,true);
    }

    /**
     * 设置listView高度
     * */

    public static void setListViewHeightBasedOnChildren(ListView listView,
                                                        Boolean flag) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int len = 1;
        if (flag)
            len = listAdapter.getCount();
        for (int i = 0; i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += (listItem.getMeasuredHeight() - 2); // 统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度

        listView.setLayoutParams(params);
    }

}

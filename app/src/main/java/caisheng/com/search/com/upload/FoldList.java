package caisheng.com.search.com.upload;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import caisheng.com.search.R;

/**
 * Created by Administrator on 2015/9/18.
 */
public class FoldList extends BaseAdapter {

    ArrayList<Folder> list=new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    public  FoldList(Context c){
        this.context=c;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<Folder> fold){
        list.clear();
        list=fold;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=inflater.inflate(R.layout.my_list_pic,null);
        }
        TextView name=(TextView)convertView.findViewById(R.id.textView13);
        name.setText(list.get(position).name+"--"+list.get(position).list.size());

        return convertView;
    }
}

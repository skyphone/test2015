package otcapp.com.upload;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;

import caisheng.com.search.R;

/**
 * Created by Administrator on 2015/9/18.
 */
public class ImageAdapter extends BaseAdapter {
    ArrayList<Image> imageList = new ArrayList<Image>();
    LayoutInflater inflate;
    Context context;

    public ImageAdapter(Context context) {
        inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context=context;
    }


    public void setData(ArrayList<Image> list){
        imageList.clear();
        if(list!=null&&list.size()>0){
            imageList=list;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder hold;

        if (convertView == null) {
            hold = new ViewHolder();
            convertView = inflate.inflate(R.layout.image_select, null);
            hold.imageView = (com.facebook.drawee.view.SimpleDraweeView) convertView.findViewById(R.id.imageView4);
            hold.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox3);
            convertView.setTag(hold);
        } else {
            hold = (ViewHolder) convertView.getTag();
        }
        //MyImageLoader.getInstance(context).displayImage("file://"+imageList.get(position).path,hold.imageView);
        hold.imageView.setImageURI(Uri.parse("file://"+imageList.get(position).path));
        return convertView;
    }

    class ViewHolder {
        com.facebook.drawee.view.SimpleDraweeView imageView;
        CheckBox checkBox;
    }


}

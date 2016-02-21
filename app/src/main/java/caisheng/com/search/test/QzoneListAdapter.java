package caisheng.com.search.test;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import caisheng.com.search.R;

/**
 * Created by Administrator on 2016/2/18.
 */
public class QzoneListAdapter extends BaseAdapter {

    List<QzoneBean> list;
    Context context;

    public QzoneListAdapter(List<QzoneBean> list,Context context){
            this.list=list;
        this.context=context;
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
        ViewHold hold;
        if(convertView==null){

            convertView= LayoutInflater.from(context).inflate(R.layout.qzone_list,null);
            hold=new ViewHold();
            hold.content=(TextView)convertView.findViewById(R.id.txt_content);
            hold.img1=(SimpleDraweeView)convertView.findViewById(R.id.img1);
            hold.img2=(SimpleDraweeView)convertView.findViewById(R.id.img2);
            convertView.setTag(hold);
        }else{
            hold=(ViewHold)convertView.getTag();
        }


        QzoneBean bean=list.get(position);
        String[] urls=bean.images.split(",");
        hold.content.setText(bean.content);
        if(urls!=null&&urls.length>0)
        hold.img1.setImageURI(Uri.parse(urls[0]));
        if(urls!=null&&urls.length>1)
        hold.img2.setImageURI(Uri.parse(urls[1]));

        return convertView;
    }

    static class ViewHold{
        TextView content;
        SimpleDraweeView img1;
        SimpleDraweeView img2;
    }
}

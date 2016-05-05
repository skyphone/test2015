package caisheng.com.search.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import caisheng.com.search.R;

public class RecycleListViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<String> list;
    private ArrayList<MyBean> list2;
    private SecondView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_recycle);



        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
//        setSimple();

        Random random = new Random();
        adapter = new SecondView();
        list2 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            MyBean b = new MyBean();
            b.name = "hahah" + i;
            b.index = random.nextInt(10);
            Log.e("ss", b.index + "");
            list2.add(b);
        }
        recyclerView.setAdapter(adapter);


    }




    public void add(View v) {
        MyBean b = new MyBean();
        b.name = "qqqqqqqq" + 10;
        b.index = new Random().nextInt(10);
        list2.add(b);
//        adapter.notifyDataSetChanged();
//        adapter.notifyItemChanged(0);
        adapter.notifyItemInserted(0);
    }

    private void setSimple() {
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("abc" + i);
        }
        MyAdapter adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }



    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHold> {

        @Override
        public MyViewHold onCreateViewHolder(ViewGroup parent, int viewType) {


            return new MyViewHold(getLayoutInflater().inflate(R.layout.recycle_view_item, parent, false));
        }

        @Override
        public void onBindViewHolder(MyViewHold holder, int position) {

            holder.textView1.setText(list.get(position));
            holder.textView2.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHold extends RecyclerView.ViewHolder {
            public TextView textView1;
            public TextView textView2;

            public MyViewHold(View itemView) {
                super(itemView);
                textView1 = (TextView) itemView.findViewById(R.id.text1);
                textView2 = (TextView) itemView.findViewById(R.id.text2);
            }
        }
    }
    /**
     * 两种ui
     */
    class SecondView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 1) {
                return new ImageViewHold(getLayoutInflater().inflate(R.layout.recycle_loading, parent, false));
            } else {
                return new TextViewHold(getLayoutInflater().inflate(R.layout.item, parent, false));
            }

        }

        @Override
        public int getItemViewType(int position) {
            if (list2.get(position).index % 2 == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            if (holder instanceof TextViewHold) {
                ((TextViewHold) holder).textView1.setText(list2.get(position).name);
            } else if (holder instanceof ImageViewHold) {
                ((ImageViewHold) holder).imageView.setImageResource(R.drawable.i);
            }
        }

        @Override
        public int getItemCount() {
            return list2.size();
        }

        class TextViewHold extends RecyclerView.ViewHolder {
            public TextView textView1;
            public TextView textView2;

            public TextViewHold(View itemView) {
                super(itemView);
                textView1 = (TextView) itemView.findViewById(R.id.text1);
                textView2 = (TextView) itemView.findViewById(R.id.text2);
            }
        }

        class ImageViewHold extends RecyclerView.ViewHolder {
            public ImageView imageView;

            public ImageViewHold(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.img);
            }
        }
    }

    class MyBean {
        public String name;
        public int index;
    }

}

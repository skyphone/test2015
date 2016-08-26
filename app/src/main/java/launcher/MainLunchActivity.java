package launcher;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import caisheng.com.search.R;


public class MainLunchActivity extends AppCompatActivity {


    private RecyclerView reView;

    private ArrayList<Bean> dataList = new ArrayList();
    private ArrayList<Bean> dataList2 = new ArrayList();
    private EditText edtName;
    private ImageView imgClose;
    private IconAdapter adapter;

    class Bean {
        public Drawable imgUrl;//图标
        public String appName;//应用名字
        public String packageName;//包名
        public String name;//activity名字
        public String appNameLower;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main_lunch);

        edtName = (EditText) findViewById(R.id.edt_name);
        reView = (RecyclerView) findViewById(R.id.grid);
        imgClose = (ImageView) findViewById(R.id.img_close);
        widListener();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        reView.setLayoutManager(gridLayoutManager);

        adapter = new IconAdapter();
        reView.setAdapter(adapter);

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> infoList = getPackageManager().queryIntentActivities(intent, 0);

//        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < infoList.size(); i++) {
            ResolveInfo p = infoList.get(i);
            Bean bean = new Bean();
            bean.appName = p.loadLabel(getPackageManager()).toString();
            bean.appNameLower=bean.appName.toLowerCase();
            Drawable drawable = p.loadIcon(getPackageManager());
            if (drawable == null) {
                bean.imgUrl = Resources.getSystem().getDrawable(android.R.mipmap.sym_def_app_icon);
            } else {
                bean.imgUrl = drawable;
            }

            bean.packageName = p.activityInfo.packageName;
            if (p.activityInfo != null) {
                bean.name = p.activityInfo.name;
            }

            dataList.add(bean);

        }

        dataList2 = (ArrayList<Bean>) dataList.clone();

        adapter.notifyDataSetChanged();

    }


    public void widListener() {
        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                imgClose.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
                dataList.clear();

                for (Bean bean : dataList2) {
                    if (bean.appNameLower.contains(s)) {
                        dataList.add(bean);
                    }
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtName.setText("");
            }
        });
    }


    class IconAdapter extends RecyclerView.Adapter<IconAdapter.MyViewHold> {


        @Override
        public MyViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHold(getLayoutInflater().inflate(R.layout.lunch_item, parent, false));
        }

        @Override
        public void onBindViewHolder(MyViewHold holder, final int position) {
            holder.imgIcon.setImageDrawable(dataList.get(position).imgUrl);
            holder.txtAppName.setText(dataList.get(position).appName);
            holder.imgIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent lauch = new Intent(Intent.ACTION_MAIN);
                    lauch.addCategory(Intent.CATEGORY_LAUNCHER);
                    lauch.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                    lauch.setComponent(new ComponentName(dataList.get(position).packageName, dataList.get(position).name));
                    startActivity(lauch);
                }
            });
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }


        class MyViewHold extends RecyclerView.ViewHolder {
            public TextView txtAppName;
            public ImageView imgIcon;

            public MyViewHold(View itemView) {
                super(itemView);
                txtAppName = (TextView) itemView.findViewById(R.id.appName);
                imgIcon = (ImageView) itemView.findViewById(R.id.appIcon);
            }
        }

    }


}

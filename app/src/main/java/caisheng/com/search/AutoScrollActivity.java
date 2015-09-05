package caisheng.com.search;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidsoft.utils.credits.CreditsParams;
import org.androidsoft.utils.credits.CreditsView;

import java.util.ArrayList;

public class AutoScrollActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_scroll);
        View v = new CreditsView(this, getCreditsParams());
        ((LinearLayout) findViewById(R.id.lin)).addView(v);

        ListView listView = (ListView) findViewById(R.id.listView2);
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            list.add(i + "asdfasdf");
        }
        BaseAdapter adapter = new BaseAdapter() {
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
            public View getView(final int position, View convertView, ViewGroup parent) {
                ViewHold hold;
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.item_auto, parent, false);
                    hold = new ViewHold();
                    hold.bt = (Button) convertView.findViewById(R.id.bt111);
                    hold.tv = (TextView) convertView.findViewById(R.id.textView12);
                    convertView.setTag(hold);
                } else {
                    hold = (ViewHold) convertView.getTag();
                }
                hold.tv.setText(list.get(position));
                hold.bt.setText("asd");
                hold.bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "asf" + position, 0).show();
                    }
                });
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), position + "asdfadasda", Toast.LENGTH_SHORT).show();
                    }
                });
                return convertView;
            }

            class ViewHold {
                Button bt;
                TextView tv;
            }


        };

        listView.setAdapter(adapter);


    }

    private CreditsParams getCreditsParams() {
        CreditsParams p = new CreditsParams();
        p.setAppNameRes(R.string.app_name);
        p.setAppVersionRes(R.string.abc_search_hint);
        p.setBitmapBackgroundRes(R.drawable.a);
        p.setBitmapBackgroundLandscapeRes(R.drawable.b);
        p.setArrayCreditsRes(R.array.credits);

        p.setColorDefault(0xCCCEA757);
        p.setTextSizeDefault(32);
        p.setTypefaceDefault(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
        p.setSpacingBeforeDefault(10);
        p.setSpacingAfterDefault(18);

        p.setColorCategory(0xCCFFFFFF);
        p.setTextSizeCategory(20);
        p.setTypefaceCategory(Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC));
        p.setSpacingBeforeCategory(12);
        p.setSpacingAfterCategory(12);

        return p;

    }

}

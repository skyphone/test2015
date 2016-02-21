package shopping.category;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import caisheng.com.search.R;


/**
 * Created by Administrator on 2015/9/18.
 */
public class CategoryFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.home_fragment,container,false);

        return v;
    }

}

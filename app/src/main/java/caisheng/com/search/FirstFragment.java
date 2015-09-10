package caisheng.com.search;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FirstFragment extends Fragment {

    public String myData="myData";
    FragCallback callback;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_first, container, false);
        Button bt=(Button)v.findViewById(R.id.button15);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.myItemClick(333);

                getActivity().startService(new Intent(getActivity(),MyService.class));
            }
        });
        return v;
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode==100&&resultCode==getActivity().RESULT_OK){
           Uri da=data.getData();

       }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback=(FragCallback)activity;
    }

}

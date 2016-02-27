package shopping.personal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import caisheng.com.search.R;
import shopping.BaseConfig;
import shopping.MyApplication;
import shopping.login.LoginActivity;
import shopping.login.RegistActivity;


/**
 * Created by Administrator on 2015/9/18.
 */
public class PersonalFragment extends Fragment implements View.OnClickListener{

    private TextView txtLogin;
    private TextView txtRegis;
    private  TextView txtCollect;
    private  TextView txtBrower;
    private TextView txtName;
    private LinearLayout linUnlogin;
    private ImageView imgPersonal;
    private Button btn_user_info;
    private RelativeLayout rel_login;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.personal_fragment,container,false);
        txtLogin=(TextView)v.findViewById(R.id.txt_login);
        txtRegis=(TextView)v.findViewById(R.id.txt_regis);
        txtCollect=(TextView)v.findViewById(R.id.txt_regis);
        txtBrower=(TextView)v.findViewById(R.id.txt_brower);
        btn_user_info=(Button)v.findViewById(R.id.btn_user_info);
        rel_login=(RelativeLayout)v.findViewById(R.id.rel_login);
        btn_user_info.setOnClickListener(this);
        txtLogin.setOnClickListener(this);
        txtRegis.setOnClickListener(this);
        txtCollect.setOnClickListener(this);
        txtBrower.setOnClickListener(this);
        txtName=(TextView)v.findViewById(R.id.txt_name);
        imgPersonal=(ImageView)v.findViewById(R.id.img_personal);
        linUnlogin=(LinearLayout)v.findViewById(R.id.lin_unlogin);
        return v;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_login:
                startActivityForResult(new Intent(getActivity(), LoginActivity.class), BaseConfig.LOGIN_CODE);
                break;
            case R.id.txt_regis:
                startActivity(new Intent(getActivity(), RegistActivity.class));
                break;

            case R.id.btn_user_info:

                break;

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==BaseConfig.LOGIN_CODE&&resultCode== Activity.RESULT_OK){

            linUnlogin.setVisibility(View.GONE);
            rel_login.setVisibility(View.VISIBLE);
            String userName=((MyApplication)getActivity().getApplication()).userInfo.userName;
            txtName.setText(userName);


        }

    }
}

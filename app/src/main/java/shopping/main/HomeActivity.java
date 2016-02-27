package shopping.main;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import caisheng.com.search.R;
import shopping.car.CarFragment;
import shopping.category.CategoryFragment;
import shopping.home.HomeFragment;
import shopping.personal.PersonalFragment;


public class HomeActivity extends Activity {
    Fragment mContent;
    HomeFragment homeFragment;
    CategoryFragment categoryFragment;
    CarFragment carFragment;
    PersonalFragment personalFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContent = new Fragment();
        homeFragment = new HomeFragment();
        switchContent(mContent, homeFragment);

    }

    public void switchContent(Fragment from, Fragment to) {
        if (mContent != to) {
            mContent = to;
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(from).add(R.id.lin_main, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

    public void home(View v) {
        switchContent(mContent, homeFragment);
    }

    public void category(View v) {
        if (categoryFragment == null) {
            categoryFragment = new CategoryFragment();
        }
        switchContent(mContent, categoryFragment);
    }

    public void car(View v) {
        if (carFragment == null) {
            carFragment = new CarFragment();
        }
        switchContent(mContent, carFragment);
    }


    public void personal(View v) {
        if (personalFragment == null) {
            personalFragment = new PersonalFragment();
        }
        switchContent(mContent, personalFragment);
    }
}

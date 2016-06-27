package design.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewAnimator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import caisheng.com.search.R;
import design.mvp.base.BaseActivity;
import design.mvp.model.presentation.MainPresentationModel;
import design.mvp.mvpview.MainView;
import design.mvp.presenters.MainPresenter;
import shopping.MyApplication;

public class MvpActivity extends BaseActivity<MainPresentationModel, MainView, MainPresenter> implements MainView {

    @Bind(R.id.usernameEditText)
    TextInputEditText usernameEditText;
    @Bind(R.id.submitButton)
    Button submitButton;
    @Bind(R.id.fullNameText)
    TextView fullNameText;
    @Bind(R.id.resultAnimator)
    ViewAnimator resultAnimator;
    @Bind(R.id.showReposButton)
    Button showReposButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        ButterKnife.bind(this);
    }

    @Override
    protected void performFieldInjection() {
        MyApplication.getAppComponent().inject(this);
    }

    @NonNull
    @Override
    protected MainPresentationModel createPresentationModel() {

        return new MainPresentationModel();
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void showUserInfo(String showUser) {
        fullNameText.setText(showUser);


    }

    @Override
    public void showRepositoriesScreen(String currentUsername) {

    }

    @OnClick(R.id.submitButton) void onSubmitClicked(){
        String username=usernameEditText.getText().toString();
        presenter.onSubmitClicked(username);
    }

    @OnClick(R.id.showReposButton) void showRepos(){
        presenter.onShowReposClicked();
    }

}

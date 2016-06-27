package design.mvp.mvpview;

import io.appflate.droidmvp.DroidMVPView;

/**
 * Created by lenovo on 2016/6/27.
 */
public interface MainView extends DroidMVPView {
    void showProgress();
    void showUserInfo(String showUser);
    void showRepositoriesScreen(String currentUsername);
}

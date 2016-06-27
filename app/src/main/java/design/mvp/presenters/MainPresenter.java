package design.mvp.presenters;

import javax.inject.Inject;

import design.mvp.domain.GithubApi;
import design.mvp.model.User;
import design.mvp.model.presentation.MainPresentationModel;
import design.mvp.mvpview.MainView;
import io.appflate.droidmvp.SimpleDroidMVPPresenter;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by lenovo on 2016/6/27.
 */
public class MainPresenter extends SimpleDroidMVPPresenter<MainView,MainPresentationModel> {
    private final GithubApi githubApi;

    @Inject
    MainPresenter(GithubApi githubApi){
        this.githubApi=githubApi;
    }

    @Override
    public void attachView(MainView mvpView, MainPresentationModel presentationModel) {
        super.attachView(mvpView, presentationModel);
        if(presentationModel.getName()!=null){
            mvpView.showUserInfo(presentationModel.getName());
        }
    }


    public void  onSubmitClicked(String username){
        if(getMvpView()!=null){
            getMvpView().showProgress();
        }

        getPresentationModel().setLogin(username);
        githubApi.getUserProfile(username).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response) {
                if (response.isSuccess()) {
                    getPresentationModel().setUser(response.body());
                    if (getMvpView() != null) {
                        getMvpView().showUserInfo(getPresentationModel().getName());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }


    public void onShowReposClicked(){
        if(getMvpView()!=null){
            getMvpView().showRepositoriesScreen(getPresentationModel().getLogin());
        }
    }
}

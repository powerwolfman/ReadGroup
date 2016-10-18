package com.lifucong.readgroup.presentation.user.login;

import com.lifucong.apphx.basemvp.MvpView;

/**
 * Created by Administrator on 2016/10/14.
 */

public interface LoginView extends MvpView{
    void showLoading();
    void hideLoading();
    void showMessage(String msg);
    //登录成功后，视图要切换到Home页面
    void navigateToHome();
    LoginView NULL_VIEW=new LoginView() {
        @Override
        public void showLoading() {
        }
        @Override
        public void hideLoading() {
        }
        @Override
        public void showMessage(String msg) {
        }
        @Override
        public void navigateToHome() {
        }
    };
}

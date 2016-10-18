package com.lifucong.readgroup.presentation.user.register;

import com.lifucong.apphx.basemvp.MvpView;

/**
 * Created by Administrator on 2016/10/15.
 */

public interface RegisterView extends MvpView {

    void showLoading();
    void hideLoading();
    void showRegisterSuccess();
    void showRegisterFail(String msg);
    // 关闭注册对话框
    void close();
    RegisterView NULL_VIEW=new RegisterView() {
        @Override
        public void showLoading() {
        }
        @Override
        public void hideLoading() {
        }
        @Override
        public void showRegisterSuccess() {
        }
        @Override
        public void showRegisterFail(String msg) {
        }

        @Override
        public void close() {

        }
    };
}

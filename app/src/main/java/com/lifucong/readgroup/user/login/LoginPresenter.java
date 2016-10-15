package com.lifucong.readgroup.user.login;

import android.support.annotation.NonNull;

import com.lifucong.apphx.HxLoginEvent;
import com.lifucong.apphx.HxUserManager;
import com.lifucong.apphx.basemvp.MvpPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2016/10/14.
 */

public class LoginPresenter extends MvpPresenter<LoginView>{

    /**
     * 登录，协调人要做的主要工作
     */
    public void login(@NonNull final String hxId, @NonNull final String password){
        //协调视图那边的变化
        getView().showLoading();
        //安排业务人员去做事，等待业务人员返结果
        HxUserManager.getInstance().asyncLogin(hxId, password);
    }

    //业务人员返结果（EventBus）
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HxLoginEvent event){
        //协调视图那边的变化
        getView().hideLoading();
        if (event.isSuccess()) {
            getView().navigateToHome();
        }else {
            //协调视图那边的变化
            String msg=String.format("失败原因 : %s",event.getErrorMessage());
            getView().showMessage(msg);
        }
    }

    @Override
    public LoginView getNullObject() {
        return LoginView.NULL_VIEW;
    }
}

package com.lifucong.readgroup.presentation.user.login;

import android.support.annotation.NonNull;

import com.lifucong.apphx.model.HxUserManager;
import com.lifucong.apphx.basemvp.MvpPresenter;
import com.lifucong.apphx.model.event.HxErrorEvent;
import com.lifucong.apphx.model.event.HxEventType;
import com.lifucong.apphx.model.event.HxSimpleEvent;

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
    public void onEvent(HxSimpleEvent event){
        // 判断是否是登录成功事件
        if (event.type != HxEventType.LOGIN) return;
        // 协调视图那边的变化
        getView().hideLoading();
        getView().navigateToHome();
    }


    // 业务人员返结果<EventBus>
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HxErrorEvent event) {
        // 判断是否是登录失败事件
        if (event.type != HxEventType.LOGIN) return;
        getView().hideLoading();
        getView().showMessage(event.toString());
    }

    @Override
    public LoginView getNullObject() {
        return LoginView.NULL_VIEW;
    }
}

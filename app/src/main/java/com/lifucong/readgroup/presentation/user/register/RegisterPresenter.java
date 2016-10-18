package com.lifucong.readgroup.presentation.user.register;

import android.support.annotation.NonNull;
import android.util.Log;

import com.lifucong.apphx.model.HxUserManager;
import com.lifucong.apphx.basemvp.MvpPresenter;
import com.lifucong.apphx.model.event.HxErrorEvent;
import com.lifucong.apphx.model.event.HxEventType;
import com.lifucong.apphx.model.event.HxSimpleEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2016/10/15.
 */

public class RegisterPresenter extends MvpPresenter<RegisterView>{

    public void register(@NonNull final String hxId, @NonNull final String password){
        getView().showLoading();
        Log.e("=================","hxId===="+hxId+",password========"+password);

        HxUserManager.getInstance().asyncRegister(hxId, password);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HxSimpleEvent event){
        // 判断是否是注册事件
        if (event.type != HxEventType.REGISTER) return;
        getView().hideLoading();
        getView().showRegisterSuccess();
        getView().close();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HxErrorEvent event) {
        // 判断是否是注册事件
        if (event.type != HxEventType.REGISTER) return;
        getView().hideLoading();
        getView().showRegisterFail(event.toString());
    }

    @Override
    public RegisterView getNullObject() {
        return RegisterView.NULL_VIEW;
    }
}

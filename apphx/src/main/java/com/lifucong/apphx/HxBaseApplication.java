package com.lifucong.apphx;

import android.app.Application;
import android.widget.Toast;

import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.lifucong.apphx.model.event.HxDisconnectEvent;
import com.lifucong.apphx.model.repository.DefaultLocalUsersRepo;
import com.lifucong.apphx.model.repository.ILocalUsersRepo;
import com.lifucong.apphx.model.repository.IRemoteUserRepo;
import com.lifucong.apphx.model.repository.MockRemoteUsersRepo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import timber.log.Timber;

/**
 * Created by Administrator on 2016/10/11.
 *
 * 环信相关基础配置
 */

public abstract class HxBaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化Timber日志
        Timber.plant(new Timber.DebugTree());
        //初始化环信sdk和easeui库
        initEaseUI();

        // 初始化apphx模块
        initHxModule();

        EventBus.getDefault().register(this);
    }

    protected void initHxModule(){
        IRemoteUserRepo remoteUsersRepo = new MockRemoteUsersRepo();
        ILocalUsersRepo localUsersRepo = DefaultLocalUsersRepo.getInstance(this);
        HxModuleInitializer.getInstance().init(remoteUsersRepo,localUsersRepo);
    }

    private void initEaseUI() {
        EMOptions options=new EMOptions();
        options.setAutoLogin(false);//关闭自动登录

        // 默认添加好友时是不需要验证的,改为需要
        options.setAcceptInvitationAlways(false);

        EMClient.getInstance().init(this,options);
        //关闭环信日志
        EMClient.getInstance().setDebugMode(false);
    }

    // 异常登出情况处理
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HxDisconnectEvent event){
        if (event.errorCode == EMError.USER_LOGIN_ANOTHER_DEVICE) {
            Toast.makeText(this, R.string.hx_error_account_conflict, Toast.LENGTH_SHORT).show();
        } else if (event.errorCode == EMError.USER_REMOVED) {
            Toast.makeText(this, R.string.hx_error_account_removed, Toast.LENGTH_SHORT).show();
        }
        exit();
    }

    protected abstract void exit();
}

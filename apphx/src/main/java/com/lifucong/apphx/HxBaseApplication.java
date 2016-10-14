package com.lifucong.apphx;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

/**
 * Created by Administrator on 2016/10/11.
 *
 * 环信相关基础配置
 */

public class HxBaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化环信sdk和easeui库
        initEaseUI();
    }

    private void initEaseUI() {
        EMOptions options=new EMOptions();
        options.setAutoLogin(false);//关闭自动登录
        options.setAcceptInvitationAlways(true); // 自动同意
        EMClient.getInstance().init(this,options);
        //关闭环信日志
        EMClient.getInstance().setDebugMode(false);
    }
}

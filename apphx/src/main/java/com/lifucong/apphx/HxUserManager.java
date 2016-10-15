package com.lifucong.apphx;

import android.support.annotation.NonNull;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import timber.log.Timber;

/**
 * Model层，环信用户基本功能管理，登录、注册、登出
 *
 * Created by Administrator on 2016/10/14.
 */

public class HxUserManager {

    private static HxUserManager sInstance;
    public static HxUserManager getInstance(){
        if (sInstance==null) {
            sInstance=new HxUserManager();
        }
        return sInstance;
    }

    private final EMClient emClient;
    private final ExecutorService executorService;
    private final EventBus eventBus;

    private HxUserManager (){
        emClient=EMClient.getInstance();
        executorService= Executors.newSingleThreadExecutor();//用于当前业务处理内，业务操作的线程池。
        eventBus=EventBus.getDefault();
    }

    /** 环信异步注册（用于测试，后期将通过自己应用服务进行注册）*/
    public void asyncRegister(@NonNull final String hxId,@NonNull final String password){
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                try {
                    emClient.createAccount(hxId,password);
                    Timber.d("%s RegisterHx success", hxId);
                    //成功了
                    eventBus.post(new HxRegisterEvent());
                } catch (HyphenateException e) {
                    Timber.e("Register fail");
                    //失败了
                    eventBus.post(new HxRegisterEvent(e));
                }

            }
        };
        //提交，线程池处理此Runnable
        executorService.submit(runnable);
    }

    public void asyncLogin(@NonNull final String hxId,@NonNull final String password){
        emClient.login(hxId, password, new EMCallBack() {
            @Override
            public void onSuccess() {
                Timber.d("%s LoginHx success", hxId);
                eventBus.post(new HxLoginEvent());
            }

            @Override
            public void onError(int i, String s) {
                Timber.d("%s LoginHx erreo,code is %s.", hxId,i);
                eventBus.post(new HxLoginEvent(i,s));
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
}


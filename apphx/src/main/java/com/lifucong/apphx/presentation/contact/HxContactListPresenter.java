package com.lifucong.apphx.presentation.contact;

import com.lifucong.apphx.basemvp.MvpPresenter;
import com.lifucong.apphx.model.HxContactManager;
import com.lifucong.apphx.model.event.HxErrorEvent;
import com.lifucong.apphx.model.event.HxEventType;
import com.lifucong.apphx.model.event.HxRefreshContactEvent;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2016/10/17.
 *
 * 环信联系人列表页面 - Presenter
 * <p/>
 * MVP的Presenter:主要负责执行model层业务,接收model层数据,触发view层视图
 * <p/>
 */

public class HxContactListPresenter extends MvpPresenter<HxContactListView>{
    @Override
    public HxContactListView getNullObject() {
        return HxContactListView.NULL;
    }

    //加載联系人业务
    public void loadContacts(){
        HxContactManager.getInstance().getContact();
    }

    //删除联系人业务
    public void deleteContact(String hxId){
        HxContactManager.getInstance().deleteContact(hxId);
    }

    //接受Model层刷新联系人事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HxRefreshContactEvent event){
        //是否有更新
        if (event.changed) {
            //设置到视图
            getView().setContacts(event.contacts);
        }
        getView().refreshContacts();
    }

    //接受Model层错误事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HxErrorEvent event){
        // 不是删除联系人的错误事件，不做处理
        if (event.type!= HxEventType.DELETE_CONTACT)return;
        getView().showDeleteContactFail(event.toString());
    }
}

package com.lifucong.apphx.presentation.contact;

import com.lifucong.apphx.basemvp.MvpView;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 *
 * 环信联系人列表页面 - 视图接口
 * <p/>
 * MVP的view接口:视图接口,将由Activity或Fragment或ViewGroup来实现
 * <p/>
 */

public interface HxContactListView extends MvpView{
    void setContacts(List<String>contacts);
    void refreshContacts();
    void showDeleteContactFail(String msg);
    HxContactListView NULL=new HxContactListView() {
        @Override
        public void setContacts(List<String> contacts) {
        }
        @Override
        public void refreshContacts() {
        }
        @Override
        public void showDeleteContactFail(String msg) {
        }
    };
}

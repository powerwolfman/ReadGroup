package com.lifucong.apphx.model.event;

import com.hyphenate.easeui.domain.EaseUser;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */

public class HxSearchContactEvent {

    public final List<EaseUser> contacts;
    public final boolean isSuccess;
    public final String errorMessage;

    public HxSearchContactEvent(List<EaseUser> contacts){
        this.contacts = contacts;
        this.isSuccess = true;
        this.errorMessage = null;
    }

    public HxSearchContactEvent(String errorMessage){
        this.contacts = null;
        this.isSuccess = false;
        this.errorMessage = errorMessage;
    }
}

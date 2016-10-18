package com.lifucong.apphx.model.event;

import com.hyphenate.exceptions.HyphenateException;

/**
 * Created by Administrator on 2016/10/17.
 *
 * 环信操作发生错误的事件
 */

public final class HxErrorEvent {
    public final HxEventType type;
    public final int errorCode;
    public final String errorMessage;
    public HxErrorEvent(HxEventType type, HyphenateException e){
        this.type=type;
        this.errorCode=e.getErrorCode();
        this.errorMessage=e.getDescription();
    }
    public HxErrorEvent(HxEventType type,int errorCode,String errorMessage){
        this.type=type;
        this.errorCode=errorCode;
        this.errorMessage=errorMessage;
    }

    @Override
    public String toString() {
        return String.format("code: %s %s",errorCode,errorMessage);
    }
}

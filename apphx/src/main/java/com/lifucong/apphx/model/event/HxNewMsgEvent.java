package com.lifucong.apphx.model.event;

import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */

public class HxNewMsgEvent {

    public final List<EMMessage> list;

    public HxNewMsgEvent(List<EMMessage> list) {
        this.list = list;
    }
}

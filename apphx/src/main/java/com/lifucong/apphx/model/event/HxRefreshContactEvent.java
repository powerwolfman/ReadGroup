package com.lifucong.apphx.model.event;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */

public final class HxRefreshContactEvent {
    public final List<String> contacts;

    // true代表联系人列表发生了变化
    public final boolean changed;

    public HxRefreshContactEvent(List<String> contacts) {
        this.contacts = contacts;
        this.changed = true;
    }

    public HxRefreshContactEvent() {
        this.contacts = null;
        this.changed = false;
    }
}

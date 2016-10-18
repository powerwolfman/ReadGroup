package com.lifucong.apphx.presentation.conversation;

import com.lifucong.apphx.basemvp.MvpView;

/**
 * Created by Administrator on 2016/10/17.
 */

public interface HxConversationListView extends MvpView{
    /** 刷新会话列表视图*/
    void refreshConversations();

    HxConversationListView NULL = new HxConversationListView() {
        @Override public void refreshConversations() {
        }
    };
}

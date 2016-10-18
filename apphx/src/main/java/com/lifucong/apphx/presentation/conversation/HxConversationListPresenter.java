package com.lifucong.apphx.presentation.conversation;

import android.support.annotation.NonNull;

import com.hyphenate.chat.EMConversation;
import com.lifucong.apphx.basemvp.MvpPresenter;
import com.lifucong.apphx.model.HxMessageManager;
import com.lifucong.apphx.model.event.HxNewMsgEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2016/10/17.
 */

public class HxConversationListPresenter extends MvpPresenter<HxConversationListView>{
    @NonNull
    @Override public HxConversationListView getNullObject() {
        return HxConversationListView.NULL;
    }

    public void deleteConversation(EMConversation conversation, boolean deleteMessage) {
        HxMessageManager.getInstance().deleteConversation(conversation.getUserName(), deleteMessage);
        getView().refreshConversations();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HxNewMsgEvent event) {
        // 收到会话,刷新会话列表视图
        getView().refreshConversations();
    }
}

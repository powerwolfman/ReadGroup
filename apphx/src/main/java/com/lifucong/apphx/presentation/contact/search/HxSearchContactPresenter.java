package com.lifucong.apphx.presentation.contact.search;

import android.support.annotation.NonNull;

import com.lifucong.apphx.basemvp.MvpPresenter;
import com.lifucong.apphx.model.HxContactManager;
import com.lifucong.apphx.model.event.HxErrorEvent;
import com.lifucong.apphx.model.event.HxEventType;
import com.lifucong.apphx.model.event.HxSearchContactEvent;
import com.lifucong.apphx.model.event.HxSimpleEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2016/10/18.
 */

public class HxSearchContactPresenter extends MvpPresenter<HxSearchContactView> {

    private final HxContactManager hxContactManager;

    public HxSearchContactPresenter() {
        hxContactManager = HxContactManager.getInstance();
    }

    @NonNull
    @Override public HxSearchContactView getNullObject() {
        return HxSearchContactView.NULL;
    }

    public void searchContact(String query) {
        getView().startLoading();
        hxContactManager.asyncSearchContacts(query);
    }

    public void sendInvite(String toHxId) {
        // 如果已经是好友
        if (hxContactManager.isFriend(toHxId)) {
            getView().showAlreadyIsFriend();
            return;
        }
        getView().startLoading();

        hxContactManager.asyncSendInvite(toHxId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HxSearchContactEvent event) {
        getView().stopLoading();

        if (event.isSuccess) {
            getView().showContacts(event.contacts);

            if (event.contacts.size() == 0) {
                getView().showSearchError("No match result!");
            }
        } else {
            getView().showSearchError(event.errorMessage);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HxSimpleEvent e) {
        if (e.type != HxEventType.SEND_INVITE) return;
        getView().stopLoading();
        getView().showSendInviteResult(true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HxErrorEvent e) {
        if (e.type != HxEventType.SEND_INVITE) return;
        getView().stopLoading();
        getView().showSendInviteResult(false);
    }
}

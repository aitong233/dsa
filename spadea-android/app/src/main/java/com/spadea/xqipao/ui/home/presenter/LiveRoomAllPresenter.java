package com.spadea.xqipao.ui.home.presenter;

import android.content.Context;

import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.home.contacts.LiveRoomAllContacts;

public class LiveRoomAllPresenter extends BasePresenter<LiveRoomAllContacts.View> implements LiveRoomAllContacts.ILiveRoomAllPre {

    public LiveRoomAllPresenter(LiveRoomAllContacts.View view, Context context) {
        super(view, context);
    }


}

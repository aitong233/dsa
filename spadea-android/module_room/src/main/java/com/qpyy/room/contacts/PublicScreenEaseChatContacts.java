package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;

public final class PublicScreenEaseChatContacts {


    public interface View extends IView<Activity> {

    }

    public interface IPublicScreenEaseChatPre extends IPresenter {
          void logEmchat(int code,String msg,String toChatUsername);

          void switchPublicScreen(String room_id,String status);
    }
}
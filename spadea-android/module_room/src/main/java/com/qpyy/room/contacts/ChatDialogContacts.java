package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.libcommon.bean.EmChatUserInfo;

public final class ChatDialogContacts {


    public interface View extends IView<Activity> {

        void userInfo(EmChatUserInfo userInfo);
    }

    public interface IChatDialogPre extends IPresenter {

    }
}
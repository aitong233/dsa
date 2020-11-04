package com.qpyy.module.me.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module.me.bean.UserRoomResp;

public final class UserRoomContacts {


    public interface View extends IView<Activity> {
        void setUserRoom(UserRoomResp data);
    }

    public interface IUserRoomPre extends IPresenter {
        void getUserRoom(String userId);
    }
}

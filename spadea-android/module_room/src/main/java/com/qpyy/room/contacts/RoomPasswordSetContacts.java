package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;

public final class RoomPasswordSetContacts {


    public interface View extends IView<Activity> {
        void roomPasswordSettingSuccess();
    }

    public interface IRoomPasswordSetPre extends IPresenter {
        void setRoomPassword(String roomId, String password);
    }
}
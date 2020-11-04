package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;

public final class RoomToolContacts {


    public interface View extends IView<Activity> {
        void setRoomCardiacSuccess();

    }

    public interface IRoomToolPre extends IPresenter {
        void clearRoomCardiac(String roomId);

        void setRoomCardiac(String roomId, int state);
    }
}
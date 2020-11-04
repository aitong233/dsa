package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;

public final class BaseRoomContacts {


    public interface View extends IView<Activity> {
    }

    public interface IBaseRoomPre extends IPresenter {
        void downWheat(String roomId);

        void applyWheat(String roomId, String pitNumber);

        void applyWheatWait(String roomId, String pitNumber);

        void getRoomInfo(String roomId);

        void putOnWheat(String roomId, String userId);


    }
}
package com.qpyy.room.contacts;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.room.bean.RoomPitInfo;


public final class WheatToolContacts {

    public interface View extends IView<Activity> {
        void setRoomPitInfo(RoomPitInfo data);

        void clearCardiacSuccess();

        void closePitSuccess();

        void dismissDialog();
    }

    public interface IWheatToolPre extends IPresenter {
        void roomPitInfo(String roomId, String pitNumber);

        void clearCardiac(String roomId, String pitNumber);

        void closePit(String state, String pitNumber, String roomId);
    }


}

package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.room.bean.ProtectedItemBean;

import java.util.List;

public final class StationRoomContacts {


    public interface View extends IView<Activity> {
        void protectedList(List<ProtectedItemBean> list, int type);

        void dismissOpenGuardDialog();
    }

    public interface IStationRoomPre extends IPresenter {
        void applyWheatFm(String roomId, String pitNumber);

        void getProtectedList(int type);

        void openFmProtected(String roomId, String type, String userId);
    }
}
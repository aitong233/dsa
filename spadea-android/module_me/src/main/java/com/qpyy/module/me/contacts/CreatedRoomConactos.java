package com.qpyy.module.me.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.libcommon.bean.CheckTxtResp;

public final class CreatedRoomConactos {


    public interface View extends IView<Activity> {
        void checkTxtSuccess(CheckTxtResp result);

        void addUserRoomSuccess(String roomId);
    }


    public interface ICreatedRoomPre extends IPresenter {
        void checkTxt(String content);

        void addUserRoom(String roomName, String labelId);
    }
}

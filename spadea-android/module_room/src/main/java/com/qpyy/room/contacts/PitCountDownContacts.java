package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;

public final class PitCountDownContacts {


    public interface View extends IView<Activity> {
        void pitCountDown(String roomId, String pitNumber, String time);
    }

    public interface IPitCountDownPre extends IPresenter {
        void pitCountDown(String roomId, String pitNumber, String time);
    }
}
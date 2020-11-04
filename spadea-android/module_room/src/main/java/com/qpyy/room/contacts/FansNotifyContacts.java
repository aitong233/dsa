package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.room.bean.FansNotifyInfo;

public final class FansNotifyContacts {


    public interface View extends IView<Activity> {
        void fansNotifyInfo(FansNotifyInfo info);
        void success();
    }

    public interface IFansNotifyPre extends IPresenter {
        void fansNotifyInfo();
        void fansNotify(String roomId);
    }
}
package com.qpyy.module.index.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module.index.bean.LastWeekStarResp;


public final class WeekStarContacts {


    public interface View extends IView<Activity> {
        void setLastWeekStar(LastWeekStarResp resp);
        void refreshFollow();
    }

    public interface IWeekStarPre extends IPresenter {
        void getLastList(String roomId, int type);

        void follow(String userId);
    }
}
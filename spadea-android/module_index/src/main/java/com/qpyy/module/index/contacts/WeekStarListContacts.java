package com.qpyy.module.index.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module.index.bean.CharmRankingResp;
import com.qpyy.module.index.bean.WeekStarResp;


public final class WeekStarListContacts {


    public interface View extends IView<Activity> {
        void setGifts(WeekStarResp.GiftInfoBean giftInfo);

        void setMyInfo(WeekStarResp.GiftRichBean.MyBean myInfo);

        void setCharmList(WeekStarResp.GiftCharmBean bean);

        void setRoomList(WeekStarResp.GiftRoomBean bean);

        void setWealthList(WeekStarResp.GiftRichBean bean);
    }

    public interface IWeekStarListPre extends IPresenter {
        void getList(String roomId, int type);
    }
}
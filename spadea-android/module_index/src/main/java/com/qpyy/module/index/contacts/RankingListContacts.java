package com.qpyy.module.index.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module.index.bean.CharmRankingResp;

import java.util.List;

public final class RankingListContacts {


    public interface View extends IView<Activity> {
        void setMyInfo(CharmRankingResp.MyBean myInfo);

        void setNo1(CharmRankingResp.ListsBean listsBean);

        void setNo2(CharmRankingResp.ListsBean listsBean);

        void setNo3(CharmRankingResp.ListsBean listsBean);

        void setList(List<CharmRankingResp.ListsBean> list);
    }

    public interface IRankingListPre extends IPresenter {
        void getCharmList(String roomId, int type);

        void getWealthList(String roomId, int type);
    }
}
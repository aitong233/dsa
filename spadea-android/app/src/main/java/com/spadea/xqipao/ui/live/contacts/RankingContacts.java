package com.spadea.xqipao.ui.live.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.CharmModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class RankingContacts {
    public interface View extends IView<FragmentActivity> {
        void setNo1(CharmModel.ListsBean data);

        void setNo2(CharmModel.ListsBean data);

        void setNo3(CharmModel.ListsBean data);

        void setListData(List<CharmModel.ListsBean> list);

        void setUserData(CharmModel.MyBean myBean);
        void networkCompletion();
    }

    public interface IRankingPre extends IPresenter {
        void getCharmList(String roomId, int type);

        void getWealthList(String roomId, int type);
    }
}

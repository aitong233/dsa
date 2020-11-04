package com.spadea.xqipao.ui.live.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.CharmModel;
import com.spadea.xqipao.data.RoomRankingModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class RoomRankingContacts {

    public interface View extends IView<FragmentActivity> {
        void setNo1(RoomRankingModel data);

        void setNo2(RoomRankingModel data);

        void setNo3(RoomRankingModel data);

        void setListData(List<RoomRankingModel> list);

        void setUserData(CharmModel.MyBean myBean);

        void networkCompletion();


    }

    public interface IRoomRankingPre extends IPresenter {
        void getRoomRankingList();


    }


}

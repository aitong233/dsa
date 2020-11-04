package com.spadea.xqipao.ui.room.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.CharmModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class RoomRankingContacts {


    public interface View extends IView<FragmentActivity> {
        void setData(List<CharmModel.ListsBean> data);

        void setUserData(CharmModel.MyBean data);

        void onComplete();
    }

    public interface IRoomRankingPre extends IPresenter {
        void getCharmList(String roomId, int type);

        void getWealthList(String roomId, int type);
    }

}

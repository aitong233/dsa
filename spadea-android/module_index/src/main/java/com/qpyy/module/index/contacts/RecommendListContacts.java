package com.qpyy.module.index.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module.index.bean.RoomModel;

import java.util.List;

public final class RecommendListContacts {


    public interface View extends IView<Activity> {
        void roomList(List<RoomModel> data,int page);

        void finishRefreshLoadMore();
    }

    public interface IRecommendListPre extends IPresenter {
        void getRoomList(int page);
    }
}
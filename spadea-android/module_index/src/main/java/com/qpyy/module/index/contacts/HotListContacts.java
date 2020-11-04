package com.qpyy.module.index.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module.index.bean.RoomModel;

import java.util.List;

public final class HotListContacts {


    public interface View extends IView<Activity> {
        void roomList(List<RoomModel> data);

        void finishRefreshLoadMore();
    }

    public interface IHotListPre extends IPresenter {
        void getRoomList(String type);
    }
}
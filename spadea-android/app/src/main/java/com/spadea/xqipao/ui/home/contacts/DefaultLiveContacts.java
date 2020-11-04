package com.spadea.xqipao.ui.home.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.RoomModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class DefaultLiveContacts {


    public interface View extends IView<FragmentActivity> {
        void roomListtSuccess(List<RoomModel> data);

        void roomListComplete();
    }

    public interface IDefaultLivePre extends IPresenter {
        void getRoomList(String type);
    }

}

package com.spadea.xqipao.ui.home.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.RoomModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class HostContacts {

    public interface View extends IView<FragmentActivity> {
        void hostRoomSuccess(List<RoomModel> data);

        void hostRoomComplete();

    }

    public interface IHostPre extends IPresenter {
        void getHostRoom();
    }

}

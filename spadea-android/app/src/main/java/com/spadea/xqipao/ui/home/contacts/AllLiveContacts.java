package com.spadea.xqipao.ui.home.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.RoomModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class AllLiveContacts {


    public interface View extends IView<FragmentActivity> {
        void setAllRoomData(List<RoomModel> data);

        void allRoomComplete();


    }

    public interface IAllLivePre extends IPresenter {
        void getAllRoom();

        void collection(String roomId);

        void cancelCollection(String roomId);
    }
}

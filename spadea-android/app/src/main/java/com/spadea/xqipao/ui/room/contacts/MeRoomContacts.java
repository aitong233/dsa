package com.spadea.xqipao.ui.room.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.ManageRoomModel;
import com.spadea.xqipao.data.MyManageRoomModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class MeRoomContacts {


    public interface View extends IView<FragmentActivity> {
        void roomDataSuccess(List<ManageRoomModel> data);

        void MyRoomDataSuccess(MyManageRoomModel manageRoomModels);

        void roomDataComplete();

        void roomManagerCanceled(int position);
    }

    public interface IMeRoomPre extends IPresenter {
        void manageRoom(int page);

        void collectRoom(int page);

        void cancelRoomManager(String roomId,int position);
    }

}

package com.spadea.xqipao.ui.room.contacts;

import android.app.Activity;

import com.spadea.xqipao.data.RoomExtraModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class RoomInfoContacts {


    public interface View extends IView<Activity> {
        void setRoomExtraSuccess(RoomExtraModel roomExtraModel);

        void delete(int type, int postion);

        void deleteManagerSuccess(String userId);

        void deleteForbidSuccess(String userId);
    }

    public interface IRoomInfoPre extends IPresenter {

        void getRoomExtra(String roomId, String password);

        void deleteManager(String roomId, String userId, int postion);

        void deleteForbid(String roomId, String userId, int postion);
    }

}

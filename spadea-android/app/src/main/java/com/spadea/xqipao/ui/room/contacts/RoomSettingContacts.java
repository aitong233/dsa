package com.spadea.xqipao.ui.room.contacts;

import android.app.Activity;

import com.spadea.xqipao.data.RoomExtraModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class RoomSettingContacts {


    public interface View extends IView<Activity> {
        void setRoomExtraSuccess(RoomExtraModel roomExtraModel);

        void editRoomSuccess();
    }

    public interface IRoomSettingPre extends IPresenter {
        void getRoomExtra(String roomId, String password);

        void editRoom(String coverPicture, String bgPicture, String password, String playing, String roomId, String roomName, String labelId,String typeId,String greeting,String wheat,String is_password);
    }
}

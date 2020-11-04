package com.spadea.xqipao.ui.room.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.RoomPitInfo;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class WheatToolContacts {

    public interface View extends IView<FragmentActivity> {
        void setRoomPitInfo(RoomPitInfo data);

        void setRoomCardiacSuccess();

        void clearRoomCardiacSuccess();

        void clearCardiacSuccess();

        void closePitSuccess();
    }

    public interface IWheatToolPre extends IPresenter {
        void roomPitInfo(String roomId, String pitNumber);

        void setRoomCardiac(String roomId, int state);

        void clearRoomCardiac(String roomId);

        void clearCardiac(String roomId, String pitNumber);

        void closePit(String state, String pitNumber, String roomId);
    }


}

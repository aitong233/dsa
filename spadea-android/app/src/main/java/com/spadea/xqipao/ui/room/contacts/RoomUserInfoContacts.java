package com.spadea.xqipao.ui.room.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.RoomUserInfo;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class RoomUserInfoContacts {


    public interface View extends IView<FragmentActivity> {
        void setRoomUserInfoData(RoomUserInfo data);

        void roomUserInfoFail();

        void followUserSuccess(int type);

        void downUserWheatSuccess(String userName, String pitNumber);

        void roomUserShutUp(int type, String userName);

        void kickOutSuccess(String userName);

        void setRoomBannedSuccess(String userName, int type);
    }

    public interface IRoomUserInfoPre extends IPresenter {
        void getRoomUserInfo(String roomId, String userId);

        void followUser(String userId, int type);

        void downUserWheat(String userId, String roomId, String userName, String pitNumber);


        void kickOut(String userId, String roomId, String userName);

        void roomUserShutUp(String roomId, String userId, int type, String userName);

        void setRoomBanned(String roomId, String userId, int type, String userName);
    }

}

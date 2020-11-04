package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.room.bean.RoomUserInfoResp;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.contacts
 * 创建人 黄强
 * 创建时间 2020/8/13 09:16
 * 描述 describe
 */
public class RoomUserInfoContacts {

    public interface View extends IView<Activity> {
        void setRoomUserInfoData(RoomUserInfoResp data);

        void roomUserInfoFail();

        void followUserSuccess(int type);

        void downUserWheatSuccess(String userName, String pitNumber);

        void roomUserShutUp(int type, String userName);

        void kickOutSuccess(String userName);

        void setRoomBannedSuccess(String userName, int type);

        void dismissDialog();
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

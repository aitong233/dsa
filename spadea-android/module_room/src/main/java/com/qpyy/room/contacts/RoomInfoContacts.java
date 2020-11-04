package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.room.bean.RoomExtraModel;
import com.qpyy.room.bean.RoomSceneItem;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.contacts
 * 创建人 黄强
 * 创建时间 2020/7/29 11:05
 * 描述 describe
 */
public class RoomInfoContacts {

    public interface View extends IView<Activity> {
        void setRoomExtraSuccess(RoomExtraModel roomExtraModel);//获取房间其他信息成功

        void delete(int type, int position);//删除

        void deleteAdminSuccess(String userId);//删除管理员成功

        void deleteBlacklistSuccess(String userId);//删除黑名单成功

        void editRoomSuccess();//编辑成功

        void uploadSuccess(String imageUrl);

        void soundEffectInfo(List<RoomSceneItem> items);

        void updateSoundEffectSuccess(RoomSceneItem item);
    }

    public interface MyRoomMsgPre extends IPresenter {

        void getRoomExtra(String roomId, String password);//获取房间其他信息

        void deleteAdmin(String roomId, String userId, int position);

        void deleteBlacklist(String roomId, String userId, int position);

        void editRoom(String coverPicture, String bgPicture, String password,
                      String playing, String roomId, String roomName,
                      String labelId, String typeId, String greeting,
                      String wheat, String is_password);//房间修改提交

        void uploadImage(File file, int type);

        void roomUpdate(Map<String, String> map);

        void soundEffectInfo();

        void updateSoundEffect(String roomId, RoomSceneItem item, String coverImageUrl, String password, String playCon, String roomName, String welcomeCon, String isPassword);


    }
}

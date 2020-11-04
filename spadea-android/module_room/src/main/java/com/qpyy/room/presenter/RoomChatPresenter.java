package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.room.contacts.RoomChatContacts;
import com.qpyy.room.contacts.RoomOnlineContacts;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.presenter
 * 创建人 黄强
 * 创建时间 2020/7/28 16:48
 * 描述 describe
 */
public class RoomChatPresenter extends BaseRoomPresenter<RoomChatContacts.View> implements RoomChatContacts.IRoomChatPre {

    public RoomChatPresenter(RoomChatContacts.View view, Context context) {
        super(view, context);
    }

}

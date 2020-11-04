package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.room.bean.NewsModel;

import java.util.Map;


public final class RoomFragmentContacts {


    public interface View extends IView<Activity> {
        void switchMic(int type);
        void userNewsSuccess(NewsModel newsModel);
        void setUserCount(String userCount);
    }

    public interface IRoomFragmentPre extends IPresenter {
        void sendTxtMessage(String user_id, String type, String content, String room_id);

        void roomUpdate(Map<String, String> map, String roomId);

        void switchMic(String roomId, String pitNumber, int type);

    }
}
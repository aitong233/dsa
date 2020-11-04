package com.qpyy.room.contacts;

import android.app.Activity;
import android.app.Application;

import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.libcommon.db.table.MusicTable;
import com.qpyy.room.bean.RoomInfoResp;
import com.qpyy.rtc.Rtc;

import java.util.List;

public final class RoomContacts {


    public interface View extends IView<Activity> {
        void roomInfo(RoomInfoResp resp);

        void enterFail();

        void addFavoriteSuccess();

        void quitSuccess( );

        void showPasswordDialog();

        void playNextMusic();

        void onRtcDestroySuccess();

    }

    public interface IRoomPre extends IPresenter {


        void initRtcManager(Application application);

        void getRoomIn(String roomId, String password);

        void addFavorite(String roomId);


        void quitRoom(String roomId);

        int getLocalMusicCount();

        List<MusicTable> getLovalMusicData();

    }
}
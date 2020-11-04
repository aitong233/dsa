package com.spadea.xqipao.ui.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.db.table.MusicTable;
import com.spadea.xqipao.data.EmojiModel;
import com.spadea.xqipao.data.FmApplyWheatResp;
import com.spadea.xqipao.data.ProtectedItemBean;
import com.spadea.xqipao.data.RoomDetailBean;
import com.spadea.xqipao.data.RoomPollModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class LiveRoomContacts {


    public interface View extends IView<Activity> {
        void roomAuthSuccess();

        void roomEnterFail();

        void removeFavoriteSuccess();

        void addRoomCollectSuccess();

        void applyWheatWaitSuccess(String state, String pitNumber);

        void quitRoomSuccess();

        void setRoomData(RoomDetailBean data, boolean b);

        void setRoomPoll(RoomPollModel roomPoll, boolean b, EmojiModel emojiModel);

        void piCountDownSuccess(String roomId, String pitNumber, String time);

        void applyWheatFmCallback(FmApplyWheatResp resp);

        void protectedList(List<ProtectedItemBean> list, int type);

        void sendTextMsg(String msg);
    }

    public interface ILiveRoomPre extends IPresenter {

        void roomGetIn(String roomId, String password);

        void addRoomCollect(String roomId);

        void removeFavorite(String roomId);

        void downWheat(String roomId);

        void applyWheat(String roomId, String pitNumber);

        void applyWheatWait(String roomId, String pitNumber);


        void follow(String userId, int type);

        int getLocalMusicCount();

        List<MusicTable> getLovalMusicData();

        void quitRoom(String roomId);

        void setRoomPassword(String roomId, String password);

        void roomPoll(String roomId, boolean b, EmojiModel emojiModel);

        void getInRoomInfo(String roomId);

        void pitCountDown(String roomId, String pitNumber, String time);

        void applyWheatFm(String roomId, String pitNumber);

        void openFmProtected(String roomId, String type, String userId);

        void getProtectedList(int type);

        void filterMessage(String msg);
    }

}

package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.room.bean.ExclusiveEmojiResp;

import java.util.List;

public final class EmojContacts {

    public interface View extends IView<Activity> {
        void setEmojData(List<List<ExclusiveEmojiResp>> data);
        void sendSuccess();
    }

    public interface IEmojPre extends IPresenter {
        void getEmojData(int isGameRoom);

        void getExclusiveEmojData();

        void sendFace(String roomId, String userId, String pit, int type);

        void roomPoll(String roomId,String pitNumber);
    }

}

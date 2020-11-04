package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;

public final class RoomGuideContacts {


    public interface View extends IView<Activity> {
        void success();
    }

    public interface IRoomGuidePre extends IPresenter {
        void completeGuide(String roomId);
    }
}
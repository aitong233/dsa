package com.qpyy.room.contacts;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.room.bean.BallResp;


public final class GameContactrs {


    public interface View extends IView<Activity> {
        void ballStartSuccess(BallResp ballResp);
        void ballThrowSuccess(   );
        void ballShowSuccess(BallResp ballResp);
    }


    public interface IGamePre extends IPresenter {
        void ballStart(String roomId, String pitNumber);

        void ballThrow(String roomId, String pitNumber);

        void ballShow(String roomId, String pitNumber);
    }

}

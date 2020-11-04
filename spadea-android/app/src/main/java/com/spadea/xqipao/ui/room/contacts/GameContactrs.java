package com.spadea.xqipao.ui.room.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.GameInfo;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class GameContactrs {


    public interface View extends IView<FragmentActivity> {
        void setData(GameInfo gameInfo1, GameInfo gameInfo2, GameInfo gameInfo3);
    }


    public interface IGamePre extends IPresenter {
        void start();
    }

}

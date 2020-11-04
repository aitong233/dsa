package com.yutang.game.fudai.contacts;

import android.support.v4.app.FragmentActivity;


import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.yutang.game.fudai.bean.GameLog;

import java.util.List;


public class GameLogContacts {
    public interface View extends IView<FragmentActivity> {
        void gameLog(List<GameLog> list);
    }

    public interface GameLogPre extends IPresenter {
        void getGameLog();
    }

}

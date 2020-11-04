package com.yutang.game.fudai.contacts;

import android.support.v4.app.FragmentActivity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.libcommon.bean.LuckyPackLuckyRankItemBean;

import java.util.List;


public class GameRankContacts {
    public interface View extends IView<FragmentActivity> {

        void luckyRankList(int type, List<LuckyPackLuckyRankItemBean> list);
    }

    public interface GameRankPre extends IPresenter {

    }
}

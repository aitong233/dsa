package com.yutang.game.fudai.contacts;

import android.support.v4.app.FragmentActivity;


import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.yutang.game.fudai.bean.CatHelpModel;
import com.yutang.game.fudai.bean.FishInfoBean;
import com.yutang.game.fudai.bean.LuckGiftBean;
import com.yutang.game.fudai.bean.WinJackpotModel;

import java.util.List;


public class EggGameDialogContacts {
    public interface View extends IView<FragmentActivity> {
        void setFishInfo(FishInfoBean fishInfo);

        void startFishingSuccess(int num);

        void gameResult(LuckGiftBean eggGiftModels, int num, int type);

        void gameRule(CatHelpModel catHelpModel);

        void poolList(List<WinJackpotModel> list);
    }

    public interface EggGamePre extends IPresenter {
        void getFishInfo(String type);

        void startFishing(int num, int type);

        void getRules();

        void getPool(String type);
    }
}

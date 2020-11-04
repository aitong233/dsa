package com.qpyy.room.contacts;

import android.support.v4.app.FragmentActivity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.room.bean.CatHelpModel;
import com.qpyy.room.bean.FishInfoBean;
import com.qpyy.room.bean.LuckGiftBean;
import com.qpyy.room.bean.WinJackpotModel;

import java.util.List;


public class EggGameDialogContacts {
    public interface View extends IView<FragmentActivity> {
        void setFishInfo(FishInfoBean fishInfo);

        void startFishingSuccess(int num);

        void gameResult(LuckGiftBean eggGiftModels);

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

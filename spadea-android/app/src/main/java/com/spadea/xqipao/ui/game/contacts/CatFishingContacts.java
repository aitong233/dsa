package com.spadea.xqipao.ui.game.contacts;

import android.app.Activity;

import com.spadea.xqipao.data.FishInfoBean;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class CatFishingContacts {


    public interface View extends IView<Activity> {
        void setFishInfo(FishInfoBean fishInfo);

        void startFishingSuccess(int num);
    }

    public interface ICatFishingPre extends IPresenter {
        void getWinRanking();

        void getCatHelp();

        void getCatWinJackpot();

        void getPropList();

        void getFishInfo();

        void startFishing(int num);
    }
}

package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.data.ProfitModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class ProfitContacter {


    public interface View extends IView<Activity> {

        void setEarnings(String earnings);


        void convertEarningsSuccess();

        void userProfit(ProfitModel profitModel);

        void roomProfit(ProfitModel profitModel);

        void applyRoomProfitSuccess();

    }

    public interface IProfitPre extends IPresenter {
        void getEarnings();

        void convertEarnings(String number, String password);

        void exchangeRoomEarnings(String number, String password);


        void getUserProfit();

        void getRoomProfit();

        void applyRoomProfit(String password, String number);
    }

}

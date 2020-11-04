package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.qpyy.libcommon.bean.RechargeInfoModel;
import com.spadea.xqipao.data.WxPayModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.ArrayList;

public final class BalanceContacter {


    public interface View extends IView<Activity> {
        void userRechargeSuccess(String oederId, int type);

        void aliPayment(String orderNo);

        void weixinPayment(WxPayModel wxPayModel);

        void setBalanceMoney(String money);

        void rechargeInfoSuccess(ArrayList<RechargeInfoModel> s);
    }

    public interface IBalancePre extends IPresenter {
        void userRecharge(String money, int type);

        void aliPay(String orderId, int type);

        void weixinPay(String orderId, int type);

        void getBalance();
    }

}

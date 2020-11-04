package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.libcommon.bean.RechargeInfoModel;
import com.qpyy.room.bean.WxPayModel;

import java.util.ArrayList;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.contacts
 * 创建人 黄强
 * 创建时间 2020/8/14 13:59
 * 描述 describe
 */
public class RechargeContacter {

    public interface View extends IView<Activity> {
        void userRechargeSuccess(String orderId, int type);

        void aliPayment(String orderNo);

        void weixinPayment(WxPayModel wxPayModel);

        void rechargeInfoSuccess(ArrayList<RechargeInfoModel> s);
    }

    public interface IRechargePre extends IPresenter {
        void userRecharge(String money, int type);

        void aliPay(String orderId, int type);

        void weixinPay(String orderId, int type);

    }
}

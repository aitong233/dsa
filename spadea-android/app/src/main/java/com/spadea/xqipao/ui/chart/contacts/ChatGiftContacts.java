package com.spadea.xqipao.ui.chart.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.libcommon.bean.GiftModel;

import java.util.List;

public final class ChatGiftContacts {


    public interface View extends IView<Activity> {
        void setData(List<GiftModel> data);

        void setGiftNumBeanData(List<com.qpyy.module_news.bean.GiftNumBean> data);

        void setBalanceMoney(String money);

        void pop();
    }

    public interface IChatGiftPre extends IPresenter {
        void giftWall();

        void giveGift(GiftModel giftModel, String user_id, String number);

        void getBalance();
    }
}
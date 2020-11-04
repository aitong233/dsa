package com.spadea.xqipao.ui.me.contacter;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.OrdersResp;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class MyOrderContacts {


    public interface View extends IView<FragmentActivity> {
        void endLoading();
        void ordersResp(int page, OrdersResp ordersResp);
    }

    public interface IMyOrderPre extends IPresenter {
        void getOrder(int type, int page);
    }
}
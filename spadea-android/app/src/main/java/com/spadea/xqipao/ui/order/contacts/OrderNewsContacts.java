package com.spadea.xqipao.ui.order.contacts;

import android.app.Activity;

import com.spadea.xqipao.data.OrderMsgResp;
import com.spadea.xqipao.data.UpdateOrderModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class OrderNewsContacts {


    public interface View extends IView<Activity> {
        void endLoading();
        void newsList(int page, OrderMsgResp resp);
        void refresh();
        void serviceUserSuccess(String uin);
    }

    public interface IOrderNewsPre extends IPresenter {
        void getList(int page);
        void accompanyAccept(UpdateOrderModel updateOrderModel);
        void serviceUser();
    }
}
package com.spadea.xqipao.ui.order.contacts;

import android.app.Activity;

import com.spadea.xqipao.data.OrderDetailResp;
import com.spadea.xqipao.data.UpdateOrderModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class OrderDetailContacts {


    public interface View extends IView<Activity> {
        void orderDetail(OrderDetailResp resp);
        void confirmOrder();
        void accompanyAccept(UpdateOrderModel model);
        void accompanyService();
    }

    public interface IOrderDetailPre extends IPresenter {
        void getDetail(int orderId, int orderType);
        void accompanyService(int orderId);//立即服务
        void bossConfirmOrder(int orderId);//完成订单
        void accompanyAccept(UpdateOrderModel model);//陪陪确认订单
    }
}
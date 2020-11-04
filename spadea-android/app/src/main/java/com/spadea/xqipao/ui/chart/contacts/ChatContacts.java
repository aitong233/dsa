package com.spadea.xqipao.ui.chart.contacts;

import android.app.Activity;

import com.qpyy.module_news.bean.EmChatUserInfo;
import com.spadea.xqipao.data.LastOrderMsg;
import com.spadea.xqipao.data.UpdateOrderModel;
import com.spadea.xqipao.data.even.PullOrderMsgEvent;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class ChatContacts {


    public interface View extends IView<Activity> {
        void lastOrderMsg(LastOrderMsg msg);

        void pullOrderMsg(PullOrderMsgEvent event);

        void userInfo(EmChatUserInfo userInfo);

        void showGiftDialog(String user_id);
    }

    public interface IChatPre extends IPresenter {
        void getLastOrderMsg(String easeName);

        void accompanyService(int orderId);//立即服务

        void agreeRefund(int orderId);//同意退款

        void disAgreeRefund(int orderId);//不同意退款

        void accompanyAccept(UpdateOrderModel model);//陪陪确认订单

        void bossAcceptService(UpdateOrderModel model);//老板立即服务

        void bossConfirmOrder(int orderId);//完成订单

        void bossRefundOrder(int orderId);//退款

        void bossAppealing(int orderId, String userId, String playUserId);//发起申诉

        void bossAgreeRefuseRefund(int orderId);//同意拒绝退款
    }
}
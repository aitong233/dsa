package com.spadea.xqipao.ui.order.contacts;

import android.app.Activity;

import com.spadea.xqipao.data.AddOrderModel;
import com.spadea.xqipao.data.OrderPayModel;
import com.spadea.xqipao.data.OrderSkillSelectItem;
import com.spadea.xqipao.data.UserSkillInfo;
import com.spadea.xqipao.data.VerifyOrderTimeModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class ConfirmOrderContacts {


    public interface View extends IView<Activity> {
        void skillList(List<OrderSkillSelectItem> list);

        void skillInfo(UserSkillInfo skillInfo);

        void orderCreated(String resp);

        void timeVerified(VerifyOrderTimeModel model, String timeStr);

        void paySuccess();

        void showNoBalance(String balance);
    }

    public interface IConfirmOrderPre extends IPresenter {
        void getSkillList(String userId);

        void getSkillInfo(String userId, int skillId);

        void createOrder(AddOrderModel model, android.view.View view);

        void verifyTime(VerifyOrderTimeModel model,String timeStr);

        void pay(OrderPayModel model);

    }
}
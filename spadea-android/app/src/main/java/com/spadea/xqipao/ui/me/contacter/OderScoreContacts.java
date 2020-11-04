package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.data.EvaluateModel;
import com.spadea.xqipao.data.OrderDetailResp;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class OderScoreContacts {


    public interface View extends IView<Activity> {
        void evaluateComplete();

        void orderDetail(OrderDetailResp resp);
    }

    public interface IOderScorePre extends IPresenter {
        void evaluate(EvaluateModel model, int type);

        void getDetail(int orderId);
    }
}
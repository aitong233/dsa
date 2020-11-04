package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.data.CashTypeModel;
import com.spadea.xqipao.data.EarningsModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class PaymentDetailsContacter {


    public interface View extends IView<Activity> {
        void getCashLogSuccess(List<EarningsModel.EarningInfo> list, boolean x);

        void cashTypeSuccess(List<CashTypeModel> data);
    }

    public interface IPaymentDetailsPre extends IPresenter {

        void getCashLog(int type, boolean x);

        void cashType();
    }
}

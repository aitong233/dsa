package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.data.UserBankModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class WithdrawalContacter {

    public interface View extends IView<Activity> {
        void setUserMoney(String money);

        void setUserBank(UserBankModel bankInfo);

        void userWithdrawSuccess();
    }

    public interface IWithdrawalPre extends IPresenter {
        void getUserBank();

        void userWithdraw(String bankId, String num);
    }


}

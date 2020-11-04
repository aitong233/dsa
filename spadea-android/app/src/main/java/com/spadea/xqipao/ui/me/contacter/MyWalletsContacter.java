package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class MyWalletsContacter {

    public interface View extends IView<Activity> {

        void setBalanceMoney(String money);

    }


    public interface IMyWalletsPre extends IPresenter {
        void getBalance();
    }
}

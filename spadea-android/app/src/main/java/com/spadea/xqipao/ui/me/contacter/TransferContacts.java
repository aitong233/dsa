package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class TransferContacts {

    public interface View extends IView<Activity> {
        void userTransferSuccess();
    }

    public interface ITransferPre extends IPresenter {
        void userTransfer(String userId, String gold);
        void imTransfer(String im, String gold);
    }

}

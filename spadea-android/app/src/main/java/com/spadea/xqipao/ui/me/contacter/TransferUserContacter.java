package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.qpyy.libcommon.bean.TransferUserModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class TransferUserContacter {


    public interface View extends IView<Activity> {
        void setTransferUser(TransferUserModel transferUser);
    }


    public interface ITransferUserPre extends IPresenter {
        void getTransferUser(String userCode);
    }
}

package com.spadea.xqipao.ui.h5;

import android.app.Activity;

import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class H5Contacts {


    public interface View extends IView<Activity> {
        void serviceUserSuccess(String uin);
    }

    public interface IH5Pre extends IPresenter {
        void serviceUser();
    }
}
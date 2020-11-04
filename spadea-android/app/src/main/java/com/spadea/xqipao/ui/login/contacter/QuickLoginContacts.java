package com.spadea.xqipao.ui.login.contacter;

import android.app.Activity;

import com.qpyy.libcommon.bean.UserBean;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class QuickLoginContacts {


    public interface View extends IView<Activity> {
        void loginSuccess(UserBean userBean);
        void go2OtherLogin();
        void go2Main();
    }

    public interface IQuickLoginPre extends IPresenter {
        void oauthLogin(String netease_token, String access_token, int type);
    }
}
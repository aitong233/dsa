package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public class LogoutAccountValidationContacts {

    public interface View extends IView<Activity> {
        void sendCodeSuccess();

        void logoutReasonResult();
    }

    public interface ILogoutAccountValidationPre extends IPresenter {
        void sendCode(String phoneNumber, int type);
        void logoutReason(String token, String mobile, String reason, String code);
    }
}

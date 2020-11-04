package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.data.LogoutReasonModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public class LogoutAccountContacts {
    public interface View extends IView<Activity> {
        void setlogoutStatus(LogoutReasonModel logoutReasonModel);
    }

    public interface ILogoutAccountPre extends IPresenter {
        void getlogoutStatus(String token, String mobile);
    }
}

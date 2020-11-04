package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.data.NobilityInfo;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class JueRenewContacts {

    public interface View extends IView<Activity> {
        void userNobilityInfoSuccess(NobilityInfo data);

        void renewNobilitySuccess();
    }

    public interface IJueRenewPre extends IPresenter {
        void userNobilityInfo();

        void renewNobility(String day);
    }

}

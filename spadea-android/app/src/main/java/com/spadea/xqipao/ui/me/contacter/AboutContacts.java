package com.spadea.xqipao.ui.me.contacter;


import android.app.Activity;

import com.spadea.xqipao.data.AppUpdateModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class AboutContacts {


    public interface View extends IView<Activity> {
        void appUpdate(AppUpdateModel appUpdateModel);
    }

    public interface IAboutPre extends IPresenter {
        void appUpdate();
    }
}

package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class SecondLevelPasswordContacter {

    public interface View extends IView<Activity> {
        void sendCodeSuccess();

        void settingPasswordSuess();
    }

    public interface ISecondLevelPre extends IPresenter {
        void sendCode(String phoneNumber, int type);

        void settingPassword(String phone, String password, String code);
    }


}

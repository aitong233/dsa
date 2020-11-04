package com.spadea.xqipao.ui.login.contacter;

import android.app.Activity;

import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class LoginContacter {


    public interface View extends IView<Activity> {
        void sendCodeSuccess(String phoneNumber);
    }

    public interface ILoginPre extends IPresenter {
        void sendCode(String phoneNumber, int type);

        void login(String mobile, String password, String code, int type);

        void thirdPartyLogin(String openId, int three_party, String nickname, String head_pic);

            void oauthLogin(String netease_token, String access_token, int type);

    }


}

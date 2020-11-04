package com.qpyy.module_main.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.libcommon.bean.UserBean;

public final class PasswordLoginContacts {


    public interface View extends IView<Activity> {
        void loginSuccess(UserBean userBean);

        void sendCodeSuccess();
    }

    public interface IPasswordLoginPre extends IPresenter {
        void sendCode(String phoneNumber, int type);

        void login(String mobile, String password, String code, int type);

        void thirdPartyLogin(String openId, int three_party, String nickname, String head_pic);

        void oauthLogin(String netEaseToken, String access_token, int type);
    }
}
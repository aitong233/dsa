package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.data.UserInfoDataModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class UserInfoxContacts {

    public interface View extends IView<Activity> {
        void userInfoDataSuccess(UserInfoDataModel data);

        void followUserSuccess();
    }

    public interface IUserInfoxPre extends IPresenter {
        void userInfoData(String userId, String emchatUsername);

        void followUser(String userId, int type);
    }
}

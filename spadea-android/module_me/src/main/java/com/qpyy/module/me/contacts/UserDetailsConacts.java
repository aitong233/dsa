package com.qpyy.module.me.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module.me.bean.UserHomeResp;

public final class UserDetailsConacts {


    public interface View extends IView<Activity> {
        void setUserDetails(UserHomeResp data);

        void onFail();

        void followSuccess(String type);

        void addBlackUserSuccess();
    }


    public interface IUserDetailsPre extends IPresenter {
        void getUserDetails(String userId,String emchatUsername);

        void follow(String userId, String type);

        void addBlackUser(String blackId, int type);
    }

}

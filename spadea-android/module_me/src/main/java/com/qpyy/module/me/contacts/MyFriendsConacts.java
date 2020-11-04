package com.qpyy.module.me.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module.me.bean.FriendBean;

import java.util.List;

public final class MyFriendsConacts {

    public interface View extends IView<Activity> {
        void setData(int page, List<FriendBean> data);

        void onComplete();
    }

    public interface IMyFriendsPre extends IPresenter {
        void friendList(int page);

        void followList(int page);

        void fansList(int page);
    }

}

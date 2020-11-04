package com.qpyy.module.me.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module.me.bean.SearchFriendResp;

public final class SearchFriendConacts {


    public interface View extends IView<Activity> {
        void setData(SearchFriendResp resp);
    }


    public interface ISearchFriendPre extends IPresenter {
        void search(int type,String keyword);
        void searchFans(String keyword);
        void searchFriend(String keyword);
        void searchFollow(String keyword);
    }
}

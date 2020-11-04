package com.spadea.xqipao.ui.home.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.SearchUserInfo;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class SearchUserContacts {


    public interface View extends IView<FragmentActivity> {
        void searchUserSuccess(List<SearchUserInfo> data);
    }

    public interface ISearchUserPre extends IPresenter {
        void searchUser(String keyword);
    }
}

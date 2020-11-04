package com.spadea.xqipao.ui.home.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.SearchRoomInfo;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class SearchRoomContacts {


    public interface View extends IView<FragmentActivity> {
        void searchRoomSuccess(List<SearchRoomInfo> data);
    }

    public interface ISearchRoomPre extends IPresenter {
        void searchRoom(String keyword);
    }
}

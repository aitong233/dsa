package com.qpyy.module.index.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module.index.bean.RecordSection;
import com.qpyy.module.index.bean.SearchResp;

import java.util.List;

public final class SearchContacts {

    public interface View extends IView<Activity> {
        void setSearchHistory(List<String> data);

        void setSearch(SearchResp data);

        void setFuzzyQuery(List<RecordSection> data);

        void followUserSuccess(int type, int postion);


    }

    public interface ISearchPre extends IPresenter {
        void getSearchHistory();

        void saveSearchHistory(String keyWord);

        void deleteSearchHistory();

        void search(String keyWord);

        void fuzzyQuery(String keyWord);

        void followUser(String userId, int type, int postion);

    }
}

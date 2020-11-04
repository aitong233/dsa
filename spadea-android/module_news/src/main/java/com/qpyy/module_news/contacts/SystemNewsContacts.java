package com.qpyy.module_news.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module_news.bean.NewsListBean;

import java.util.List;

public final class SystemNewsContacts {


    public interface View extends IView<Activity> {
        void newsList(List<NewsListBean> listBeans);
        void loadComplete();
        void serviceSuccess(String data);
    }

    public interface ISystemNewsPre extends IPresenter {
        void getList(int page);
        void serviceUser();
    }
}
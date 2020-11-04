package com.qpyy.room.contacts;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.room.bean.NewsListBean;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.contacts
 * 创建人 黄强
 * 创建时间 2020/8/10 10:59
 * 描述 describe
 */
public class SystemNewsContacts {

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

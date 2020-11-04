package com.spadea.xqipao.ui.home.contacts;

import android.app.Activity;

import com.spadea.xqipao.data.AppUpdateModel;
import com.spadea.xqipao.data.NewsModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class HomeContacts {


    public interface View extends IView<Activity> {


        void appUpdate(AppUpdateModel appUpdateModel);

        void setRandomHotRoom(String roomId);

        void userNewsSuccess(NewsModel newsModel);
    }

    public interface IHomePre extends IPresenter {
        void initData();

        void loginIm();

        void appUpdate();

        void randomHotRoom();

        void userNews();

        void signSwitch();
    }
}

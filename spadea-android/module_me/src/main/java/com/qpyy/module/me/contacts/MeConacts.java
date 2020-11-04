package com.qpyy.module.me.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module.me.bean.MyInfoResp;
import com.qpyy.module.me.bean.NameAuthResult;

public final class MeConacts {

    public interface View extends IView<Activity> {
        void myInfoSuccess(MyInfoResp data);

        void serviceSuccess(String data);
    }

    public interface IMePre extends IPresenter {
        void getMyInfo();

        void serviceUser();

        void getNameAuthResult(int type);

        void getGuildInfo();
    }

}

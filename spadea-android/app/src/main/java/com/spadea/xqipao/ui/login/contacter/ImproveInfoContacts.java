package com.spadea.xqipao.ui.login.contacter;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module.me.bean.UserFillResp;

public final class ImproveInfoContacts {


    public interface View extends IView<Activity> {
        void success(UserFillResp s);
    }

    public interface IImproveInfoPre extends IPresenter {
        void fill(String user_no, String nickname, String sex);
    }
}
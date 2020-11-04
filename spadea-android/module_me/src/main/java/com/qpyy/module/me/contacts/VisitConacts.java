package com.qpyy.module.me.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module.me.bean.ComeUserResp;

import java.util.List;

public final class VisitConacts {

    public interface View extends IView<Activity> {

        void setUserVisit(List<ComeUserResp> data);

        void finishRefresh();
    }


    public interface IVisitPre extends IPresenter {
        void userVisit(int page);
    }

}

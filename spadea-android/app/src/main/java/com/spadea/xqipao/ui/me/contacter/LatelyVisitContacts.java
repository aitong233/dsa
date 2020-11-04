package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.data.LatelyVisitInfo;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class LatelyVisitContacts {


    public interface View extends IView<Activity> {
        void setComeUserData(List<LatelyVisitInfo> data);

        void comeUserComplete();
    }

    public interface ILatelyVisitPre extends IPresenter {
        void comeUser(int pager);
    }
}

package com.spadea.xqipao.ui.me.contacter;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.NobilityInfo;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class JueContacts {


    public interface View extends IView<FragmentActivity> {
        void userNobilityInfoSuccess(NobilityInfo data);
    }

    public interface IJuyePre extends IPresenter {
        void userNobilityInfo();
    }
}

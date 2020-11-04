package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.data.NobilityInfo;
import com.spadea.xqipao.data.NobilityModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class JueUpgradeContacts {

    public interface View extends IView<Activity> {
        void nobilitySuccess(List<NobilityModel> data);

        void userNobilityInfoSuccess(NobilityInfo data);

        void buyNobilitySuccess();
    }


    public interface IJueUpgradePre extends IPresenter {
        void nobility();

        void userNobilityInfo();

        void buyNobility(String nobilityId);
    }
}

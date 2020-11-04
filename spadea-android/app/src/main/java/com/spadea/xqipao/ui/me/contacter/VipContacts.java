package com.spadea.xqipao.ui.me.contacter;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.VipInfo;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class VipContacts {


    public interface View extends IView<FragmentActivity> {
        void vipInfoSuccess(VipInfo vipInfo);
    }

    public interface IVipPre extends IPresenter {
        void vipInfo();
    }

}

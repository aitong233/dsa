package com.spadea.xqipao.ui.room.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class HoldingWheatContacts {


    public interface View extends IView<FragmentActivity> {

    }

    public interface IHoldingWheatPre extends IPresenter {
        void putOnWheat(String roomId, String userId);
    }

}

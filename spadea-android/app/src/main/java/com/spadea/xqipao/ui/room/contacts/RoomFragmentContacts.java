package com.spadea.xqipao.ui.room.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.SignSwitchModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class RoomFragmentContacts {


    public interface View extends IView<FragmentActivity> {
        void indexSwitch(SignSwitchModel.Children children);
    }

    public interface IRoomFragmentPre extends IPresenter {
        void indexSwitch();
    }
}
package com.spadea.xqipao.ui.me.contacter;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.FriendModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class FriendContacts {


    public interface View extends IView<FragmentActivity> {
        void setData(int page,List<FriendModel> data);
        void onComplete();
    }

    public interface IFriendPre extends IPresenter {
        void friendList(int page);

        void followList(int page);

        void fansList(int page);
    }
}

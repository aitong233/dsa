package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.data.FriendModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class SelectFriendContacts {


    public interface View extends IView<Activity> {
        void setData(int page,List<FriendModel> data);

        void buyShopSuccess();

        void finishLoading();

    }

    public interface ISelectFriendPre extends IPresenter {
        void friendList(int page);

        void buyShop(String friendId, String productId, String priceId);
    }

}

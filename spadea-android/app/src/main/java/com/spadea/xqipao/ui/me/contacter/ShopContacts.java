package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.data.CategoriesModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class ShopContacts {

    public interface View extends IView<Activity> {
        void setBalanceMoney(String money);

        void categoriesSuccess(List<CategoriesModel> data);
    }

    public interface IShopPre extends IPresenter {
        void getBalance();

        void categories();
    }
}

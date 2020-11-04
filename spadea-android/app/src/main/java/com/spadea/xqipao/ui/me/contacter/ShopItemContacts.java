package com.spadea.xqipao.ui.me.contacter;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.ProductsModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class ShopItemContacts {


    public interface View extends IView<FragmentActivity> {
        void productsSuccess(List<ProductsModel> data);

        void productsComplete();

        void buyShopSuccess();
    }

    public interface IShopItemPre extends IPresenter {
        void products(String categoryId);

        void buyShop(String productId, String priceId);
    }


}

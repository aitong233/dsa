package com.qpyy.room.contacts;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.room.bean.ProductsModel;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.contacts
 * 创建人 黄强
 * 创建时间 2020/8/11 10:48
 * 描述 describe
 */
public class ShopItemContacts {
    public interface View extends IView<Activity> {
        void productsSuccess(List<ProductsModel> data);

        void productsComplete();

        void buyShopSuccess();
    }

    public interface IShopItemPre extends IPresenter {
        void products(String categoryId);

        void buyShop(String productId, String priceId);

    }

}

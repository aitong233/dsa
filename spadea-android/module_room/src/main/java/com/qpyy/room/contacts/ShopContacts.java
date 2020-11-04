package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.room.bean.CategoriesModel;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.contacts
 * 创建人 黄强
 * 创建时间 2020/8/11 10:18
 * 描述 describe
 */
public class ShopContacts {

    public interface View extends IView<Activity> {
        void setBalanceMoney(String money);

        void categoriesSuccess(List<CategoriesModel> data);
    }

    public interface IShopPre extends IPresenter {
        void getBalance();

        void categories();
    }
}

package com.qpyy.room.fragment;

import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.room.bean.ProductsModel;
import com.qpyy.room.contacts.ShopItemContacts;
import com.qpyy.room.dialog.ShopDialog;
import com.qpyy.room.presenter.ShopItemPresenter;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.fragment
 * 创建人 黄强
 * 创建时间 2020/8/11 10:48
 * 描述 describe
 */
public class ShopFragment extends BaseMvpFragment<ShopItemPresenter> implements ShopItemContacts.View{
    @Override
    protected ShopItemPresenter bindPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void productsSuccess(List<ProductsModel> data) {

    }

    @Override
    public void productsComplete() {

    }

    @Override
    public void buyShopSuccess() {

    }
}

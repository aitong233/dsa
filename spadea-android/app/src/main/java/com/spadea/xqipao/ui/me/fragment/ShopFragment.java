package com.spadea.xqipao.ui.me.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.ProductsModel;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.me.contacter.ShopItemContacts;
import com.spadea.xqipao.ui.me.presenter.ShopItemPresenter;
import com.spadea.xqipao.utils.dialog.BalanceNoFootDialog;
import com.spadea.xqipao.utils.dialog.ShopDialog;
import com.spadea.xqipao.utils.view.CommonEmptyView;
import com.spadea.xqipao.ui.me.activity.ShopActivity;
import com.spadea.xqipao.ui.me.adapter.ShopAdapter;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

public class ShopFragment extends BaseFragment<ShopItemPresenter> implements ShopItemContacts.View, ShopDialog.ShopOnClickListener {


    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.srl)
    SmartRefreshLayout smartRefreshLayout;

    private String id = "";
    private String title = "";
    private ShopAdapter shopAdapter;
    private ShopDialog shopDialog;

    public static Fragment newInstance(String title, String id) {
        ShopFragment shopFragment = new ShopFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("title", title);
        shopFragment.setArguments(bundle);
        return shopFragment;
    }


    @Override
    protected ShopItemPresenter bindPresenter() {
        return new ShopItemPresenter(this, mContext);
    }

    @Override
    protected void initData() {
        CommonEmptyView commonEmptyView = new CommonEmptyView(mContext);
        id = getArguments().getString("id", "");
        title = getArguments().getString("title", "");
        recyclerView.setAdapter(shopAdapter = new ShopAdapter(id));
        shopAdapter.setEmptyView(commonEmptyView);
        MvpPre.products(id);
    }

    @Override
    protected void initView(View rootView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void initListener() {
        super.initListener();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                MvpPre.products(id);
            }
        });
        shopAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ProductsModel item = shopAdapter.getItem(position);
                showShop(item, id);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }


    @Override
    public void productsSuccess(List<ProductsModel> data) {
        shopAdapter.setNewData(data);
    }

    @Override
    public void productsComplete() {
        smartRefreshLayout.finishRefresh();
    }

    @Override
    public void buyShopSuccess() {
        ToastUtils.showShort("购买成功");
        ((ShopActivity) getActivity()).getBalance();
    }

    private void showShop(ProductsModel item, String id) {
        if (shopDialog == null) {
            shopDialog = new ShopDialog(mContext);
        }
        shopDialog.setShopOnClickListener(this);
        shopDialog.setData(item, id);
        shopDialog.show();
    }

    @Override
    public void give(ProductsModel productsModel, String priceId, String price, String day) {
        String money = ((ShopActivity) getActivity()).getMoney();
        if (Double.valueOf(money) < Double.valueOf(price)) {
            showBalanceNoFoot();
        } else {
            ARouter.getInstance().build(ARouters.ME_SELECTFRIEND)
                    .withString("productId", productsModel.getId())
                    .withString("priceId", priceId)
                    .withString("title", productsModel.getTitle())
                    .withString("day",day)
                    .navigation();
        }
    }

    @Override
    public void payment(ProductsModel productsModel, String priceId, String price) {
        String money = ((ShopActivity) getActivity()).getMoney();
        if (Double.valueOf(money) < Double.valueOf(price)) {
            showBalanceNoFoot();
        } else {
            MvpPre.buyShop(productsModel.getId(), priceId);
        }
    }


    private BalanceNoFootDialog balanceNoFootDialog;

    private void showBalanceNoFoot() {
        if (balanceNoFootDialog == null) {
            balanceNoFootDialog = new BalanceNoFootDialog(mContext);
            balanceNoFootDialog.setBalanceNoFootOnClickListener(new BalanceNoFootDialog.BalanceNoFootOnClickListener() {
                @Override
                public void toRecharge() {
                    ARouter.getInstance().build(ARouters.ME_BALANCE).navigation();
                }
            });
        }
        balanceNoFootDialog.show();
    }
}

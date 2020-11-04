package com.qpyy.room.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.SlidingTabLayout;
import com.qpyy.libcommon.adapter.MyFragmentPagerAdapter;
import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.bean.CategoriesModel;
import com.qpyy.room.contacts.ShopContacts;
import com.qpyy.room.fragment.ShopFragment;
import com.qpyy.room.presenter.ShopPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.activity
 * 创建人 黄强
 * 创建时间 2020/8/11 10:16
 * 描述 describe
 */
public class ShopActivity extends BaseMvpActivity<ShopPresenter> implements ShopContacts.View {

    @BindView(R2.id.iv_bg)
    ImageView ivBg;
    @BindView(R2.id.iv_back)
    ImageView ivBack;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.tv_right)
    TextView tvRight;
    @BindView(R2.id.stl_shop)
    SlidingTabLayout stlShop;
    @BindView(R2.id.vp_shop)
    ViewPager vpShop;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    @Override
    protected ShopPresenter bindPresenter() {
        return new ShopPresenter(this, this);
    }

    @Override
    protected void initData() {
        MvpPre.categories();
    }

    @Override
    protected void initView() {
        super.initView();
        tvTitle.setText("个性装扮");
        tvRight.setText("我的背包");
        tvRight.setVisibility(View.VISIBLE);
        initListener();
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ARouteConstants.ME_KNAPSACK).navigation();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBalance();
    }

    public void getBalance() {
        MvpPre.getBalance();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_activity_shop;
    }

    @Override
    public void setBalanceMoney(String money) {
    }

    @Override
    public void categoriesSuccess(List<CategoriesModel> data) {
//        List<Fragment> fragmentList = new ArrayList<>();
//        String[] title = new String[data.size()];
//        for (int i = 0; i < data.size(); i++) {
//            CategoriesModel categoriesModel = data.get(i);
//            title[i] = categoriesModel.getTitle();
//            fragmentList.add(ShopFragment.newInstance(categoriesModel.getTitle(), categoriesModel.getId()));
//        }
//        vpShop.setAdapter(myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragmentList, getSupportFragmentManager()));
//        stlShop.setViewPager(vpShop, title);
//        stlShop.setCurrentTab(0);
    }


    @OnClick(R2.id.iv_back)
    public void onViewClicked() {
        finish();
    }


}

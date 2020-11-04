package com.spadea.xqipao.ui.me.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.spadea.yuyin.R;
import com.spadea.xqipao.data.UserInfoDataModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.utils.view.CommonEmptyView;
import com.spadea.xqipao.ui.me.adapter.GiftWallAdapter;

import butterknife.BindView;

public class GiftWallFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    private GiftWallAdapter giftWallAdapter;


    public static Fragment newInstance(UserInfoDataModel data) {
        GiftWallFragment giftWallFragment = new GiftWallFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        giftWallFragment.setArguments(bundle);
        return giftWallFragment;
    }

    @Override
    protected IPresenter bindPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        UserInfoDataModel userInfoDataModel = (UserInfoDataModel) getArguments().getSerializable("data");
        if (userInfoDataModel != null) {
            giftWallAdapter.setNewData(userInfoDataModel.getGift_list());
        }
    }

    @Override
    protected void initView(View rootView) {
        CommonEmptyView commonEmptyView = new CommonEmptyView(mContext);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        recyclerView.setAdapter(giftWallAdapter = new GiftWallAdapter());
        giftWallAdapter.setHeaderView(LayoutInflater.from(mContext).inflate(R.layout.header_gift_wall,null));
        giftWallAdapter.setEmptyView(commonEmptyView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gift_wall;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }


}

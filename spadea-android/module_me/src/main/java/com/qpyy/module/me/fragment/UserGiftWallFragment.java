package com.qpyy.module.me.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;
import com.qpyy.module.me.adapter.UserGiftWallAdapter;
import com.qpyy.module.me.bean.GiftBean;
import com.qpyy.module.me.contacts.UserGiftWallConacts;
import com.qpyy.module.me.presenter.UserGiftWallPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

/**
 * 礼物墙
 */
public class UserGiftWallFragment extends BaseMvpFragment<UserGiftWallPresenter> implements UserGiftWallConacts.View {


    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;

    private String userId;
    private UserGiftWallAdapter mUserGiftWallAdapter;


    public static UserGiftWallFragment newInstance(String userId) {
        UserGiftWallFragment fragment = new UserGiftWallFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userId", userId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected UserGiftWallPresenter bindPresenter() {
        return new UserGiftWallPresenter(this, getContext());
    }

    @Override
    protected void initData() {
        userId = getArguments().getString("userId");
        recyclerView.setAdapter(mUserGiftWallAdapter = new UserGiftWallAdapter());
        MvpPre.giftWall(userId);
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                MvpPre.giftWall(userId);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.me_fagment_user_gift_wall;
    }


    @Override
    public void setGiftWall(List<GiftBean> data) {
        mUserGiftWallAdapter.setNewData(data);
    }

    @Override
    public void finishRefresh() {
        mSmartRefreshLayout.finishRefresh();
    }
}

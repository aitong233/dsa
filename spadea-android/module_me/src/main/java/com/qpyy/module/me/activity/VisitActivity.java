package com.qpyy.module.me.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;
import com.qpyy.module.me.adapter.VisitAdapter;
import com.qpyy.module.me.bean.ComeUserResp;
import com.qpyy.module.me.contacts.VisitConacts;
import com.qpyy.module.me.presenter.VisitPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouteConstants.ME_VISIT, name = "最近来访")
public class VisitActivity extends BaseMvpActivity<VisitPresenter> implements VisitConacts.View {

    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;


    private int page = 1;
    private VisitAdapter mVisitAdapter;

    @Override
    protected VisitPresenter bindPresenter() {
        return new VisitPresenter(this, this);
    }

    @Override
    protected void initData() {
        tvTitle.setText("最近来访");
        MvpPre.userVisit(page);
    }

    @Override
    protected void initView() {
        super.initView();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mVisitAdapter = new VisitAdapter());
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                MvpPre.userVisit(page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                MvpPre.userVisit(page);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.me_activity_visit;
    }


    @OnClick({R2.id.iv_back})
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            this.finish();
        }
    }

    @Override
    public void setUserVisit(List<ComeUserResp> data) {
        if (page == 1) {
            mVisitAdapter.setNewData(data);
        } else {
            mVisitAdapter.addData(data);
        }
        if (data == null || data.size() == 0) {
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    @Override
    public void finishRefresh() {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }
}

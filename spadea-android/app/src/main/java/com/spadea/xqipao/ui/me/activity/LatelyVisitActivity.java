package com.spadea.xqipao.ui.me.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.LatelyVisitInfo;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.LatelyVisitContacts;
import com.spadea.xqipao.ui.me.presenter.LatelyVisitPresenter;
import com.spadea.xqipao.utils.view.CommonEmptyView;
import com.spadea.xqipao.ui.me.adapter.LatelyVisitAdapter;
import com.spadea.yuyin.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouters.ME_LATELYVISIT, name = "最近来访")
public class LatelyVisitActivity extends BaseActivity<LatelyVisitPresenter> implements LatelyVisitContacts.View {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;

    private int pager = 1;
    private LatelyVisitAdapter latelyVisitAdapter;

    public LatelyVisitActivity() {
        super(R.layout.activity_latelyvisit);
    }

    @Override
    protected void initData() {
        MvpPre.comeUser(pager);
    }

    @Override
    protected void initView() {
        tvTitle.setText("最近来访");
        CommonEmptyView commonEmptyView = new CommonEmptyView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(latelyVisitAdapter = new LatelyVisitAdapter());
        latelyVisitAdapter.setEmptyView(commonEmptyView);
    }

    @Override
    protected void setListener() {
        srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pager = 1;
                MvpPre.comeUser(pager);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pager++;
                MvpPre.comeUser(pager);
            }
        });
        latelyVisitAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LatelyVisitInfo item = latelyVisitAdapter.getItem(position);
                ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", item.getUser_id()).navigation();
            }
        });
    }

    @Override
    protected LatelyVisitPresenter bindPresenter() {
        return new LatelyVisitPresenter(this, this);
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }


    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void setComeUserData(List<LatelyVisitInfo> data) {
        if (data.size() == 0) {
            srl.finishLoadMoreWithNoMoreData();
        } else {
            if (pager == 1) {
                latelyVisitAdapter.setNewData(data);
            } else {
                latelyVisitAdapter.addData(data);
            }
        }
    }

    @Override
    public void comeUserComplete() {
        srl.finishRefresh();
        srl.finishLoadMore();
    }
}

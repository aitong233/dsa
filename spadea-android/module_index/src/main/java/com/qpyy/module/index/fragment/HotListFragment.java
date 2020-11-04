package com.qpyy.module.index.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.module.index.R;
import com.qpyy.module.index.R2;
import com.qpyy.module.index.adapter.HotAdapter;
import com.qpyy.module.index.bean.RoomModel;
import com.qpyy.module.index.contacts.HotListContacts;
import com.qpyy.module.index.event.BannerRefreshEvent;
import com.qpyy.module.index.presenter.HotListPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;


public class HotListFragment extends BaseMvpFragment<HotListPresenter> implements HotListContacts.View {
    @BindView(R2.id.recycle_view)
    RecyclerView mRecycleView;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    private String type;
    private HotAdapter mAdapter;

    public static HotListFragment newInstance(String type) {

        Bundle args = new Bundle();
        args.putString("type", type);
        HotListFragment fragment = new HotListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        type = arguments.getString("type");
    }

    @Override
    protected HotListPresenter bindPresenter() {
        return new HotListPresenter(this, getActivity());
    }

    @Override
    protected void initData() {
        MvpPre.getRoomList(type);
    }

    @Override
    protected void initView() {
        mRecycleView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                EventBus.getDefault().post(new BannerRefreshEvent());
                MvpPre.getRoomList(type);
            }
        });
        mAdapter = new HotAdapter();
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRecycleView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RoomModel item = mAdapter.getItem(position);
                if (item != null) {
                    ARouter.getInstance().build(ARouteConstants.LIVE_ROOM).withString("roomId", item.getRoom_id()).navigation();
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.index_fragement_hot_list;
    }

    @Override
    public void roomList(List<RoomModel> data) {
        mAdapter.setNewData(data);
    }

    @Override
    public void finishRefreshLoadMore() {
        mSmartRefreshLayout.finishRefresh();
    }
}
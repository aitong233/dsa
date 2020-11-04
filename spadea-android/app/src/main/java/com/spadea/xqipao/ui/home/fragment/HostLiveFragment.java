package com.spadea.xqipao.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.xqipao.data.RoomModel;
import com.spadea.xqipao.ui.home.presenter.HostPresenter;
import com.spadea.xqipao.ui.room.activity.LiveRoomActivity;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.home.adapter.HostAdapter;
import com.spadea.xqipao.ui.home.contacts.HostContacts;
import com.spadea.yuyin.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

public class HostLiveFragment extends BaseFragment<HostPresenter> implements HostContacts.View {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private HostAdapter hostAdapter;

    public static HostLiveFragment newInstance(String title) {
        HostLiveFragment hostFragment = new HostLiveFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        hostFragment.setArguments(bundle);
        return hostFragment;
    }

    @Override
    protected HostPresenter bindPresenter() {
        return new HostPresenter(this, mContext);
    }

    @Override
    protected void initData() {
        MvpPre.getHostRoom();
    }

    @Override
    protected void initView(View rootView) {
        recyclerview.setLayoutManager(new GridLayoutManager(mContext, 2));
        recyclerview.setAdapter(hostAdapter = new HostAdapter());
    }

    @Override
    protected void initListener() {
        super.initListener();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                MvpPre.getHostRoom();
            }
        });
        hostAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RoomModel item = hostAdapter.getItem(position);
                LiveRoomActivity.start(getActivity(), item.getRoom_id());
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_host;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @Override
    public void hostRoomSuccess(List<RoomModel> data) {
        hostAdapter.setNewData(data);
    }

    @Override
    public void hostRoomComplete() {
        smartRefreshLayout.finishRefresh();
    }
}

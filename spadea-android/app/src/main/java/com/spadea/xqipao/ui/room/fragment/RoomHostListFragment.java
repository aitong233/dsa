package com.spadea.xqipao.ui.room.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.yuyin.R;
import com.spadea.xqipao.data.AnchorRankingItemBean;
import com.spadea.xqipao.data.AnchorRankingListResp;
import com.spadea.xqipao.data.even.AnchorListRefreshEvent;
import com.spadea.xqipao.ui.room.presenter.RoomHostListPresenter;
import com.spadea.xqipao.ui.base.view.BaseDialogFragment;
import com.spadea.xqipao.ui.room.adapter.RoomHostAdapter;
import com.spadea.xqipao.ui.room.contacts.RoomHostListContact;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.fragment
 * 创建人 王欧
 * 创建时间 2020/4/2 4:53 PM
 * 描述 describe
 */
public class RoomHostListFragment extends BaseDialogFragment<RoomHostListPresenter> implements RoomHostListContact.View, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private RoomHostAdapter mAdapter;

    private int type;

    private String roomId;

    public static RoomHostListFragment newInstance(int type, String roomId) {
        RoomHostListFragment fragment = new RoomHostListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putString("roomId", roomId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefresh(AnchorListRefreshEvent event) {
        if (event.type == type) {
            return;
        }
        MvpPre.getAnchorRankingList(roomId, String.valueOf(type));
    }

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new RoomHostAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnItemClickListener(this);
        MvpPre.getAnchorRankingList(roomId, String.valueOf(type));
    }

    @Override
    protected void initView(View rootView) {
        if (getArguments() != null) {
            type = getArguments().getInt("type");
            roomId = getArguments().getString("roomId");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragement_room_host_list;
    }

    @Override
    protected RoomHostListPresenter bindPresenter() {
        return new RoomHostListPresenter(this, mContext);
    }

    @Override
    public void anchorRankingList(AnchorRankingListResp resp) {
        mAdapter.setNewData(resp.getList());
    }

    @Override
    public void followUserSuccess(int position, int type) {
        AnchorRankingItemBean item = mAdapter.getItem(position);
        if (item == null) {
            return;
        }
        item.setIs_follow(type == 1 ? 1 : 0);
        mAdapter.setData(position, item);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        EventBus.getDefault().post(new AnchorListRefreshEvent(type));
        AnchorRankingItemBean item = mAdapter.getItem(position);
        if (item == null) {
            return;
        }
        MvpPre.followUser(position, item.getUser_id(), item.getIs_follow() == 1 ? 2 : 1);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", mAdapter.getItem(position).getUser_id()).navigation();
    }
}

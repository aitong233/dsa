package com.spadea.xqipao.ui.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.RoomModel;
import com.spadea.xqipao.ui.home.presenter.AllLivePresenter;
import com.spadea.xqipao.ui.room.activity.LiveRoomActivity;
import com.spadea.xqipao.utils.dialog.HomeRoomDialog;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.home.adapter.AllLiveAdapter;
import com.spadea.xqipao.ui.home.contacts.AllLiveContacts;
import com.spadea.yuyin.R;
import com.spadea.yuyin.ui.fragment0.roomdetail.reprot.ReportActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

public class AllLiveFragment extends BaseFragment<AllLivePresenter> implements AllLiveContacts.View {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;


    private HomeRoomDialog homeRoomDialog;
    private AllLiveAdapter allLiveAdapter;

    public static AllLiveFragment newInstance(String title) {
        AllLiveFragment allLiveFragment = new AllLiveFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        allLiveFragment.setArguments(bundle);
        return allLiveFragment;
    }


    @Override
    protected AllLivePresenter bindPresenter() {
        return new AllLivePresenter(this, mContext);
    }

    @Override
    protected void initData() {
        MvpPre.getAllRoom();
    }

    @Override
    protected void initListener() {
        super.initListener();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                MvpPre.getAllRoom();
            }
        });
        allLiveAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RoomModel item = allLiveAdapter.getItem(position);
                switch (view.getId()) {
                    case R.id.iv_more:
                        showMore(item);
                        break;
                    case R.id.riv_head_img:
                        ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", MyApplication.getInstance().getUser().getUser_id()).navigation();
                        break;
                    case R.id.tv_together:
                        LiveRoomActivity.start(getActivity(), item.getRoom_id());
//                        LiveRoomActivity.start(getActivity(), item.getRoom_id());
                        break;
                }
            }
        });
        allLiveAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RoomModel item = allLiveAdapter.getItem(position);
//                RoomActivity.start(getActivity(), item.getRoom_id());
                LiveRoomActivity.start(getActivity(), item.getRoom_id());
            }
        });

    }

    @Override
    protected void initView(View rootView) {
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(allLiveAdapter = new AllLiveAdapter());
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_live;
    }



    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }


    @Override
    public void setAllRoomData(List<RoomModel> data) {
        allLiveAdapter.setNewData(data);
    }

    @Override
    public void allRoomComplete() {
        smartRefreshLayout.finishRefresh();
    }


    private void showMore(RoomModel item) {
        if (homeRoomDialog == null) {
            homeRoomDialog = new HomeRoomDialog(mContext);
            homeRoomDialog.setmOnClickListener(new HomeRoomDialog.OnClickListener() {
                @Override
                public void collection(String roomId, String state) {
                    if (state.equals("0")) {
                        MvpPre.collection(roomId);
                    } else {
                        MvpPre.cancelCollection(roomId);
                    }
                }

                @Override
                public void report(String roomId) {
                    Intent intent = new Intent(mContext, ReportActivity.class);
                    intent.putExtra("id", roomId);
                    startActivity(intent);
                }
            });
        }
        homeRoomDialog.setData(item.getRoom_id(), item.getFavorite());
        homeRoomDialog.show();
    }
}

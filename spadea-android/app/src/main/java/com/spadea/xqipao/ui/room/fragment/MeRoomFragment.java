package com.spadea.xqipao.ui.room.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.xqipao.data.ManageRoomModel;
import com.spadea.xqipao.data.MyManageRoomModel;
import com.spadea.xqipao.ui.room.presenter.MeRoomPresenter;
import com.spadea.xqipao.utils.view.CommonEmptyView;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.room.activity.LiveRoomActivity;
import com.spadea.xqipao.ui.room.adapter.MeRoomAdapter;
import com.spadea.xqipao.ui.room.contacts.MeRoomContacts;

import java.util.List;

import butterknife.BindView;

public class MeRoomFragment extends BaseFragment<MeRoomPresenter> implements MeRoomContacts.View, BaseQuickAdapter.OnItemLongClickListener {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.riv)
    RoundedImageView riv;
    @BindView(R.id.tv_room_name)
    TextView tvRoomName;
    @BindView(R.id.tv_identity)
    TextView tvIdentity;
    @BindView(R.id.tv_jost_num)
    TextView tvJostNum;
    @BindView(R.id.rl_me)
    RelativeLayout rlMe;


    private MeRoomAdapter mMeRoomAdapter;

    private int type = 0;
    private int page = 1;
    private CommonEmptyView commonEmptyView;

    public MeRoomFragment() {

    }

    public static MeRoomFragment newInstance(int type) {
        MeRoomFragment meRoomFragment = new MeRoomFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        meRoomFragment.setArguments(bundle);
        return meRoomFragment;
    }

    @Override
    protected MeRoomPresenter bindPresenter() {
        return new MeRoomPresenter(this, mContext);
    }

    @Override
    protected void initData() {
        commonEmptyView = new CommonEmptyView(mContext);
        type = getArguments().getInt("type", 0);
        recyclerview.setAdapter(mMeRoomAdapter = new MeRoomAdapter(type));
//        if (type != 0) {
        mMeRoomAdapter.setEmptyView(commonEmptyView);
//        }
//        mMeRoomAdapter.setOnItemLongClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (type == 0) {
            MvpPre.manageRoom(page);
        } else {
            MvpPre.collectRoom(page);
        }
    }

    @Override
    protected void initView(View rootView) {
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setFocusable(false);
    }


    @Override
    protected void initListener() {
        super.initListener();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                if (type == 0) {
                    MvpPre.manageRoom(page);
                } else {
                    MvpPre.collectRoom(page);
                }
            }
        });
        mMeRoomAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                if (type == 0) {
                    MvpPre.manageRoom(page);
                } else {
                    MvpPre.collectRoom(page);
                }
            }
        }, recyclerview);
        mMeRoomAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ManageRoomModel item = mMeRoomAdapter.getItem(position);
                if (item != null && !TextUtils.isEmpty(item.getRoom_id())) {
                    LiveRoomActivity.start(getActivity(), item.getRoom_id());
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me_room;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }


    @Override
    public void roomDataSuccess(List<ManageRoomModel> data) {
        if (data == null || data.size() == 0) {
            mMeRoomAdapter.loadMoreEnd();
        } else {
            if (page == 1) {
                mMeRoomAdapter.setNewData(data);
            } else {
                mMeRoomAdapter.addData(data);
            }
            mMeRoomAdapter.loadMoreComplete();
        }
    }

    @Override
    public void MyRoomDataSuccess(MyManageRoomModel manageRoomModels) {
        MyManageRoomModel.MyBean my = manageRoomModels.getMy();
        if (my == null) {
//            mMeRoomAdapter.setEmptyView(commonEmptyView);
            rlMe.setVisibility(View.GONE);
        } else {
            rlMe.setVisibility(View.VISIBLE);
            ImageLoader.loadHead(mContext, riv, my.getCover_picture());
            tvRoomName.setText(my.getRoom_name());
            tvJostNum.setText(my.getPopularity());
            tvIdentity.setText("房主");
            rlMe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LiveRoomActivity.start(getActivity(), my.getRoom_id());
                }
            });
        }
    }

    @Override
    public void roomDataComplete() {
        smartRefreshLayout.finishRefresh();
    }

    @Override
    public void roomManagerCanceled(int position) {
        mMeRoomAdapter.remove(position);
    }

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        ManageRoomModel model = mMeRoomAdapter.getItem(position);
        if (model != null && type == 0) {
            new AlertDialog.Builder(mContext).setMessage("取消管理员身份？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MvpPre.cancelRoomManager(model.getRoom_id(), position);
                        }
                    })
                    .setNegativeButton("暂不", null).create().show();
        }
        return true;
    }
}

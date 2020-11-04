package com.qpyy.room.fragment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.adapter.GuardMedalAdapter;
import com.qpyy.room.adapter.RoomGuardAdapter;
import com.qpyy.room.bean.ProtectedRankingItemBean;
import com.qpyy.room.bean.ProtectedRankingListResp;
import com.qpyy.room.contacts.RoomGuardListContact;
import com.qpyy.room.event.OpenGuardEvent;
import com.qpyy.room.presenter.RoomGuardListPresenter;
import com.qpyy.room.widget.GuardListHeadView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.fragment
 * 创建人 王欧
 * 创建时间 2020/4/2 2:30 PM
 * 描述 describe
 */
public class RoomGuardListDialogFragment extends BaseMvpDialogFragment<RoomGuardListPresenter> implements RoomGuardListContact.View, BaseQuickAdapter.OnItemClickListener {
    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.btn_action)
    Button mBtnAction;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.riv_avatar)
    RoundedImageView mRivAvatar;
    @BindView(R2.id.tv_name)
    TextView mTvName;
    @BindView(R2.id.tv_desc)
    TextView mTvDesc;
    @BindView(R2.id.rv_medal)
    RecyclerView mRvMedal;
    @BindView(R2.id.cl_self_info)
    ConstraintLayout mClSelfInfo;
    private RoomGuardAdapter mAdapter;

    private String roomId;

    private String myUserId;

    private boolean isHost;

    public static RoomGuardListDialogFragment newInstance(String roomId, boolean isHost) {
        RoomGuardListDialogFragment fragment = new RoomGuardListDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("roomId", roomId);
        bundle.putBoolean("isHost", isHost);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new RoomGuardAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(mRecyclerView);
        MvpPre.getProtectedRankingList(roomId);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        roomId = getArguments().getString("roomId");
        isHost = getArguments().getBoolean("isHost");
    }

    @Override
    protected void initView() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.room_fragement_dialog_room_guard_list;
    }

    @Override
    protected RoomGuardListPresenter bindPresenter() {
        return new RoomGuardListPresenter(this, getActivity());
    }

    @OnClick({R2.id.btn_action, R2.id.cl_self_info})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.btn_action) {
            EventBus.getDefault().post(new OpenGuardEvent());
        } else if (id == R.id.cl_self_info) {
            if (!TextUtils.isEmpty(myUserId)) {
                ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", myUserId).navigation();
            }
        }
    }

    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.room_bg_r15_0e0e0e));
        window.setGravity(Gravity.CENTER);
        int width = (int) (ScreenUtils.getScreenWidth() / 375.0 * 305);
        int height = (int) (width / 305.0 * 525);
        window.setLayout(width, height);
        setCancelable(true);
    }

    @Override
    public void protectedRankingList(ProtectedRankingListResp resp) {
        mTvTitle.setText(String.format("守护榜(%s)", resp.getTotal()));
        if (resp.getList() != null && resp.getList().size() > 0) {
            mAdapter.setHeaderView(new GuardListHeadView(getActivity(), resp.getList().get(0)));
            resp.getList().remove(0);
            mAdapter.setNewData(resp.getList());
        }
        ProtectedRankingItemBean bean = resp.getMy_info();
        if (bean == null) {
            mClSelfInfo.setVisibility(View.GONE);
            mBtnAction.setVisibility(View.VISIBLE);
        } else {
            myUserId = bean.getUser_id();
            mClSelfInfo.setVisibility(View.VISIBLE);
            mBtnAction.setVisibility(View.GONE);
            ImageUtils.loadHeadCC(bean.getHead_picture(), mRivAvatar);
            mTvName.setText(bean.getNickname());
            mTvDesc.setText(String.format("%s位：剩余%s天", bean.getType_name(), bean.getDays()));
            mRvMedal.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            mRvMedal.setAdapter(new GuardMedalAdapter(bean.getProtect_info()));
        }
        if (isHost) {
            mBtnAction.setVisibility(View.GONE);
            mClSelfInfo.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", mAdapter.getItem(position).getUser_id()).navigation();
    }
}

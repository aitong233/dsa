package com.spadea.xqipao.ui.room.fragment;

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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.yuyin.util.utilcode.ScreenUtils;
import com.spadea.xqipao.data.FmApplyWheatResp;
import com.spadea.xqipao.data.ProtectedRankingItemBean;
import com.spadea.xqipao.data.ProtectedRankingListResp;
import com.spadea.xqipao.ui.room.presenter.RoomGuardListPresenter;
import com.spadea.xqipao.utils.LogUtils;
import com.spadea.xqipao.utils.view.room.GuardListHeadView;
import com.spadea.xqipao.ui.base.view.BaseDialogFragment;
import com.spadea.xqipao.ui.room.activity.LiveRoomActivity;
import com.spadea.xqipao.ui.room.adapter.GuardMedalAdapter;
import com.spadea.xqipao.ui.room.adapter.RoomGuardAdapter;
import com.spadea.xqipao.ui.room.contacts.RoomGuardListContact;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.fragment
 * 创建人 王欧
 * 创建时间 2020/4/2 2:30 PM
 * 描述 describe
 */
public class RoomGuardListDialogFragment extends BaseDialogFragment<RoomGuardListPresenter> implements RoomGuardListContact.View, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.btn_action)
    Button mBtnAction;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.riv_avatar)
    RoundedImageView mRivAvatar;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_desc)
    TextView mTvDesc;
    @BindView(R.id.rv_medal)
    RecyclerView mRvMedal;
    @BindView(R.id.cl_self_info)
    ConstraintLayout mClSelfInfo;
    Unbinder unbinder;
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new RoomGuardAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(mRecyclerView);
        MvpPre.getProtectedRankingList(roomId);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initView(View rootView) {
        if (getArguments() != null) {
            roomId = getArguments().getString("roomId");
            isHost = getArguments().getBoolean("isHost");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragement_dialog_room_guard_list;
    }

    @Override
    protected RoomGuardListPresenter bindPresenter() {
        return new RoomGuardListPresenter(this, mContext);
    }

    @OnClick({R.id.btn_action, R.id.cl_self_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_action:
                LogUtils.e("btn_action","btn_action");
                if (mContext instanceof LiveRoomActivity) {
                    FmApplyWheatResp fmApplyWheatResp = new FmApplyWheatResp();
                    fmApplyWheatResp.setState(0);
                    ((LiveRoomActivity) mContext).applyWheatFmCallback(fmApplyWheatResp);
                }
                break;
            case R.id.cl_self_info:
                if (!TextUtils.isEmpty(myUserId)) {
                    ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", myUserId).navigation();
                }
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_r5_gradient_main));
        window.setGravity(Gravity.CENTER);
        int width = (int) (ScreenUtils.getScreenWidth() / 375.0 * 305);
        int height = (int) (width / 305.0 * 505);
        window.setLayout(width, height);
        window.setWindowAnimations(R.style.ShowDialogBottom);
        setCancelable(true);
    }

    @Override
    public void protectedRankingList(ProtectedRankingListResp resp) {
        mTvTitle.setText(String.format("守护榜(%s)", resp.getTotal()));
        if (resp.getList() != null && resp.getList().size() > 0) {
            mAdapter.setHeaderView(new GuardListHeadView(mContext, resp.getList().get(0)));
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
            ImageLoader.loadImage(MyApplication.getInstance(), mRivAvatar, bean.getHead_picture());
            mTvName.setText(bean.getNickname());
            mTvDesc.setText(String.format("%s位：剩余%s天", bean.getType_name(), bean.getDays()));
            mRvMedal.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            mRvMedal.setAdapter(new GuardMedalAdapter(bean.getProtect_info()));
        }
        if (isHost) {
            mBtnAction.setVisibility(View.GONE);
            mClSelfInfo.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", mAdapter.getItem(position).getUser_id()).navigation();
    }
}

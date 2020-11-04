package com.spadea.xqipao.ui.room.fragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.RoomUserInfo;
import com.spadea.xqipao.ui.room.presenter.RoomUserInfoPresenter;
import com.spadea.xqipao.utils.dialog.room.CountDownChooseDialog;
import com.spadea.xqipao.ui.base.view.BaseDialogFragment;
import com.spadea.xqipao.ui.room.contacts.RoomUserInfoContacts;

import java.util.Arrays;

import butterknife.BindView;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.utils.dialog.room
 * 创建人 王欧
 * 创建时间 2020/3/30 3:08 PM
 * 描述 describe
 */
public class RoomUserPermissionDialogFragment extends BaseDialogFragment<RoomUserInfoPresenter> implements BaseQuickAdapter.OnItemClickListener, RoomUserInfoContacts.View {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    private String mRoomId;
    private String mUserId;
    private RoomUserInfo mRoomUserInfo;
    private RoomFragmentListener mRoomFragmentListener;
    private BaseQuickAdapter<String, BaseViewHolder> mAdapter;

    public static RoomUserPermissionDialogFragment newInstance(String roomId, String userId) {
        RoomUserPermissionDialogFragment roomUserInfoDialogFragment = new RoomUserPermissionDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("roomId", roomId);
        bundle.putString("userId", userId);
        roomUserInfoDialogFragment.setArguments(bundle);
        return roomUserInfoDialogFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RoomFragmentListener) {
            mRoomFragmentListener = (RoomFragmentListener) context;
        }
    }

    @Override
    protected void initData() {
        mTvTitle.setText("管理权限");
        MvpPre.getRoomUserInfo(mRoomId, mUserId);
        String[] items = {"个人资料", "计时", "移麦", "禁麦", "禁言", "踢出"};
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.rv_item_dialog_count_down, Arrays.asList(items)) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.text, item);
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initView(View rootView) {
        mRoomId = getArguments().getString("roomId");
        mUserId = getArguments().getString("userId");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_count_down_choose;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        setCancelable(true);
    }

    @Override
    protected RoomUserInfoPresenter bindPresenter() {
        return new RoomUserInfoPresenter(this, mContext);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case 0:
                RoomUserInfoDialogFragment.newInstance(mRoomId, mUserId, false).show(getFragmentManager());
                dismiss();
                break;
            case 1:
                if(mRoomUserInfo!=null) {
                    new CountDownChooseDialog(getSelfActivity(), mRoomUserInfo.getRoom_info().getPit_number()).show();
                }else {
                    ToastUtils.showShort("网络状况不佳");
                }
                dismiss();
                break;
            case 2:
                if (mRoomUserInfo != null) {
                    if (mRoomUserInfo.getRoom_info().getPit_number() == 0) {
                        mRoomFragmentListener.getOnWheat(mUserId);
                        this.dismiss();
                    } else {
                        MvpPre.downUserWheat(mUserId, mRoomId, mRoomUserInfo.getNickname(), String.valueOf(mRoomUserInfo.getRoom_info().getPit_number()));
                    }
                }
                break;
            case 3:
                if (mRoomUserInfo != null) {
                    if (mRoomUserInfo.getRoom_info().getShutup() == 1) {
                        MvpPre.roomUserShutUp(mRoomId, mUserId, 2, mRoomUserInfo.getNickname());
                    } else {
                        MvpPre.roomUserShutUp(mRoomId, mUserId, 1, mRoomUserInfo.getNickname());
                    }
                }
                break;
            case 4:
                if (mRoomUserInfo != null) {
                    if (mRoomUserInfo.getRoom_info().getBanned() == 1) {
                        MvpPre.setRoomBanned(mRoomId, mUserId, 2, mRoomUserInfo.getNickname());
                    } else {
                        MvpPre.setRoomBanned(mRoomId, mUserId, 1, mRoomUserInfo.getNickname());
                    }
                }
                break;
            case 5:
                if (mRoomUserInfo != null) {
                    MvpPre.kickOut(mUserId, mRoomId, mRoomUserInfo.getNickname());
                }
                break;
        }

    }

    @Override
    public void setRoomUserInfoData(RoomUserInfo data) {
        this.mRoomUserInfo = data;
        RoomUserInfo.RoomInfoBean roomInfo = data.getRoom_info();
        if (roomInfo.getShutup() == 1) {
            mAdapter.setData(3, "解麦");
        } else {
            mAdapter.setData(3, "禁麦");
        }
        if (roomInfo.getBanned() == 1) {
            mAdapter.setData(4, "解禁");
        } else {
            mAdapter.setData(4, "禁言");
        }
    }

    @Override
    public void roomUserInfoFail() {
        this.dismiss();
    }

    @Override
    public void followUserSuccess(int type) {
        if (type == 1) {
            ToastUtils.showShort("关注成功");
        } else {
            ToastUtils.showShort("取消关注成功");
        }
        this.dismiss();
    }

    @Override
    public void downUserWheatSuccess(String userName, String pitNumber) {
        ToastUtils.showShort("抱下麦成功");
//        mRoomFragmentListener.downUserWheat(userName, pitNumber);
        this.dismiss();
    }

    /**
     * 禁麦成功
     *
     * @param type
     * @param userName
     */
    @Override
    public void roomUserShutUp(int type, String userName) {
        mRoomFragmentListener.shutUp(String.valueOf(type), userName, mUserId);
        this.dismiss();
    }


    /**
     * 踢出房间
     *
     * @param userName
     */
    @Override
    public void kickOutSuccess(String userName) {
        this.dismiss();
    }

    /**
     * 禁言
     *
     * @param userName
     * @param type
     */
    @Override
    public void setRoomBannedSuccess(String userName, int type) {
        if (type == 1) {
            mRoomFragmentListener.addRoomBanned(mUserId, userName);
        } else {
            mRoomFragmentListener.cancelRoomBanned(mUserId, userName);
        }
        this.dismiss();
    }
}

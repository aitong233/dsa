package com.qpyy.room.dialog;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.bean.RoomPitInfo;
import com.qpyy.room.contacts.WheatToolContacts;
import com.qpyy.room.event.BaoWheatEvent;
import com.qpyy.room.presenter.WheatToolPreswenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.dialog
 * 创建人 黄强
 * 创建时间 2020/8/1 16:13
 * 描述 麦位工具窗口
 */
public class RoomWheatManageDialogFragment extends BaseMvpDialogFragment<WheatToolPreswenter> implements WheatToolContacts.View {

    private final String TAG = "RoomWheatManageDialogFragment";

    @BindView(R2.id.iv_lock_wheat)
    ImageView ivLockWheat;
    @BindView(R2.id.ll_lock_wheat)
    LinearLayout llLockWheat;
    @BindView(R2.id.iv_lock_all_wheat)
    ImageView ivLockAllWheat;
    @BindView(R2.id.ll_lock_all_wheat)
    LinearLayout llLockAllWheat;
    @BindView(R2.id.iv_love_clear)
    ImageView ivLoveClear;
    @BindView(R2.id.ll_love_clear)
    LinearLayout llLoveClear;
    @BindView(R2.id.iv_bao_wheat)
    ImageView ivBaoWheat;
    @BindView(R2.id.ll_bao_wheat)
    LinearLayout llBaoWheat;
    @BindView(R2.id.iv_time)
    ImageView ivTime;
    @BindView(R2.id.ll_time)
    LinearLayout llTime;
    @BindView(R2.id.tv_cancel)
    TextView tvCancel;
    @BindView(R2.id.tv_bao_wheat)
    TextView tvBaoWheat;
    @BindView(R2.id.tv_lock_wheat)
    TextView tvLockWheat;
    @BindView(R2.id.iv_ban_wheat)
    ImageView ivBanWheat;
    @BindView(R2.id.tv_ban_wheat)
    TextView tvBanWheat;
    @BindView(R2.id.ll_ban_wheat)
    LinearLayout llBanWheat;

    private RoomPitInfo mRoomPitInfo;
    private String mRoomId;
    private String mPitNum;
    private boolean isStation;//是否是电台
    private String shutUp;

    //true为电台，隐藏爆麦
    public static RoomWheatManageDialogFragment newInstance(String roomId, String pitNumber, String shutUp, boolean b) {
        RoomWheatManageDialogFragment wheatToolDialogFragment = new RoomWheatManageDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("roomId", roomId);
        bundle.putString("pitNum", pitNumber);
        bundle.putBoolean("isStation", b);
        bundle.putString("shutUp", shutUp);
        wheatToolDialogFragment.setArguments(bundle);
        return wheatToolDialogFragment;
    }

    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
        int width = (int) (ScreenUtils.getScreenWidth() * 284 / 375.0);
        window.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.dimAmount = 0.4f;
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        mRoomId = arguments.getString("roomId");
        mPitNum = arguments.getString("pitNum");
        shutUp = arguments.getString("shutUp");

    }

    @Override
    public int getLayoutId() {
        Log.d("TAG", "(Start)启动了===========================RoomWheatManageDialogFragment");
        isStation = getArguments().getBoolean("isStation", false);
        return isStation ? R.layout.room_dialog_manage_wheat_other : R.layout.room_dialog_manage_wheat;
    }

    @Override
    public void initView() {
//        if (isStation) {
//            baoWheatGone();
//        }
        if ("1".equals(shutUp)) {
            tvBanWheat.setText("解麦");
        } else {
            tvBanWheat.setText("禁麦");
        }
    }

    @Override
    protected WheatToolPreswenter bindPresenter() {
        return new WheatToolPreswenter(this, getContext());
    }

    @Override
    public void initData() {
        MvpPre.roomPitInfo(mRoomId, mPitNum);
    }


    @OnClick({R2.id.ll_lock_wheat, R2.id.ll_lock_all_wheat, R2.id.ll_love_clear, R2.id.ll_bao_wheat, R2.id.ll_time, R2.id.ll_ban_wheat, R2.id.tv_cancel})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.ll_lock_wheat) {
            if (mRoomPitInfo != null) {
                //1锁麦 2接麦
                if (mRoomPitInfo.getState().equals("1")) {
                    MvpPre.closePit("2", mPitNum, mRoomId);
                } else {
                    MvpPre.closePit("1", mPitNum, mRoomId);
                }
            }
            LogUtils.e(TAG, "点击了锁麦");
        } else if (id == R.id.ll_lock_all_wheat) {
            LogUtils.e(TAG, "点击了一键全锁");
            MvpPre.closeAllPit(mRoomId);
        } else if (id == R.id.ll_love_clear) {
            MvpPre.clearCardiac(mRoomId, mPitNum);
            LogUtils.e(TAG, "点击了清除心动值");
        } else if (id == R.id.ll_bao_wheat) {
//            if (isStation) {//是电台，抱麦变成计时
//                if (mRoomPitInfo != null) {
//                    CountDownChooseDialog.newInstance(mRoomId, mPitNum).show(getFragmentManager());
//                    dismiss();
//                    LogUtils.e(TAG, "点击了计时");
//                }
//                return;
//            }
            EventBus.getDefault().post(new BaoWheatEvent(true));
            LogUtils.e(TAG, "点击了抱麦");
            dismiss();
        } else if (id == R.id.ll_time) {
            if (mRoomPitInfo != null) {
                CountDownChooseDialog.newInstance(mRoomId, mPitNum).show(getFragmentManager());
                dismiss();
            }
            LogUtils.e(TAG, "点击了计时");
        } else if (id == R.id.ll_ban_wheat) {
            if ("1".equals(shutUp)) {
                MvpPre.roomUserShutUp(mRoomId, mPitNum, 2);
            } else {
                MvpPre.roomUserShutUp(mRoomId, mPitNum, 1);
            }
            LogUtils.e(TAG, "点击了禁麦");
        } else if (id == R.id.tv_cancel) {
            dismiss();
            return;
        }
    }

    /**
     * 抱麦隐藏
     *
     * @param
     */
    private void baoWheatGone() {
        ivBaoWheat.setImageResource(R.mipmap.room_all_lock_bt);
        tvBaoWheat.setText("计时");
        llTime.setVisibility(View.INVISIBLE);
    }


    @Override
    public void setRoomPitInfo(RoomPitInfo data) {
        this.mRoomPitInfo = data;
        if ("1".equals(data.getState())) {
            ivLockWheat.setImageResource(R.mipmap.room_ic_unlock_wheat);
            tvLockWheat.setText("解锁");
        } else {
            ivLockWheat.setImageResource(R.mipmap.room_lock_bt);
            tvLockWheat.setText("锁麦");
        }
    }


    @Override
    public void clearCardiacSuccess() {
        dismiss();
    }

    @Override
    public void closePitSuccess() {
        dismiss();
    }

    @Override
    public void dismissDialog() {
        dismiss();
    }

    public void show(FragmentManager childFragmentManager) {
        show(childFragmentManager, "RoomWheatManageDialogFragment");
    }

}

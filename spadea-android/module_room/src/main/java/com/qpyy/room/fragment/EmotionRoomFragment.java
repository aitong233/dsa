package com.qpyy.room.fragment;

import android.os.Bundle;

import com.blankj.utilcode.util.ObjectUtils;
import com.qpyy.libcommon.event.RoomBeckoningEvent;
import com.qpyy.libcommon.event.RoomWheatEvent;
import com.qpyy.libcommon.event.UserInfoShowEvent;
import com.qpyy.libcommon.utils.OnClickUtils;
import com.qpyy.libcommon.widget.dialog.CommonDialog;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.bean.RoomBean;
import com.qpyy.room.bean.RoomInfoResp;
import com.qpyy.room.bean.RoomPitBean;
import com.qpyy.room.contacts.EmotionRoomContacts;
import com.qpyy.room.dialog.RoomWheatManageDialogFragment;
import com.qpyy.room.presenter.EmotionRoomPresenter;
import com.qpyy.room.widget.RoomDefaultWheatView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class EmotionRoomFragment extends BaseRoomFragment<EmotionRoomPresenter> implements EmotionRoomContacts.View {

    @BindView(R2.id.dhv_host)
    RoomDefaultWheatView mDhvHost;
    @BindView(R2.id.dhv1)
    RoomDefaultWheatView mDhv1;
    @BindView(R2.id.dhv2)
    RoomDefaultWheatView mDhv2;
    @BindView(R2.id.dhv3)
    RoomDefaultWheatView mDhv3;
    @BindView(R2.id.dhv4)
    RoomDefaultWheatView mDhv4;
    @BindView(R2.id.dhv5)
    RoomDefaultWheatView mDhv5;
    @BindView(R2.id.dhv6)
    RoomDefaultWheatView mDhv6;
    @BindView(R2.id.dhv7)
    RoomDefaultWheatView mDhv7;
    @BindView(R2.id.dhv8)
    RoomDefaultWheatView mDhv8;

    private String roomId;

    private RoomBean mRoomBean;
    private List<RoomPitBean> mPitList;
    private RoomInfoResp roomInfoResp;
    private String pitNumber;//当前点击的麦序
    private RoomWheatManageDialogFragment roomWheatManageDialogFragment;


    public static EmotionRoomFragment newInstance(RoomInfoResp roomInfo) {
        Bundle args = new Bundle();
        args.putSerializable("roomInfo", roomInfo);
        EmotionRoomFragment fragment = new EmotionRoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        roomInfoResp = (RoomInfoResp) arguments.getSerializable("roomInfo");
        mRoomBean = roomInfoResp.getRoom_info();
        roomId = mRoomBean.getRoom_id();
        mPitList = mRoomBean.getPit_list();
    }

    @Override
    protected EmotionRoomPresenter bindPresenter() {
        return new EmotionRoomPresenter(this, getActivity());
    }

    @Override
    protected void initData() {

        updateWheatData();
    }

    private void updateWheatData() {
        if (!ObjectUtils.isEmpty(mPitList)) {
            for (RoomPitBean bean : mPitList) {
                EventBus.getDefault().post(bean);
            }
        }
        RoomBeckoningEvent event = new RoomBeckoningEvent(roomId,mRoomBean.getCardiac()==1);
        EventBus.getDefault().post(event);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_fragement_emotion;
    }

    @OnClick({R2.id.dhv_host, R2.id.dhv1, R2.id.dhv2, R2.id.dhv3, R2.id.dhv4, R2.id.dhv5, R2.id.dhv6, R2.id.dhv7, R2.id.dhv8})
    public void onWheatClicked(RoomDefaultWheatView view) {
        if (view.isOn()) {
            EventBus.getDefault().post(new UserInfoShowEvent(roomId, view.pitBean.getUser_id()));
        } else {
            if (roomInfoResp.isWheatManager() ||(roomInfoResp.isManager() &&  view.isLocked())) {
                if (!OnClickUtils.isFastDoubleClick()) {
                    //弹出空麦位弹窗
                    if (roomWheatManageDialogFragment != null) {
                        roomWheatManageDialogFragment.dismiss();
                    }
                    roomWheatManageDialogFragment = RoomWheatManageDialogFragment.newInstance(roomId, view.pitNumber,view.pitBean.getShutup(), false);
                    roomWheatManageDialogFragment.show(getChildFragmentManager());
                }
                return;
            }
            if ("1".equals(roomInfoResp.getRoom_info().getWheat())) {//自由模式
                MvpPre.applyWheat(roomId, view.pitNumber);
            } else {//排麦模式
                //普通用户排麦模式弹出提示框
                if (!roomInfoResp.isManager()) {
                    showConfirmApplyWait();
                } else {
                    MvpPre.applyWheatWait(roomId, view.pitNumber);
                }
            }
        }
        pitNumber = view.pitNumber;
    }

    CommonDialog commonDialog;

    private void showConfirmApplyWait() {
        if (commonDialog == null) {
            commonDialog = new CommonDialog(getContext());
            commonDialog.setContent("是否加入当前麦序队列");
            commonDialog.setmOnClickListener(new CommonDialog.OnClickListener() {
                @Override
                public void onLeftClick() {

                }

                @Override
                public void onRightClick() {
                    MvpPre.applyWheatWait(roomId, pitNumber);
                }
            });
        }
        if (!commonDialog.isShowing()) {
            commonDialog.show();
        }
    }

    @Override
    public void roomInfoUpdate(RoomInfoResp resp) {
        mRoomBean = resp.getRoom_info();
        roomId = mRoomBean.getRoom_id();
        mPitList = mRoomBean.getPit_list();
        updateWheatData();
    }

    @Override
    public void registerWheatViews() {
        mDhv1.register(this);
        mDhv2.register(this);
        mDhv3.register(this);
        mDhv4.register(this);
        mDhv5.register(this);
        mDhv6.register(this);
        mDhv7.register(this);
        mDhv8.register(this);
        mDhvHost.register(this);
    }

    @Override
    public void unRegisterWheatViews() {
        mDhv1.unRegister(this);
        mDhv2.unRegister(this);
        mDhv3.unRegister(this);
        mDhv4.unRegister(this);
        mDhv5.unRegister(this);
        mDhv6.unRegister(this);
        mDhv7.unRegister(this);
        mDhv8.unRegister(this);
        mDhvHost.unRegister(this);
    }

    @Override
    protected void initListener() {
        super.initListener();

    }

    /**
     * 上麦模式变化通知 1自由2排麦
     *
     * @param roomWheatEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomWheatEvent roomWheatEvent) {
        if (roomId.equals(roomWheatEvent.getRoomId())) {
            roomInfoResp.getRoom_info().setWheat(roomWheatEvent.isFree() ? "1" : "2");
        }
    }
}
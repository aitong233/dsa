package com.qpyy.room.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ObjectUtils;
import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.event.RoomBeckoningEvent;
import com.qpyy.libcommon.event.RoomWheatEvent;
import com.qpyy.libcommon.event.UserInfoShowEvent;
import com.qpyy.libcommon.utils.OnClickUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.bean.ProtectedItemBean;
import com.qpyy.room.bean.RoomBean;
import com.qpyy.room.bean.RoomInfoResp;
import com.qpyy.room.bean.RoomPitBean;
import com.qpyy.room.contacts.StationRoomContacts;
import com.qpyy.room.dialog.OpenGuardDialog;
import com.qpyy.room.dialog.RoomWheatManageDialogFragment;
import com.qpyy.room.event.OpenGuardEvent;
import com.qpyy.room.presenter.StationRoomPresenter;
import com.qpyy.room.widget.RoomStationWheatView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class StationRoomFragment extends BaseRoomFragment<StationRoomPresenter> implements StationRoomContacts.View {

    @BindView(R2.id.dhv_host)
    RoomStationWheatView mDhvHost;
    @BindView(R2.id.iv_guard_list)
    ImageView mIvGuardList;
    @BindView(R2.id.dhv5)
    RoomStationWheatView mDhv5;
    @BindView(R2.id.dhv6)
    RoomStationWheatView mDhv6;
    @BindView(R2.id.dhv7)
    RoomStationWheatView mDhv7;
    @BindView(R2.id.dhv8)
    RoomStationWheatView mDhv8;
    @BindView(R2.id.iv_anchor_list)
    ImageView mIvAnchorList;
    private String roomId;

    private RoomBean mRoomBean;
    private RoomInfoResp roomInfoResp;
    private List<RoomPitBean> mPitList;
    private OpenGuardDialog openGuardDialog;
    private RoomWheatManageDialogFragment roomWheatManageDialogFragment;

    public static StationRoomFragment newInstance(RoomInfoResp roomInfo) {

        Bundle args = new Bundle();
        args.putSerializable("roomInfo", roomInfo);
        StationRoomFragment fragment = new StationRoomFragment();
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
    protected void initData() {
        updateWheatData();
    }

    private void updateWheatData() {
        if (!ObjectUtils.isEmpty(mPitList)) {
            for (RoomPitBean bean : mPitList) {
                EventBus.getDefault().post(bean);
            }
        }
        RoomBeckoningEvent event = new RoomBeckoningEvent(roomId, mRoomBean.getCardiac() == 1);
        EventBus.getDefault().post(event);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_fragement_station_room;
    }

    @Override
    protected StationRoomPresenter bindPresenter() {
        return new StationRoomPresenter(this, getActivity());
    }

    @OnClick({R2.id.dhv_host, R2.id.dhv5, R2.id.dhv6, R2.id.dhv7, R2.id.dhv8})
    public void onWheatClicked(RoomStationWheatView view) {
        if (view.isOn()) {
            EventBus.getDefault().post(new UserInfoShowEvent(roomId, view.pitBean.getUser_id()));
        } else {
            if (roomInfoResp.isWheatManager() || (roomInfoResp.isManager() && view.isLocked())) {
                //弹出空麦位弹窗
                if (roomWheatManageDialogFragment != null) {
                    roomWheatManageDialogFragment.dismiss();
                }
                roomWheatManageDialogFragment = RoomWheatManageDialogFragment.newInstance(roomId, view.pitNumber, view.pitBean.getShutup(), true);
                roomWheatManageDialogFragment.show(getChildFragmentManager());
                return;
            }
            MvpPre.applyWheatFm(roomId, view.pitNumber);
        }
    }

    @OnClick({R2.id.iv_guard_list, R2.id.iv_anchor_list})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.iv_guard_list) {
            boolean isHost = false;
            if (mPitList != null && mPitList.size() > 0) {
                isHost = BaseApplication.getIns().getUser().getUser_id().equals(mPitList.get(mPitList.size() - 1).getUser_id());
            }
            RoomGuardListDialogFragment.newInstance(roomId, isHost).show(getChildFragmentManager(), "RoomGuardListDialogFragment");
        } else if (id == R.id.iv_anchor_list) {
            RoomHostListDialogFragment.newInstance(roomId).show(getChildFragmentManager(), "RoomHostListDialogFragment");
        }
    }

    @Override
    public void protectedList(List<ProtectedItemBean> list, int type) {
        if (mPitList == null || mPitList.size() == 0) {
            return;
        }
        RoomPitBean roomPitBean = mPitList.get(mPitList.size() - 1);
        if (!OnClickUtils.isFastDoubleClick()) {//防止重复点击
            if (openGuardDialog != null) {
                openGuardDialog.dismiss();
            }
            openGuardDialog = new OpenGuardDialog(getActivity());
            openGuardDialog.show();
            openGuardDialog.setData(roomPitBean.getNickname(), type, list, new OpenGuardDialog.OnSelectedProtectListener() {
                @Override
                public void onSelectedProtect(String type) {
                    MvpPre.openFmProtected(roomId, type, roomPitBean.getUser_id());
                }
            });
        }
    }

    @Override
    public void dismissOpenGuardDialog() {
        if (openGuardDialog != null && openGuardDialog.isShowing()) {
            openGuardDialog.dismiss();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void openGuardEvent(OpenGuardEvent event) {
        MvpPre.getProtectedList(1);
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
        mDhv5.register(this);
        mDhv6.register(this);
        mDhv7.register(this);
        mDhv8.register(this);
        mDhvHost.register(this);
    }

    @Override
    public void unRegisterWheatViews() {
        mDhv5.unRegister(this);
        mDhv6.unRegister(this);
        mDhv7.unRegister(this);
        mDhv8.unRegister(this);
        mDhvHost.unRegister(this);
    }

    /**
     * 上麦模式变化通知 1自由2排麦
     *
     * @param roomWheatEvent
     */
    @Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public void subscribeMessages(RoomWheatEvent roomWheatEvent) {
        if (roomId.equals(roomWheatEvent.getRoomId())) {
            roomInfoResp.getRoom_info().setWheat(roomWheatEvent.isFree() ? "1" : "2");
        }
    }
}